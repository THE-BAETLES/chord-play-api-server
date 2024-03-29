package com.chordplay.chordplayapiserver.domain.video.service;

import com.chordplay.chordplayapiserver.domain.video.dto.YoutubeVideoResponse;
import com.chordplay.chordplayapiserver.domain.video.exception.InvaliedVideoException;
import com.chordplay.chordplayapiserver.global.exception.RuntimeIoException;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class YoutubeVideoSearch {

    /** Global instance properties filename. */
    private String PROPERTIES_FILENAME = "youtube.properties";

    /** Global instance of the HTTP transport. */
    private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /** Global instance of the JSON factory. */
    private final JsonFactory JSON_FACTORY = new JacksonFactory();

    /** Global instance of the max number of videos we want returned (50 = upper limit per page). */
    private final long NUMBER_OF_VIDEOS_RETURNED = 25;

    /** Global instance of Youtube object to make all API requests. */
    private YouTube youtube;

    @Value(value = "${youtube.api-key}")
    private String apiKey;


    public YoutubeVideoSearch() {
        /*
         * The YouTube object is used to make all API requests. The last argument is required, but
         * because we don't need anything initialized when the HttpRequest is initialized, we override
         * the interface and provide a no-op function.
         */
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {}
        }).setApplicationName("youtube-cmdline-search-sample").build();
        log.info("youtube bean 초기화 완료");
    }

    public List<SearchResult> search_KR(String search_query){
        return this.search(search_query, "KR");
    }



    public List<SearchResult> search (String search_query, String regionCode){

        List<SearchResult> searchResultList = null;

        try {
            YouTube.Search.List search = youtube.search().list("id, snippet");

            this.setYoutubeSearchConfig(search,search_query,regionCode);

            SearchListResponse searchResponse = search.execute();
            searchResultList = searchResponse.getItems();
            return searchResultList;

        } catch (GoogleJsonResponseException e) {
            log.error("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
            throw new RuntimeIoException();
        } catch (IOException e) {
            log.error("There was an IO error: " + e.getCause() + " : " + e.getMessage());
            throw new RuntimeIoException();
        } catch (Throwable t) {
            log.error(t.getMessage());
            throw new RuntimeIoException();
        }
    }

    private void setYoutubeSearchConfig(YouTube.Search.List search, String search_query, String regionCode) throws IOException {

        // Get query term from user.
        String queryTerm = search_query;
        search.setQ(queryTerm);

        /*
         * It is important to set your API key from the Google Developer Console for
         * non-authenticated requests (found under the Credentials tab at this link:
         * console.developers.google.com/). This is good practice and increased your quota.
         */
        search.setKey(apiKey);
        /*
         * We are only searching for videos (not playlists or channels). If we were searching for
         * more, we would add them as a string like this: "video,playlist,channel".
         */
        search.setType("video");

        /*
         * This method reduces the info returned to only the fields we need and makes calls more
         * efficient.
         */
        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/high/url,snippet/publishedAt,snippet/channelTitle)");
        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

        /*
         * 글로벌 서비스 출시에 맞게 해당 나라와 연관된 검색이 되어야 함
         */
        search.setRegionCode(regionCode);

    }

    public Video getYoutubeVideoInfo(String video_id) {

        try {

            Video video = null;
            YouTube.Videos.List videos = youtube.videos().list("id, snippet, contentDetails");
            videos.setKey(apiKey);
            videos.setId(video_id);
            videos.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            videos.setFields("items(id,snippet/title,snippet/thumbnails/high/url,snippet/publishedAt,snippet/channelTitle,snippet/tags,contentDetails/duration)");
            VideoListResponse videoResponse = null;
            videoResponse = videos.execute();
            if (videoResponse.getItems().size() > 0) {
                video = videoResponse.getItems().get(0);
                return video;
            } else {
                throw new InvaliedVideoException();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvaliedVideoException e){
            throw e;
        }
    }

    /*
     * Prints out all SearchResults in the Iterator. Each printed line includes title, id, and
     * thumbnail.
     *
     * @param iteratorSearchResults Iterator of SearchResults to print
     *
     * @param query Search query (String)
     */
    private void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n==========================================================ㄴ===");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Double checks the kind is video.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = (Thumbnail)singleVideo.getSnippet().getThumbnails().get("default");

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }
}

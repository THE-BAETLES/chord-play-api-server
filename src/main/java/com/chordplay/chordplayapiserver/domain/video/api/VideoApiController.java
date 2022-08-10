package com.chordplay.chordplayapiserver.domain.video.api;

import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoApiController {


    @GetMapping(value = "/ai/{search_query}")
    public Object search(@PathVariable("search_query") String search_query){



        return null;
    }


}

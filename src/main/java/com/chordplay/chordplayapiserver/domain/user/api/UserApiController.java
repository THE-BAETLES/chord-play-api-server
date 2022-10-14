package com.chordplay.chordplayapiserver.domain.user.api;

import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetails;
import com.chordplay.chordplayapiserver.domain.user.dto.*;
import com.chordplay.chordplayapiserver.domain.user.exception.NotFullyJoinedException;
import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import com.chordplay.chordplayapiserver.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@AuthenticationPrincipal PrincipalDetails principalDetails){
        if (principalDetails.getUser().getNickname() == null) throw new NotFullyJoinedException("세부 회원가입이 필요합니다");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/nickname")
    public ApiResponse<NicknameResponse> getNickname(@AuthenticationPrincipal PrincipalDetails principalDetails){
        String userEmail = principalDetails.getUser().getEmail();
        NicknameResponse nicknameResponse = new NicknameResponse(userService.RecommendNickname(userEmail));
        return ApiResponse.success(nicknameResponse, 200);
    }

    @PostMapping("/check-duplication")
    public ResponseEntity<Void> checkDuplication(@RequestBody CheckDuplicationRequest dto){
        String nickname = dto.getNickname();
        userService.checkNicknameDuplication(nickname);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup-favorite")
    public void getEarlyFavoriteSongs(@RequestBody SignupFavoriteRequest req){
        userService.createSignupFavorite(req);
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody JoinRequest dto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        dto.setFirebaseJwtInfo(principalDetails.getUser());
        userService.join(dto);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("my-collection/{videoId}")
    public ResponseEntity<ApiResponse<String>> addVideoIdToMyCollection(@PathVariable String videoId){
        userService.addVideoIdToMyCollection(videoId);
        ApiResponse<String> body = ApiResponse.success("", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @DeleteMapping("my-collection/{videoId}")
    public ResponseEntity<ApiResponse<String>> deleteVideoIdFromMyCollection(@PathVariable String videoId){
        userService.deleteVideoIdFromMyCollection(videoId);
        ApiResponse<String> body = ApiResponse.success("", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}

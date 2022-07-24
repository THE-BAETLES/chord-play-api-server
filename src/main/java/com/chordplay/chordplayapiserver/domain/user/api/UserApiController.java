package com.chordplay.chordplayapiserver.domain.user.api;

import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetails;
import com.chordplay.chordplayapiserver.domain.user.dto.CheckDuplicationRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.FavoritesResponse;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.NicknameResponse;
import com.chordplay.chordplayapiserver.domain.user.exception.NotFullyJoinedException;
import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
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
        if (principalDetails.getUser().getNickname().equals(null)) throw new NotFullyJoinedException("세부 회원가입이 필요합니다");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/nickname")
    public NicknameResponse getNickname(@AuthenticationPrincipal PrincipalDetails principalDetails){
        String userEmail = principalDetails.getUser().getEmail();
        return new NicknameResponse(userService.RecommendNickname(userEmail));
    }

    @PostMapping("/check-duplication")
    public ResponseEntity<Void> checkDuplication(@RequestBody CheckDuplicationRequest dto){
        String nickname = dto.getNickname();
        userService.checkNicknameDuplication(nickname);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favorites")
    public ResponseEntity<FavoritesResponse> getEarlyFavoriteSongs(@RequestParam HashMap<String,String> paramMap){
        //수정중...
        return ResponseEntity.ok().body(userService.getFavorites());
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody JoinRequest dto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        dto.setFirebaseJwtInfo(principalDetails.getUser());
        userService.join(dto);
        return ResponseEntity.ok().build();
    }
}

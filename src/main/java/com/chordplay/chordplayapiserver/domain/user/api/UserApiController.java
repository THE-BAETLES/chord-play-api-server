package com.chordplay.chordplayapiserver.domain.user.api;

import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetails;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApiController {

    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/nickname")
    public ResponseEntity<Void> getNickname(){ return ResponseEntity.ok().build();}

    @PostMapping("check-duplication")
    public ResponseEntity<Void> checkDuplication(@RequestParam String nickname){
        userService.checkNicknameDuplication(nickname);
        return ResponseEntity.ok().build();
    }

    @GetMapping("favorite")
    public ResponseEntity<Void> getEarlyFavoriteSongs(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("join")
    public ResponseEntity<Void> join(@RequestBody JoinRequest dto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        userService.join(dto);
        return ResponseEntity.ok().build();
    }
}

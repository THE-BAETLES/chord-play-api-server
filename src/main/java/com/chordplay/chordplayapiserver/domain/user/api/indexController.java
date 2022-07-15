package com.chordplay.chordplayapiserver.domain.user.api;

import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetails;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class indexController {

    private final UserService userService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("principalDetails: " + principalDetails.getUser());
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    @GetMapping("/loginForm")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testLogin(Authentication authentication){
        System.out.println("/test/login==========");
        OAuth2User principalDetails = (OAuth2User) authentication.getPrincipal();

        return "OAUTH 세션 정보 확인하기";
    }

    @PostMapping("/join")
    public String join(JoinRequest dto){
        userService.join(dto);
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String loginProc(){
        return "joinForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "정보 입니다";
    }


}

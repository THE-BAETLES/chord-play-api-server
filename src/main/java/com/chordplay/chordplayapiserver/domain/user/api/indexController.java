package com.chordplay.chordplayapiserver.domain.user.api;

import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody String user(){
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

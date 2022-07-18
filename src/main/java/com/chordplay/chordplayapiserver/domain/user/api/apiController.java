package com.chordplay.chordplayapiserver.domain.user.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class apiController {

    @PostMapping("token")
    public String token(){
        return "<h1>token</h1>";
    }

    @GetMapping("/user")
    public String user(){ return "user";}
}

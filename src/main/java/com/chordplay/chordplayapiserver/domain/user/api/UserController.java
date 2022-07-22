package com.chordplay.chordplayapiserver.domain.user.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<Void> login(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public String user(){ return "user";}
}

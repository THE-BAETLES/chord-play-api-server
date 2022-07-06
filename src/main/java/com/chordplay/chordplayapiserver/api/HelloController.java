package com.chordplay.chordplayapiserver.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HelloController {


    @GetMapping ("/hi")
    public String hi(){
        return "hello";
    }
    @PostMapping("/hi")
    public ResponseEntity<HelloDTO> hi(@RequestBody HelloDTO helloDTO){
        System.out.println(helloDTO);
        return ResponseEntity.ok(helloDTO);
    }


}

package com.chordplay.chordplayapiserver.domain.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

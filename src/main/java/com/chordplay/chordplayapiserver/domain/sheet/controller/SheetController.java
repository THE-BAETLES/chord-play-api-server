package com.chordplay.chordplayapiserver.domain.sheet.controller;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1")
public class SheetController {
    @GetMapping("/sheet/ai")
    @ResponseBody
    public void getAISheet(@RequestParam String videoId){

    }

    @GetMapping("/sheet/{userId}")
    @ResponseBody
    public void getUserSheet(@PathVariable("userId") @NotNull String userId){

    }

    @PostMapping("/sheet/{userId}")
    public void createUserSheet(@PathVariable("userId") @NotNull String userId){}



    @GetMapping("/sheet/{videoId}")
    public void getVideoSheet(@PathVariable("videoId") @NotNull String videoId){
    }

}

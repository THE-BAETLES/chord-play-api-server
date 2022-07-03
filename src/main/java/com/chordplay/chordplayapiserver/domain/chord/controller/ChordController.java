package com.chordplay.chordplayapiserver.domain.chord.controller;

import com.chordplay.chordplayapiserver.domain.chord.entity.ChordRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class ChordController {
    @PostMapping("/sheet")
    public void createSheet(@RequestBody ChordRequest request){

    }
}

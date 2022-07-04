package com.chordplay.chordplayapiserver.domain.sheet.controller;

import com.chordplay.chordplayapiserver.domain.sheet.entity.PostAISheet;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetGenerateService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sheets")
public class SheetController {

/*
  생성자 주입 방식
  생성자 파라미터가 빈일 경우 스프링이 생성자를 자동으로 주입함.

  생성자 주입을 사용하는 이유
  1. final 키워드를 사용하여 객체가 변경 불가능하도록 함
  2. 생성자에 의존하는 클래스를 명시함으로서 개발자로 하여금 단일 책임 원칙을 지키게 하도록 유도함

 */
    private final SheetGenerateService generateService;

    public SheetController(SheetGenerateService generateService){
        this.generateService = generateService;
    }

    /*
    Long polling 방식으로 클라이언트와 통신해야함
    *
    */
    @PostMapping("/ai")
    @ResponseBody
    public void createAISheet(@RequestBody PostAISheet sheetInfo){
        generateService.createSheet();


    }

}

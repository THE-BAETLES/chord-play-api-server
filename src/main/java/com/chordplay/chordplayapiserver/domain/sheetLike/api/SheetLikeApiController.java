package com.chordplay.chordplayapiserver.domain.sheetLike.api;

import com.chordplay.chordplayapiserver.domain.sheetLike.service.SheetLikeService;
import com.chordplay.chordplayapiserver.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sheet-like")
@RequiredArgsConstructor
@Slf4j
public class SheetLikeApiController {

    private final SheetLikeService sheetLikeService;

    @PostMapping("/{sheetId}")
    public ResponseEntity<ApiResponse<String>> addLike(@PathVariable("sheetId") String sheetId){
        sheetLikeService.addLike(sheetId);
        ApiResponse<String> body = ApiResponse.success("", HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }


}

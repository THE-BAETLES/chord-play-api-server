package com.chordplay.chordplayapiserver.api.sheet;

import com.chordplay.chordplayapiserver.dto.sheet.SheetAiRequest;
import com.chordplay.chordplayapiserver.service.SheetGenerateService;
import com.chordplay.chordplayapiserver.service.SheetService;
import com.chordplay.chordplayapiserver.dto.sheet.SheetDataResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sheets")
@RequiredArgsConstructor
public class SheetApiController {
    private final SheetGenerateService sheetGenerateService;
    private final SheetService sheetService;

    @PostMapping("/ai")
    public ResponseEntity<Void> sheetAi(@RequestBody SheetAiRequest dto){
        sheetGenerateService.createAi(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SheetDataResponseDTO> sheet(@PathVariable("id") String id){
        System.out.println("sheet service: " + sheetService);

        SheetDataResponseDTO responseDTO = sheetService.read(id);
        return ResponseEntity.ok(responseDTO);
    }


}

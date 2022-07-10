package com.chordplay.chordplayapiserver.domain.sheet.api;

import com.chordplay.chordplayapiserver.domain.sheet.dto.sheet.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetGenerateService;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.sheet.dto.sheet.SheetDataResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

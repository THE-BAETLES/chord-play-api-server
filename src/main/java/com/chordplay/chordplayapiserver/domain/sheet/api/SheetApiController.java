package com.chordplay.chordplayapiserver.domain.sheet.api;

import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.service.SheetService;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sheets")
@RequiredArgsConstructor
public class SheetApiController {
    private final SheetService sheetService;

    @PostMapping("/ai")
    public ResponseEntity<Void> sheetAi(@RequestBody SheetAiRequest dto){
        sheetService.createAi(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SheetDataResponse> sheet(@PathVariable("id") String id){
        System.out.println("sheet service: " + sheetService);

        SheetDataResponse responseDTO = sheetService.read(id);
        return ResponseEntity.ok(responseDTO);
    }


}

package com.chordplay.chordplayapiserver.api.sheet;

import com.chordplay.chordplayapiserver.service.SheetService;
import com.chordplay.dto.SheetDataRequestDTO;
import com.chordplay.dto.SheetDataResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sheet")
@RequiredArgsConstructor
public class SheetApiController {

    private SheetService sheetService;
    @GetMapping(value = "/")
    public ResponseEntity<SheetDataResponseDTO> sheet(@RequestBody SheetDataRequestDTO requestDTO){

        SheetDataResponseDTO responseDTO = sheetService.read(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

}

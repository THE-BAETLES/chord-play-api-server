package com.chordplay.chordplayapiserver.api.sheet;

import com.chordplay.chordplayapiserver.service.SheetService;
import com.chordplay.dto.SheetDataRequestDTO;
import com.chordplay.dto.SheetDataResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sheet")
@RequiredArgsConstructor
public class SheetApiController {
    private final SheetService sheetService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SheetDataResponseDTO> sheet(@PathVariable("id") String id){
        System.out.println("sheet service: " + sheetService);

        SheetDataResponseDTO responseDTO = sheetService.read(id);
        return ResponseEntity.ok(responseDTO);
    }


}

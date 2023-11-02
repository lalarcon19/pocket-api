package com.bidi.pocketapi.controller;

import com.bidi.pocketapi.ApiException;
import com.bidi.pocketapi.dto.PocketRequest;
import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.service.IPocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("bidi/pocket")
public class PocketController {
    private final IPocketService iPocketService;

    @GetMapping("/all")
    public ResponseEntity <List<PocketResponse>> getAllPocket() throws ApiException {
        List<PocketResponse> pocketResponse = iPocketService.getAllPocket();
        return ResponseEntity.status(HttpStatus.OK).body(pocketResponse);
    }
    @GetMapping("/{namePocket}")
    public ResponseEntity <PocketResponse> getByIdPocket (@PathVariable String namePocket) throws ApiException {
        PocketResponse pocketResponse = iPocketService.getByNamePocket(namePocket);
        return ResponseEntity.status(HttpStatus.OK).body(pocketResponse);
    }

    @PostMapping("/create")
    public ResponseEntity <PocketResponse>createPocket (@RequestBody PocketRequest pocketRequest) throws ApiException {
        PocketResponse pocketResponse = iPocketService.createPocket(pocketRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pocketResponse);
    }

    @PatchMapping("/update/{idPocket}")
    public ResponseEntity <PocketResponse> updatePocket (@PathVariable long idPocket, @RequestBody PocketRequest pocketRequest) throws ApiException {
        PocketResponse response = iPocketService.updatePocket(idPocket,pocketRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{idPocket}")
    public ResponseEntity deletePocket (@PathVariable long idPocket) throws ApiException {
        iPocketService.deletePocket(idPocket);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}

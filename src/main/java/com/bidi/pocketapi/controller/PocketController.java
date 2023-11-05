package com.bidi.pocketapi.controller;

import com.bidi.pocketapi.dto.PocketRequest;
import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.exception.ApiException;
import com.bidi.pocketapi.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pocket")
public class PocketController {
    private final IGetAllPocketService getAllPocket;
    private final IGetByIdPocketService getByIdPocket;
    private final ICreatePocketService createPocket;
    private final IUpdatePocketService updatePocket;
    private final IDeletePocketService deletePocket;


    @GetMapping("/{idUser}")
    public ResponseEntity <List<PocketResponse>> getAllPocket() throws ApiException {
        List<PocketResponse> pocketResponse = getAllPocket.getAllPocket();
        return ResponseEntity.status(HttpStatus.OK).body(pocketResponse);
    }
    @GetMapping("/{idUSer}/{idPocket}")
    public ResponseEntity <PocketResponse> getByIdPocket (@PathVariable long idPocket) throws ApiException {
        PocketResponse pocketResponse = getByIdPocket.getByIdPocket(idPocket);
        return ResponseEntity.status(HttpStatus.OK).body(pocketResponse);
    }

    @PostMapping("/{idUser}")
    public ResponseEntity <PocketResponse>createPocket (@PathVariable String idUser, @RequestBody PocketRequest pocketRequest) throws ApiException {
        PocketResponse pocketResponse = createPocket.createPocket(pocketRequest, idUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(pocketResponse);
    }

    @PutMapping("/{idPocket}")
    public ResponseEntity <PocketResponse> updatePocket (@PathVariable long idPocket, @RequestBody PocketRequest pocketRequest) throws ApiException {
        PocketResponse response = updatePocket.updatePocket(idPocket,pocketRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{idPocket}")
    public ResponseEntity deletePocket (@PathVariable long idPocket) throws ApiException {
        deletePocket.deletePocket(idPocket);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}

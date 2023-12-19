package com.bidi.pocketapi.controller;


import com.bidi.pocketapi.dto.PocketRequest;
import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.exception.ApiException;
import com.bidi.pocketapi.service.ICreatePocketService;
import com.bidi.pocketapi.service.IDeletePocketService;
import com.bidi.pocketapi.service.IGetAllPocketByIdUser;
import com.bidi.pocketapi.service.IGetAllPocketService;
import com.bidi.pocketapi.service.IGetByIdPocketService;
import com.bidi.pocketapi.service.IUpdatePocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pocket")
@CrossOrigin("*")
public class PocketController {
    private final IGetAllPocketService getAllPocket;
    private final IGetByIdPocketService getByIdPocket;
    private final ICreatePocketService createPocket;
    private final IUpdatePocketService updatePocket;
    private final IDeletePocketService deletePocket;
    private final IGetAllPocketByIdUser getAllByIdUser;


    @GetMapping("/all")
    public ResponseEntity<List<PocketResponse>> getAllPocket
            (@RequestHeader("Authorization") String token) throws ApiException {
        List<PocketResponse> pocketResponse = getAllPocket.getAllPocket();
        return ResponseEntity.status(HttpStatus.OK).body(pocketResponse);
    }

    @GetMapping("/{idUSer}/{idPocket}")
    public ResponseEntity<PocketResponse> getByIdPocket(
            @PathVariable long idPocket,
            @RequestHeader("Authorization") String token) throws ApiException {
        PocketResponse pocketResponse = getByIdPocket.getByIdPocket(idPocket);
        return ResponseEntity.status(HttpStatus.OK).body(pocketResponse);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<List<PocketResponse>> getAllPocketsByIdUser(
            @PathVariable String idUser,
            @RequestHeader("Authorization") String token) throws ApiException {
        List<PocketResponse> response = getAllByIdUser.getAllPocketIdUser(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{idUser}")
    public ResponseEntity<PocketResponse> createPocket(
            @PathVariable String idUser,
            @RequestBody PocketRequest pocketRequest,
            @RequestHeader("Authorization") String token) throws ApiException {
        PocketResponse pocketResponse = createPocket.createPocket(pocketRequest, idUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(pocketResponse);
    }

    @PutMapping("/{idPocket}")
    public ResponseEntity<PocketResponse> updatePocket(
            @PathVariable long idPocket,
            @RequestBody PocketRequest pocketRequest,
            @RequestHeader("Authorization") String token) throws ApiException {
        PocketResponse response = updatePocket.updatePocket(idPocket, pocketRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{idPocket}")
    public ResponseEntity deletePocket(
            @PathVariable long idPocket,
            @RequestHeader("Authorization") String token) throws ApiException {
        deletePocket.deletePocket(idPocket);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}

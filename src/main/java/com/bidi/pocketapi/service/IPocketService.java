package com.bidi.pocketapi.service;

import com.bidi.pocketapi.ApiException;
import com.bidi.pocketapi.dto.PocketRequest;
import com.bidi.pocketapi.dto.PocketResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPocketService{

 List<PocketResponse> getAllPocket() throws ApiException;
 PocketResponse getByNamePocket (String namePocket) throws ApiException;
 PocketResponse createPocket (PocketRequest pocketRequest) throws ApiException;


 PocketResponse updatePocket(long idPcket,PocketRequest pocketRequest) throws ApiException;
 void deletePocket(long idPocket) throws ApiException;



}

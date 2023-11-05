package com.bidi.pocketapi.service;

import com.bidi.pocketapi.dto.PocketRequest;
import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.exception.ApiException;
import org.springframework.stereotype.Service;

@Service
public interface IUpdatePocketService {
    PocketResponse updatePocket(long idPcket, PocketRequest pocketRequest) throws ApiException;
}

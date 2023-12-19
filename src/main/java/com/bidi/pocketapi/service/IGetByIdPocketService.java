package com.bidi.pocketapi.service;

import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.exception.ApiException;
import org.springframework.stereotype.Service;

@Service
public interface IGetByIdPocketService {
    PocketResponse getByIdPocket(long idPocket) throws ApiException;
}

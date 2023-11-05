package com.bidi.pocketapi.service;

import com.bidi.pocketapi.exception.ApiException;
import org.springframework.stereotype.Service;

@Service
public interface IDeletePocketService {
    void deletePocket(long idPocket) throws ApiException;
}

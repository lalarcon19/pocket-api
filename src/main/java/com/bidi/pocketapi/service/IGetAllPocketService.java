package com.bidi.pocketapi.service;

import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.exception.ApiException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IGetAllPocketService {
    List<PocketResponse> getAllPocket() throws ApiException;
}

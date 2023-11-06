package com.bidi.pocketapi.service.impl;

import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.entity.Pocket;
import com.bidi.pocketapi.exception.ApiException;
import com.bidi.pocketapi.repository.IPocketRepository;
import com.bidi.pocketapi.service.IGetAllPocketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class GetAllPocketImpl implements IGetAllPocketService {
    public static final Logger logger = LoggerFactory.getLogger(GetAllPocketImpl.class);
    private final IPocketRepository pocketRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<PocketResponse> getAllPocket() throws ApiException {
        logger.info("Entro al servicio para buscar lista de pockets");
        List<Pocket> listPocket;
        try {
            listPocket = pocketRepository.findAll();
        } catch (Exception e) {
            logger.error("ocurrio un error al hacer la consulta en base de datos", e);
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listPocket.isEmpty()) {
            logger.info("No hay pockets creados");
            return Collections.emptyList();
        }
        logger.info("se obtuvieron bolsillos");
        return listPocket.stream()
                .map(pocket -> mapper.map(pocket, PocketResponse.class))
                .collect(Collectors.toList());
    }

}

package com.bidi.pocketapi.service.impl;

import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.entity.Pocket;
import com.bidi.pocketapi.exception.ApiException;
import com.bidi.pocketapi.repository.IPocketRepository;
import com.bidi.pocketapi.service.IGetByIdPocketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class GetByIdPocketImpl implements IGetByIdPocketService {
    public static final Logger logger = LoggerFactory.getLogger(GetByIdPocketImpl.class);
    private final IPocketRepository pocketRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public PocketResponse getByIdPocket(long idPocket) throws ApiException {
        logger.info("Se conecto al servicio para traer bolsillo por nombre");
        Optional<Pocket> pocketById = pocketRepository.findById(idPocket);
        if (pocketById.isEmpty()) {
            logger.info("No se encontro bolsillo con el nombre");
            throw new ApiException("No se encontro bolsillo", HttpStatus.NOT_FOUND);
        }else {
            logger.info("se encontro esa informacion en base de datos");
            Pocket pocket = pocketById.get();
            PocketResponse pocketResponse = mapper.map(pocket, PocketResponse.class);
            return pocketResponse;
        }
    }

}

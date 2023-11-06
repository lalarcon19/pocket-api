package com.bidi.pocketapi.service.impl;

import com.bidi.pocketapi.dto.PocketRequest;
import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.entity.Pocket;
import com.bidi.pocketapi.exception.ApiException;
import com.bidi.pocketapi.repository.IPocketRepository;
import com.bidi.pocketapi.service.ICreatePocketService;
import com.bidi.pocketapi.service.IUpdatePocketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePocketImpl implements IUpdatePocketService {
    public static final Logger logger = LoggerFactory.getLogger(UpdatePocketImpl.class);
    private final IPocketRepository pocketRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public PocketResponse updatePocket(long idPocket, PocketRequest pocketRequest) throws ApiException {
        logger.info("Entro al servicio para actualizar bolsillo");
        Pocket pocket = pocketRepository.findPocketByIdPocket(idPocket);
        if (pocket != null) {
            pocket.setNamePocket(pocketRequest.getNamePocket());
            pocket.setAmountPocket(pocketRequest.getAmountPocket());
            pocket.setColorPocket(pocketRequest.getColorPocket());
            pocketRepository.saveAndFlush(pocket);
            logger.info("bolsillo actualizado");
            PocketResponse pocketResponse = mapper.map(pocket, PocketResponse.class);
            throw new ApiException("bolsillo actualizado", HttpStatus.OK);
        } else {
            logger.info("no se encontro bolsillo registrado para actualizar");
            throw new ApiException("No se encontro bolsillo", HttpStatus.NOT_FOUND);
        }
    }

}

package com.bidi.pocketapi.service.impl;

import com.bidi.pocketapi.dto.PocketRequest;
import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.entity.Pocket;
import com.bidi.pocketapi.exception.ApiException;
import com.bidi.pocketapi.repository.IPocketRepository;
import com.bidi.pocketapi.service.ICreatePocketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreatePocketImpl implements ICreatePocketService {
    public static final Logger logger = LoggerFactory.getLogger(CreatePocketImpl.class);
    private final IPocketRepository pocketRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public PocketResponse createPocket(PocketRequest pocketRequest, String idUser) throws ApiException {
        logger.info("Entro al servicio para crear bolsillos");
        try {
            Optional<Pocket> pocketByName = pocketRepository.findBynamePocket(pocketRequest.getNamePocket());
            if (pocketByName.isPresent()) {
                logger.info("El bolsillo ya existe");
                throw new ApiException("El bolsillo ya existe", HttpStatus.CONFLICT);
            }
            Pocket pocket = mapper.map(pocketRequest, Pocket.class);
            pocket.setIdUser(idUser);
            pocketRepository.saveAndFlush(pocket);
            logger.info("Se guardo bolsillo en base de datos");
            return mapper.map(pocket, PocketResponse.class);
        }catch (Exception e){
            throw new ApiException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

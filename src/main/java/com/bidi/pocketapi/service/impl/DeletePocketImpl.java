package com.bidi.pocketapi.service.impl;

import com.bidi.pocketapi.entity.Pocket;
import com.bidi.pocketapi.exception.ApiException;
import com.bidi.pocketapi.repository.IPocketRepository;
import com.bidi.pocketapi.service.IDeletePocketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class DeletePocketImpl implements IDeletePocketService {
    public static final Logger logger = LoggerFactory.getLogger(DeletePocketImpl.class);
    private final IPocketRepository pocketRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public void deletePocket(long idPocket) throws ApiException {
        logger.info("Entro al servicio para eliminar bolsillos");
        Optional<Pocket> pocketOptional = pocketRepository.findById(idPocket);
        if (pocketOptional.isPresent()) {
            Pocket pocket = pocketOptional.get();
            pocketRepository.delete(pocket);
            logger.info("bolsillo eliminado");
            throw new ApiException("Bolsillo eliminado", HttpStatus.OK);
        }else {
            logger.info("El bolsillo no existe");
            throw new ApiException("El bolsillo no exite",HttpStatus.NOT_FOUND);
        }
    }
}

package com.bidi.pocketapi.service.impl;

import com.bidi.pocketapi.ApiException;
import com.bidi.pocketapi.dto.PocketRequest;
import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.entity.Pocket;
import com.bidi.pocketapi.repository.IPocketRepository;
import com.bidi.pocketapi.service.IPocketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PocketImpl implements IPocketService {

    public static final Logger logger = LoggerFactory.getLogger(PocketImpl.class);
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
            throw new ApiException("Ocurrio un error", HttpStatus.INTERNAL_SERVER_ERROR);
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

    @Override
    public PocketResponse getByNamePocket(String namePocket) throws ApiException {
        logger.info("Se conecto al servicio para traer bolsillo por nombre");
        PocketRequest.builder().namePocket(namePocket);
        Optional<Pocket> pocketByName = pocketRepository.findBynamePocket(namePocket);
        if (!pocketByName.isPresent()) {
            logger.info("No se encontro bolsillo con el nombre");
            throw new ApiException("No se encontro bolsillo",HttpStatus.NOT_FOUND);
        }
        logger.info("se encontro esa informacion en base de datos");
        Pocket pocket = pocketByName.get();
        PocketResponse pocketResponse = mapper.map(pocket, PocketResponse.class);
        return pocketResponse;
    }

    @Override
    public PocketResponse createPocket(PocketRequest pocketRequest) throws ApiException {
        logger.info("Entro al servicio para crear bolsillos");
        Optional<Pocket> pocketByName = pocketRepository.findBynamePocket(pocketRequest.getNamePocket());
        if (pocketByName.isPresent()) {
            logger.info("El bolsillo ya existe");
            throw new ApiException("El bolsillo ya existe",HttpStatus.CONFLICT);
        }
        Pocket pocket = mapper.map(pocketRequest, Pocket.class);
        pocketRepository.saveAndFlush(pocket);
        logger.info("Se guardo bolsillo en base de datos");
        return mapper.map(pocket, PocketResponse.class);
    }

    @Override
    public PocketResponse updatePocket(long idPocket,PocketRequest pocketRequest) throws ApiException {
        logger.info("Entro al servicio para actualizar bolsillo");
        Optional<Pocket> pocketByname = pocketRepository.findById(idPocket);
        if (pocketByname.isPresent()) {
            Pocket pocket = Pocket.builder()
                    .namePocket(pocketRequest.getNamePocket())
                    .amountPocket(pocketRequest.getAmountPocket())
                    .colorPocket(pocketRequest.getColorPocket())
                    .build();
            pocketRepository.save(pocket);
            PocketResponse pocketResponse = mapper.map(pocket, PocketResponse.class);
            logger.info("bolsillo actualizado");
            throw new ApiException("Se actualizo el bolsillo",HttpStatus.OK);
        } else {
            logger.info("no se encontro bolsillo registrado para actualizar");
            throw new ApiException("No se encontro bolsillo", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deletePocket(long id) throws ApiException {
        logger.info("Entro al servicio para eliminar bolsillos");
        Optional<Pocket> pocketOptional = pocketRepository.findById(id);
        if (pocketOptional.isPresent()) {
            Pocket pocket = pocketOptional.get();
            pocketRepository.delete(pocket);
            logger.info("bolsillo eliminado");
        }else {
            logger.info("El bolsillo no existe");
            throw new ApiException("El bolsillo no exite",HttpStatus.NOT_FOUND);
        }
    }
}

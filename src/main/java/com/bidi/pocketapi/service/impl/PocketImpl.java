package com.bidi.pocketapi.service.impl;

import com.bidi.pocketapi.dto.PocketRequest;
import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.entity.Pocket;
import com.bidi.pocketapi.exception.ApiException;
import com.bidi.pocketapi.repository.IPocketRepository;
import com.bidi.pocketapi.service.*;
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
public class PocketImpl implements IGetAllPocketService, IGetByIdPocketService, IGetAllPocketByIdUser,ICreatePocketService, IUpdatePocketService, IDeletePocketService {

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

    @Override
    public PocketResponse getByIdPocket(long idPocket) throws ApiException {
        logger.info("Se conecto al servicio para traer bolsillo por nombre");
        Optional<Pocket> pocketById = pocketRepository.findById(idPocket);
        if (pocketById.isEmpty()) {
            logger.info("No se encontro bolsillo con el nombre");
            throw new ApiException("No se encontro bolsillo",HttpStatus.NOT_FOUND);
        }else {
            logger.info("se encontro esa informacion en base de datos");
            Pocket pocket = pocketById.get();
            PocketResponse pocketResponse = mapper.map(pocket, PocketResponse.class);
            return pocketResponse;
        }
    }

    @Override
    public List<PocketResponse> getAllPocketIdUser(String idUser) throws ApiException{
        logger.info("Entro al servicio para traer lista de bolsillos por id de usuario");
        List<Pocket> pocketList = pocketRepository.findPocketByIdUser(idUser);
        if (pocketList.isEmpty()){
            logger.info("No se encontro bolsillos registrados con este usuario");
            throw new ApiException("No se encontro bolsillos registrados con este usuario",HttpStatus.NOT_FOUND);
        }else {
            logger.info("Se encontraron bolsillos registrados con el usuario");
            return pocketList.stream()
                    .map(pocket -> mapper.map(pocket, PocketResponse.class))
                    .collect(Collectors.toList());
        }
    }
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

    @Override
    public PocketResponse updatePocket(long idPocket,PocketRequest pocketRequest) throws ApiException {
        logger.info("Entro al servicio para actualizar bolsillo");
        Pocket pocket = pocketRepository.findPocketByIdPocket(idPocket);
        if (pocket != null) {
            pocket.setNamePocket(pocketRequest.getNamePocket());
            pocket.setAmountPocket(pocketRequest.getAmountPocket());
            pocket.setColorPocket(pocketRequest.getColorPocket());
            pocketRepository.saveAndFlush(pocket);
            logger.info("bolsillo actualizado");
            PocketResponse pocketResponse = mapper.map(pocket, PocketResponse.class);
            throw new ApiException("bolsillo actualizado",HttpStatus.OK);
        } else {
            logger.info("no se encontro bolsillo registrado para actualizar");
            throw new ApiException("No se encontro bolsillo", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deletePocket(long idPocket) throws ApiException {
        logger.info("Entro al servicio para eliminar bolsillos");
        Optional<Pocket> pocketOptional = pocketRepository.findById(idPocket);
        if (pocketOptional.isPresent()) {
            Pocket pocket = pocketOptional.get();
            pocketRepository.delete(pocket);
            logger.info("bolsillo eliminado");
            throw new ApiException("Bolsillo eliminado",HttpStatus.OK);
        }else {
            logger.info("El bolsillo no existe");
            throw new ApiException("El bolsillo no exite",HttpStatus.NOT_FOUND);
        }
    }
}

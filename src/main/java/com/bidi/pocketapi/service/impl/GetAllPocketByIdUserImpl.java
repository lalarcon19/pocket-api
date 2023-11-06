package com.bidi.pocketapi.service.impl;

import com.bidi.pocketapi.dto.PocketResponse;
import com.bidi.pocketapi.entity.Pocket;
import com.bidi.pocketapi.exception.ApiException;
import com.bidi.pocketapi.repository.IPocketRepository;
import com.bidi.pocketapi.service.IGetAllPocketByIdUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class GetAllPocketByIdUserImpl implements IGetAllPocketByIdUser {
    public static final Logger logger = LoggerFactory.getLogger(GetAllPocketByIdUserImpl.class);
    private final IPocketRepository pocketRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public List<PocketResponse> getAllPocketIdUser(String idUser) throws ApiException {
        logger.info("Entro al servicio para traer lista de bolsillos por id de usuario");
        List<Pocket> pocketList = pocketRepository.findPocketByIdUser(idUser);
        if (pocketList.isEmpty()){
            logger.info("No se encontro bolsillos registrados con este usuario");
            throw new ApiException("No se encontro bolsillos registrados con este usuario", HttpStatus.NOT_FOUND);
        }else {
            logger.info("Se encontraron bolsillos registrados con el usuario");
            return pocketList.stream()
                    .map(pocket -> mapper.map(pocket, PocketResponse.class))
                    .collect(Collectors.toList());
        }
    }


}

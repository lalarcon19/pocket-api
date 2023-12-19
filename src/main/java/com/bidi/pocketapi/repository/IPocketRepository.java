package com.bidi.pocketapi.repository;

import com.bidi.pocketapi.entity.Pocket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPocketRepository extends JpaRepository<Pocket, Long> {
    Optional<Pocket> findBynamePocket(String namePocket);

    Pocket findPocketByIdPocket(long idPocket);

    List<Pocket> findPocketByIdUser(String idUser);
}

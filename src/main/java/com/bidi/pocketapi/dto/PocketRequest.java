package com.bidi.pocketapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PocketRequest {
    private long idPocket;
    private String namePocket;
    private int amountPocket;
    private String colorPocket;
    private String idUser;
}
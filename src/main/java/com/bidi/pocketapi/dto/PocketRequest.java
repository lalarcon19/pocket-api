package com.bidi.pocketapi.dto;

import lombok.*;


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
}
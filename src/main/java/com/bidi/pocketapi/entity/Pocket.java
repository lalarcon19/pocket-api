package com.bidi.pocketapi.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "BIDI_USERS")
public class Pocket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_POCKET")
    private long idPocket;
    @Column(name = "NAME_POCKET")
    private String namePocket;
    @Column(name = "AMOUNT_POCKET")
    private int amountPocket;
    @Column(name = "COLOR_POCKET")
    private String colorPocket;
    @Column(name = "ID_USER")
    private String idUser;
}

package com.jfloresdev.java.ws.api_books_v1.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Entity //JAVA
@Table(name = "libro")
public class LibroEntity {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 4)
    private Long id;

    @Version
    private Integer version;

    @Column(name ="titulo", nullable = false, length = 260, unique = true)
    private String titulo;

    @Column(name = "resumen", nullable = false, length = 4000)
    private String resumen;

    @Column(name ="nro_paginas", nullable = false, length = 10)
    private Integer nroPaginas;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = "1";

    public LibroEntity(){
    }


}

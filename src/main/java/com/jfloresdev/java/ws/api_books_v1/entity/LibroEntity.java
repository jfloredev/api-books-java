package com.jfloresdev.java.ws.api_books_v1.entity;


import jakarta.persistence.*;


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

    public LibroEntity(Long id, String titulo, String resumen, Integer nroPaginas, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.resumen = resumen;
        this.nroPaginas = nroPaginas;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Integer getNroPaginas() {
        return nroPaginas;
    }

    public void setNroPaginas(Integer nroPaginas) {
        this.nroPaginas = nroPaginas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

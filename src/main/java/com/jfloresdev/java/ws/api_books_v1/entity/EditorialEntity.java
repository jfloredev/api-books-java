package com.jfloresdev.java.ws.api_books_v1.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "EditorialEntity") //JAVA
@Table(name = "EDITORIAL")
public class EditorialEntity {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 4)
    @SequenceGenerator(name = "editorial_id_seq", sequenceName = "editorial_id_seq", allocationSize = 1)
    private Long id;

    @Version
    private Integer version;

    @Column(name ="NOMBRE", nullable = false, length = 260, unique = true)
    private String nombre;

    @Column(name = "RUC", nullable = false, length = 4000)
    private String ruc;

    @Column(name ="servicios", nullable = false, length = 4000)
    private String servicios;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = "1";


    public EditorialEntity(Long id, String nombre, String ruc, String servicios) {
        this.id = id;
        this.nombre = nombre;
        this.ruc = ruc;
        this.servicios = servicios;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

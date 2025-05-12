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


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tipo_id", referencedColumnName = "id", nullable = true)
    private TipoEntity tipo;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = "1";

}

package com.jfloresdev.java.ws.api_books_v1.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TIPO")
@Entity(name = "TipoEntity") //JAVA
public class TipoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(sequenceName = "tipo_id_seq", name = "tipo_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, length = 4)

    private Long id;

    @Column(name = "nombre", nullable = false, length = 260, unique = true)
    private String nombreCorto;

    @Column(name = "nombre_Largo", nullable = false, length = 4000)
    private String nombreLargo;



    @Column(name = "estado", nullable = false, length = 1)
    private String estado = "1";


}

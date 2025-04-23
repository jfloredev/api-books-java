package com.jfloresdev.java.ws.api_books_v1.repository;


import com.jfloresdev.java.ws.api_books_v1.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LibroRepository extends JpaRepository<LibroEntity, Long> {

    //Busqueda de libors
    //JPL (Java Persistence Language)

    @Query("SELECT l FROM LibroEntity l WHERE UPPER(l.titulo) LIKE upper(concat('%', :titulo, '%')) and l.estado = '1'")
    List<LibroEntity> findByTitulo(@Param("titulo") String titulo);


    List<LibroEntity> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT L FROM LibroEntity L WHERE L.estado = '1'")
    List<LibroEntity> findAllByEstado(String number);


    @Transactional
    @Modifying
    @Query(value = "UPDATE libro set estado = '0' where id = :id", nativeQuery = true)
    void updateEstado(@Param("id") Long id);

}

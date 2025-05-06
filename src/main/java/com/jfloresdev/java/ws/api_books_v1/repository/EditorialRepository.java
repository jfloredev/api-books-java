package com.jfloresdev.java.ws.api_books_v1.repository;


import com.jfloresdev.java.ws.api_books_v1.entity.EditorialEntity;
import com.jfloresdev.java.ws.api_books_v1.service.EditorialService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EditorialRepository extends JpaRepository<EditorialEntity, Long> {

    //Busqueda de libors
    //JPL (Java Persistence Language)

    @Query("SELECT p FROM EditorialEntity p WHERE UPPER(p.nombre) LIKE upper(concat('%', :nombre, '%')) and p.estado = '1'")
    List<EditorialEntity> findByTitulo(@Param("nombre") String titulo);


    @Query("SELECT L FROM EditorialEntity L WHERE L.estado = '1'")
    List<EditorialService> findAllByEstado(String number);


    /*
   // List<LibroEntity> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT L FROM LibroEntity L WHERE L.estado = '1'")
    List<LibroEntity> findAllByEstado(String number);



    @Modifying
    @Query(value = "UPDATE libro set estado = '0' where id = :id", nativeQuery = true)
    void updateEstado(@Param("id") Long id);

*/

}

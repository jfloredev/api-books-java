package com.jfloresdev.java.ws.api_books_v1.controller;

import com.jfloresdev.java.ws.api_books_v1.entity.LibroEntity;
import com.jfloresdev.java.ws.api_books_v1.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {


    //DI
    @Autowired
    private  LibroRepository libroRepository;


    @GetMapping("/{id}")
    public LibroEntity findById(@PathVariable Long id){
        return this.libroRepository.findById(id).orElse(null);
    }
        /*
        return new LibroEntity(1L,
                "El arte de la guerra",
                "Un libro sobre estrategia militar",
                300);
    }*/

    @GetMapping("/all")
    public List<LibroEntity> getAll(){
/*
        List<LibroEntity> libros = new ArrayList<>();

        libros.add(new LibroEntity(1L,
                "20 poemas y una cancion desesperada",
                "Un libro de Pablo Neruda",
                200
        ));

        libros.add(new LibroEntity(2L,
                "La ciudad y Los Perros",
                "Mario Vargas llosa ",
                200
        ));
            return libros;
*/
        return libroRepository.findAll();
    }


    @PostMapping
    public LibroEntity create(@RequestBody LibroEntity libro){
        return libroRepository.save(libro);
    }


}





















package com.jfloresdev.java.ws.api_books_v1.controller;

import com.jfloresdev.java.ws.api_books_v1.entity.LibroEntity;
import com.jfloresdev.java.ws.api_books_v1.repository.LibroRepository;
import com.jfloresdev.java.ws.api_books_v1.service.LibroService;
import com.jfloresdev.java.ws.api_books_v1.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    private Map<String, String> map = new HashMap<>();
    private String MSG_INTERNAL_ERROR = "Error interno del servidor";


    //DI
    @Autowired
    private LibroService libroService;


    @GetMapping("/all")
    public ResponseEntity<?> getAll()  {



        try {
            List<LibroEntity> libros = libroService.getAll();
            if (libros.isEmpty()){
                return ResponseEntity.ok(libroService.getAll());
            }else{
                return ResponseEntity.ok(libros);
            }
        }catch (Exception e){
            map.put("message", MSG_INTERNAL_ERROR);
           return ResponseEntity.internalServerError().body(map);
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

       try{

           Optional<LibroEntity> optLibroEntity = libroService.findById(id);
           if (optLibroEntity.isEmpty()) {
                return ResponseEntity.noContent().build();
           }else {
               return ResponseEntity.ok(optLibroEntity);
           }
       }catch (Exception e){
              map.put("message", MSG_INTERNAL_ERROR);
              return ResponseEntity.internalServerError().body(map);
         }

    }



/*

    @GetMapping("/{id}")
    public LibroEntity findById(@PathVariable Long id){
     //   return this.libroRepository.findById(id).orElse(null);
        return  this.libroRepository.findAllByEstado("1").stream()
                .filter(libroEntity -> libroEntity.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public LibroEntity create(@RequestBody LibroEntity libro){

        return libroRepository.save(libro);
    }

    @GetMapping("/by-titulo")
    public List<LibroEntity> findByTitulo(@RequestParam String titulo){

        return  this.libroRepository.findByTitulo(titulo);
    }

    @GetMapping("/by-titulo2")
    public List<LibroEntity> findByTitulo2(@RequestParam String titulo){
        return  this.libroRepository.findByTituloContainingIgnoreCase(titulo);
    }


    @PutMapping("/{id}")
    public LibroEntity update(@PathVariable Long id, @RequestBody LibroEntity libroEntity){


        Optional<LibroEntity> optLibroEntity = libroRepository.findById(id);

        if (optLibroEntity.isPresent()){

            LibroEntity oLibroEntity = optLibroEntity.get();
            oLibroEntity.setId(id);
            oLibroEntity.setTitulo(libroEntity.getTitulo());
            oLibroEntity.setResumen(libroEntity.getResumen());
            oLibroEntity.setNroPaginas(libroEntity.getNroPaginas());

            return this.libroRepository.save(oLibroEntity);

        }

        return this.libroRepository.save(libroEntity);
    }

    @PatchMapping("/{id}")
    public Object updateResumen(@PathVariable Long id, @RequestBody LibroEntity libroEntity){



        Optional<LibroEntity> optLibroEntity = libroRepository.findById(id);

        if (optLibroEntity.isEmpty()){
            Map<String, String> map = new HashMap<>();
            map.put("message", "No existe Libro con el id" + id);
            return map;
        }

        LibroEntity oLibroEntity = optLibroEntity.get();
        oLibroEntity.setId(id);
        oLibroEntity.setResumen(libroEntity.getResumen());

        return this.libroRepository.save(oLibroEntity);
    }


        @DeleteMapping("/{id}")
        public Object delete(@PathVariable Long id){

            Map<String , String> map= new HashMap<>();
            Optional<LibroEntity> optLibroEntity = libroRepository.findById(id);

            if (optLibroEntity.isEmpty()){
                map.put("messsage", "No existe libro con el id = "+ id);
                return map;
            }

            libroRepository.updateEstado(id);
            map.put("message", "Libro eliminado con exito");
            return map;
        }

        */
}





















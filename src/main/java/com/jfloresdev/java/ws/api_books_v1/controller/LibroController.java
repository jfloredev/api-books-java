package com.jfloresdev.java.ws.api_books_v1.controller;

import com.jfloresdev.java.ws.api_books_v1.entity.LibroEntity;
import com.jfloresdev.java.ws.api_books_v1.repository.LibroRepository;
import com.jfloresdev.java.ws.api_books_v1.service.LibroService;
import com.jfloresdev.java.ws.api_books_v1.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static java.util.Objects.isNull;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    private Map<String, String> map = new HashMap<>();
    private final String MSG_INTERNAL_ERROR = "Error interno del servidor";
    private final String MSG_BAD_REQUEST =  "Se ha producido un error interno";


    //DI
    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }


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


    @GetMapping("/by-titulo")
    public ResponseEntity<?> findByTitulo(@RequestParam String titulo){
        try {
            List<LibroEntity> libros = this.libroService.findByTitulo(titulo);
            if (libros.isEmpty()){
                map.put("message", "No se encontraron libros con el titulo: " + titulo);
                return ResponseEntity.ok(map);
            }else{
                return ResponseEntity.ok(libros);
            }

    } catch (ServiceException e) {
            map.put("message", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }
}


    @PostMapping
    public ResponseEntity<?> create(@RequestBody LibroEntity libroEntity) {

        try{
            LibroEntity oLibroEntity = this.libroService.save(libroEntity);
            if (isNull(oLibroEntity)){
                map.put("alert", MSG_INTERNAL_ERROR);
                return ResponseEntity.badRequest().body(map);
            }else {
                return new ResponseEntity<>(oLibroEntity,HttpStatus.CREATED);
            }
        }catch (Exception e){
            map.put("errpr", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }


    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LibroEntity libroEntity){
        try {
            LibroEntity oLibroEntity = this.libroService.update(id, libroEntity);
            if (isNull(oLibroEntity)){
                map.put("ALERTA", MSG_BAD_REQUEST);
                return ResponseEntity.badRequest().body(map);
            }else {
                return ResponseEntity.ok(null);
              //  return new ResponseEntity<>(oLibroEntity, HttpStatus.OK);
            }
        }catch (Exception e){
            map.put("error", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateResumen(@PathVariable Long id, @RequestBody LibroEntity libroEntity) {

        try {
            LibroEntity oLibroEntity = this.libroService.updateResumen(id, libroEntity.getResumen());
            if (isNull(oLibroEntity)) {
                map.put("alerta", MSG_BAD_REQUEST);
                return ResponseEntity.badRequest().body(map);
            } else {
                return ResponseEntity.ok(oLibroEntity);
            }
        } catch (ServiceException e) {
            map.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(map);
        } catch (Exception e) {
            map.put("error", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

       try {
           this.libroService.delete(id);
           return ResponseEntity.ok().build();
         }catch (ServiceException e){
            map.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(map);
       }catch (Exception e){
            map.put("error", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }
    }

}





















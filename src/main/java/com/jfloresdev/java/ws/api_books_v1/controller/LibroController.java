package com.jfloresdev.java.ws.api_books_v1.controller;

import com.jfloresdev.java.ws.api_books_v1.entity.LibroEntity;
import com.jfloresdev.java.ws.api_books_v1.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {


    //DI
    @Autowired
    private  LibroRepository libroRepository;


    @GetMapping("/{id}")
    public LibroEntity findById(@PathVariable Long id){
     //   return this.libroRepository.findById(id).orElse(null);
        return  this.libroRepository.findAllByEstado("1").stream()
                .filter(libroEntity -> libroEntity.getId().equals(id))
                .findFirst()
                .orElse(null);
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
        //return libroRepository.findAll();
        return libroRepository.findAllByEstado("1");
    }


    //Save
    @PostMapping
    public LibroEntity create(@RequestBody LibroEntity libro){
        //libro.setId("1");
        return libroRepository.save(libro);
    }

    //Para buscar por titulo
    @GetMapping("/by-titulo")
    public List<LibroEntity> findByTitulo(@RequestParam String titulo){

        return  this.libroRepository.findByTitulo(titulo);
    }

    @GetMapping("/by-titulo2")
    public List<LibroEntity> findByTitulo2(@RequestParam String titulo){
        return  this.libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    //Para actualizar
    @PutMapping("/{id}")
    public LibroEntity update(@PathVariable Long id, @RequestBody LibroEntity libroEntity){

     /*   LibroEntity oLibroEntity = libroRepository.findById(id)
                .orElseThrow(
                        ()-> new RuntimeException("No existe libro con eel "+id
                        ));*/

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

    //Actualizacion en parcial
    @PatchMapping("/{id}")
    public Object updateResumen(@PathVariable Long id, @RequestBody LibroEntity libroEntity){

      /*  LibroEntity oLibroEntity = libroRepository.findById(id)
                .orElseThrow(
                        ()-> new RuntimeException("No existe libro con eel "+id
                        ));
*/

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

/*
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        libroRepository.deleteById(id); //Delete Fisico
    }
*/

    //Delete logico
        @DeleteMapping("/{id}")
        public Object delete(@PathVariable Long id){

            Map<String , String> map= new HashMap<>();
            Optional<LibroEntity> optLibroEntity = libroRepository.findById(id);

            if (optLibroEntity.isEmpty()){
                map.put("messsage", "No existe libro con el id = "+ id);
                return map;
            }
            /*
            LibroEntity oLibroEntity = optLibroEntity.get();
            oLibroEntity.setId(id);
            oLibroEntity.setEstado("0");*/

            libroRepository.updateEstado(id);
            map.put("message", "Libro eliminado con exito");
            return map;
        }
}





















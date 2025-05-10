package com.jfloresdev.java.ws.api_books_v1.controller;


import com.jfloresdev.java.ws.api_books_v1.entity.EditorialEntity;
import com.jfloresdev.java.ws.api_books_v1.service.EditorialService;
import com.jfloresdev.java.ws.api_books_v1.service.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/v1/editoriales")
public class EditorialController {

    private Map<String, String> map = new HashMap<>();
    private final String MSG_INTERNAL_ERROR = "Error interno del servidor";
    private final String MSG_BAD_REQUEST = "Se ha producido un error interno";

    private final EditorialService editorialService;

    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            List<EditorialEntity> editoriales = editorialService.getAll();
            if (editoriales.isEmpty()) {
                return ResponseEntity.ok(editorialService.getAll());
            } else {
                return ResponseEntity.ok(editoriales);
            }
        } catch (Exception e) {
            map.put("message", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EditorialEntity editorialEntity) {
        try {
            EditorialEntity oEditorialEntity = this.editorialService.save(editorialEntity);
            if (isNull(oEditorialEntity)) {
                map.put("alert", MSG_INTERNAL_ERROR);
                return ResponseEntity.badRequest().body(map);
            } else {
                return new ResponseEntity<>(oEditorialEntity, HttpStatus.CREATED);
            }
        } catch (ServiceException e) {
            map.put("message", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }
    }

    @GetMapping("/by-nombre")
    public ResponseEntity<?> findByNombre(@RequestParam String nombre) {

        try {
            List<EditorialEntity> editorial = this.editorialService.findByNombre(nombre);
            if (editorial.isEmpty()) {
                map.put("Message", "No se encontraron libros con el nombre" + nombre);
                return ResponseEntity.ok(map);
            } else {
                return ResponseEntity.ok(editorial);
            }
        } catch (ServiceException e) {
            map.put("Message", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        try {
            Optional<EditorialEntity> optEditorialEntity = editorialService.findById(id);
            if (optEditorialEntity.isEmpty()){
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.ok(optEditorialEntity);
            }
        } catch (Exception e) {
            map.put("Message", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EditorialEntity editorialEntity) {
        try {
            EditorialEntity oEditorialEntity = this.editorialService.update(id, editorialEntity);
            if (isNull(oEditorialEntity)) {
                map.put("alert", MSG_INTERNAL_ERROR);
                return ResponseEntity.badRequest().body(map);
            } else {
                return ResponseEntity.ok(oEditorialEntity);
            }
        } catch (ServiceException e) {
            map.put("Message", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }
    }

}

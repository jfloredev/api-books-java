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

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/v1/editoriales")
public class EditorialController {

    private Map<String, String> map = new HashMap<>();
    private final String MSG_INTERNAL_ERROR = "Error interno del servidor";
    private final String MSG_BAD_REQUEST =  "Se ha producido un error interno";

    private final EditorialService editorialService;

    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            List<EditorialService> editoriales = editorialService.getAll();
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
    public ResponseEntity<?> create(@RequestBody EditorialEntity editorialEntity){
        try {
            EditorialEntity oEditorialEntity = this.editorialService.save(editorialEntity);
            if (isNull(oEditorialEntity)){
                map.put("alert", MSG_INTERNAL_ERROR);
                return ResponseEntity.badRequest().body(map);
            }else{
                return new ResponseEntity<>(oEditorialEntity, HttpStatus.CREATED);
            }
        } catch (ServiceException e) {
            map.put("message", MSG_INTERNAL_ERROR);
            return ResponseEntity.internalServerError().body(map);
        }
    }
}

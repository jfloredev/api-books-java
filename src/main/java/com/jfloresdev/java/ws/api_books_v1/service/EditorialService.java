package com.jfloresdev.java.ws.api_books_v1.service;

import com.jfloresdev.java.ws.api_books_v1.entity.EditorialEntity;

import java.util.List;
import java.util.Optional;

public interface EditorialService {

    List<EditorialEntity> getAll() throws ServiceException;
    List<EditorialEntity> findByNombre(String nombre) throws ServiceException;
    Optional<EditorialEntity> findById(Long id) throws ServiceException;
    EditorialEntity save(EditorialEntity editorialEntity) throws ServiceException;
    EditorialEntity update(Long id, EditorialEntity editorialEntity) throws ServiceException;

    void delete(Long id) throws ServiceException;

}

package com.jfloresdev.java.ws.api_books_v1.service;

import com.jfloresdev.java.ws.api_books_v1.entity.EditorialEntity;

import java.util.List;
import java.util.Optional;

public interface EditorialService {

    List<EditorialService> getAll() throws ServiceException;
    List<EditorialService> findByNombre(String titulo) throws ServiceException;
    Optional<EditorialService> findById(Long id) throws ServiceException;
    EditorialEntity save(EditorialEntity editorialEntity) throws ServiceException;
    EditorialService update(Long id, EditorialService editorialService) throws ServiceException;

    void delete(Long id) throws ServiceException;

}

package com.jfloresdev.java.ws.api_books_v1.service;

import com.jfloresdev.java.ws.api_books_v1.entity.LibroEntity;
import com.jfloresdev.java.ws.api_books_v1.repository.LibroRepository;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<LibroEntity> getAll() throws ServiceException;
    List<LibroEntity> findByTitulo(String titulo) throws ServiceException;
    Optional<LibroEntity> findById(Long id) throws ServiceException;
    LibroEntity save(LibroEntity libroEntity) throws ServiceException;
    LibroEntity update(Long id, LibroEntity libroEntity) throws ServiceException;
    LibroEntity updateResumen(Long id, String resumen) throws ServiceException;

    void delete(Long id) throws ServiceException;

}

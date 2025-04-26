package com.jfloresdev.java.ws.api_books_v1.service;

import com.jfloresdev.java.ws.api_books_v1.entity.LibroEntity;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<LibroEntity> getAll() throws ServiceException;
    Optional<LibroEntity> findById(Long id) throws ServiceException;
}

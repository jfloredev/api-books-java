package com.jfloresdev.java.ws.api_books_v1.service;

import com.jfloresdev.java.ws.api_books_v1.entity.LibroEntity;
import com.jfloresdev.java.ws.api_books_v1.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LibroServiceImpl implements LibroService {


    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }


    @Override
    public List<LibroEntity> getAll() throws ServiceException {
        return libroRepository.findAllByEstado("1");
    }

    @Override
    public Optional<LibroEntity> findById(Long id) throws ServiceException {
        this.libroRepository.findById(id).orElse(null);
        return this.libroRepository.findAllByEstado("1").stream()
                .filter(libroEntity -> libroEntity.getId().equals(id))
                .findFirst();
    }


}

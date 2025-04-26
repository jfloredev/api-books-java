package com.jfloresdev.java.ws.api_books_v1.service;

import com.jfloresdev.java.ws.api_books_v1.entity.LibroEntity;
import com.jfloresdev.java.ws.api_books_v1.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class LibroServiceImpl implements LibroService {


    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }


    @Override
    public List<LibroEntity> getAll() throws ServiceException {
        try {
            return this.libroRepository.findAllByEstado("1");
        } catch (Exception e) {
            throw new ServiceException("Error al obtener los libros", e);
        }
    }

    @Override
    public List<LibroEntity> findByTitulo(String titulo) throws ServiceException {
    try{
        String val = (titulo == null) ? "" : titulo.trim();
        return this.libroRepository.findByTitulo(val);

    } catch (Exception e) {
        throw new ServiceException("Error al obtener los libros", e);
    }
    }

    @Override
    public Optional<LibroEntity> findById(Long id) throws ServiceException {

        try{


        this.libroRepository.findById(id).orElse(null);
        return this.libroRepository.findAllByEstado("1").stream()
                .filter(libroEntity -> libroEntity.getId().equals(id))
                .findFirst();
    } catch (Exception e) {
        throw new ServiceException("Error al obtener los libros", e);
    }}

    @Override
    public LibroEntity save(LibroEntity libroEntity) throws ServiceException {
        try{
        return libroRepository.save(libroEntity);
    } catch (Exception e) {
        throw new ServiceException("Error al obtener los libros", e);
    }
    }

    @Override
    public LibroEntity update(Long id, LibroEntity libroEntity) throws ServiceException {

        try{
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
    } catch (Exception e) {
        throw new ServiceException("Error al obtener los libros", e);
    }
    }

    @Override
    public LibroEntity updateResumen(Long id, String resumen) throws ServiceException {

        try {


        Optional<LibroEntity> optLibroEntity = libroRepository.findById(id);

        if (optLibroEntity.isEmpty()){
            throw new ServiceException(String.format("No existe lubro con el id",id));
        }
        LibroEntity oLlibroEntity = optLibroEntity.get();
        oLlibroEntity.setResumen(resumen);
        return this.libroRepository.save(oLlibroEntity);
    } catch (Exception e) {
        throw new ServiceException("Error al obtener los libros", e);
    }
    }

    @Transactional
    @Override
    public void delete(Long id) throws ServiceException {

    try{
        Optional<LibroEntity> optLibroEntity = libroRepository.findById(id);

        if (optLibroEntity.isEmpty()){
            throw  new ServiceException(String.format("No existe libro con el id", id));
        }
        libroRepository.updateEstado(id);
    } catch (Exception e) {
        throw new ServiceException("Error al obtener los libros", e);
    }
    }
}

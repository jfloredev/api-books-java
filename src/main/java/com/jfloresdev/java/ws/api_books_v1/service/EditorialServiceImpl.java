package com.jfloresdev.java.ws.api_books_v1.service;

import com.jfloresdev.java.ws.api_books_v1.entity.EditorialEntity;
import com.jfloresdev.java.ws.api_books_v1.repository.EditorialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EditorialServiceImpl implements EditorialService {

    private final EditorialRepository editorialRepository;

    public EditorialServiceImpl(EditorialRepository editorialRespository) {
        this.editorialRepository = editorialRespository;
    }

    @Override
    public List<EditorialService> getAll() throws ServiceException {
        try{
            return this.editorialRepository.findAllByEstado("1");
        }catch (Exception e){
            throw new ServiceException("Error al obtener los libros", e);
        }
    }

    @Override
    public List<EditorialService> findByNombre(String titulo) throws ServiceException {

        return List.of();
    }

    @Override
    public Optional<EditorialService> findById(Long id) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public EditorialEntity save(EditorialEntity editorialEntity) throws ServiceException {
        try{
            return editorialRepository.save(editorialEntity);
        }catch (Exception e){
            throw new ServiceException("error al guardar los libros", e);
        }
    }


    @Override
    public EditorialService update(Long id, EditorialService editorialService) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Long id) throws ServiceException {

    }


}

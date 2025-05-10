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
    public List<EditorialEntity> getAll() throws ServiceException {
        try{
           return editorialRepository.findAllByEstado("1");
        }catch (Exception e){
            throw new ServiceException("Error al obtener los libros", e);
        }
    }

    @Override
    public List<EditorialEntity> findByNombre(String nombre) throws ServiceException {

        try {
            String val = (nombre == null) ? " " : nombre.trim();
            return this.editorialRepository.findByNombre(val);
        } catch (Exception e) {
            throw new ServiceException("Error al obtener los libros", e);
        }
    }

    @Override
    public Optional<EditorialEntity> findById(Long id) throws ServiceException {

        try{
            this.editorialRepository.findById(id).orElse(null);
            return this.editorialRepository.findAllByEstado("1").stream()
                    .filter(editorialEntity -> editorialEntity.getId().equals(id))
                    .findFirst();
        }catch (Exception e){
            throw new ServiceException("ERrro al obtener el libro", e);
        }

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
    public EditorialEntity update(Long id, EditorialEntity editorialEntity) throws ServiceException {

        try{
            Optional<EditorialEntity> optEditorialEntity = editorialRepository.findById(id);

            if (optEditorialEntity.isPresent()){

                EditorialEntity oEditorialEntity = optEditorialEntity.get();
                oEditorialEntity.setId(id);
                oEditorialEntity.setNombre(editorialEntity.getNombre());
                oEditorialEntity.setRuc(editorialEntity.getRuc());
                oEditorialEntity.setServicios(editorialEntity.getServicios());

                return this.editorialRepository.save(oEditorialEntity);
            }

            return this.editorialRepository.save(editorialEntity);

        }catch (Exception e){
            throw new ServiceException("error al obtener los editoriales", e);
        }

    }


    @Override
    public void delete(Long id) throws ServiceException {
    }


}

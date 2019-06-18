package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.exceptions.RestApiException;
import com.crossover.jns.JnsFilmes.presentation.dto.RespostaSimplesDto;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Base class for REST API Controllers with common CRUD methods for a single TEntity identified by an ID of type IId.
 */
public abstract class ApiEntityControllerBase<TEntity, TId, TRepository extends JpaRepository<TEntity, TId>, TDto> {

    protected abstract EntityServiceBase<TEntity, TId, TRepository> getEntityService();

    protected abstract TDto convertToDto(TEntity entity);

    protected abstract TEntity convertToEntity(TDto dto) throws PersistenceException, NotFoundException, InvalidDtoException;

    protected abstract List<TDto> findAllDto() throws PersistenceException;

    protected abstract String getEntityName();

    protected abstract TId getDtoId(TDto dto);

    /**
     * Get all entities
     */
    @GetMapping({"", "/"})
    public List<TDto> getAll() throws RestApiException {
        try {
            return findAllDto();
        } catch (PersistenceException e) {
            throw new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Persistence error: " + e.getMessage());
        }
    }

    /**
     * Get entity by ID
     */
    @GetMapping("/{id}")
    public TDto get(@PathVariable TId id) throws RestApiException {
        if (id == null)
            throw new RestApiException(HttpStatus.BAD_REQUEST, "Invalid argument(s) - ID: can't be empty");

        TEntity entity;
        try {
            entity = getEntityService().findById(id);
        } catch (NotFoundException e) {
            throw new RestApiException(HttpStatus.NOT_FOUND, "Couldn't find " + getEntityName() + " with ID " + id);
        } catch (PersistenceException e) {
            throw new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Persistence error: " + e.getMessage());
        }

        return convertToDto(entity);
    }

    /**
     * Add new entity
     */
    @PostMapping({"", "/"})
    public TDto post(@RequestBody @Valid TDto dto) throws RestApiException {
        if (getDtoId(dto) != null)
            throw new RestApiException(HttpStatus.BAD_REQUEST, "Invalid argument(s) - ID: should be empty");

        try {
            TEntity entity = convertToEntity(dto);
            entity = getEntityService().save(entity);
            return convertToDto(entity);
        } catch (InvalidDtoException e) {
            throw new RestApiException(HttpStatus.BAD_REQUEST, "Invalid argument(s) - " + e.getField() + ": " + e.getMessage());
        } catch (NotFoundException e) {
            throw new RestApiException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (PersistenceException e) {
            throw new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Persistence error: " + e.getMessage());
        }
    }

    /**
     * Edit/replace existing entity
     */
    @PutMapping("/{id}")
    public TDto put(@RequestBody @Valid TDto dto,
                    @PathVariable TId id) throws RestApiException {
        if (getDtoId(dto) == null || !getDtoId(dto).equals(id))
            throw new RestApiException(HttpStatus.BAD_REQUEST, "Invalid argument(s) - ID: can't be empty and must match the URL");

        try {
            if (!getEntityService().existsById(id))
                throw new RestApiException(HttpStatus.NOT_FOUND, "Couldn't find " + getEntityName() + " with the ID " + id);
            TEntity entity = convertToEntity(dto);
            entity = getEntityService().save(entity);
            return convertToDto(entity);
        } catch (InvalidDtoException e) {
            throw new RestApiException(HttpStatus.BAD_REQUEST, "Invalid argument(s) - " + e.getField() + ": " + e.getMessage());
        } catch (NotFoundException e) {
            throw new RestApiException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (PersistenceException e) {
            throw new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Persistence error: " + e.getMessage());
        }
    }

    /**
     * Delete existing entity
     */
    @DeleteMapping("/{id}")
    public RespostaSimplesDto delete(@PathVariable TId id) throws RestApiException {
        if (id == null)
            throw new RestApiException(HttpStatus.BAD_REQUEST, "Invalid argument(s) - ID: can't be empty");

        try {
            getEntityService().deleteById(id);
        } catch (NotFoundException e) {
            throw new RestApiException(HttpStatus.NOT_FOUND, "Couldn't find " + getEntityName() + " with the ID " + id);
        } catch (PersistenceException e) {
            throw new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Persistence error: " + e.getMessage());
        }

        return new RespostaSimplesDto(HttpStatus.OK, getEntityName() + " with ID '" + id + "' was deleted");
    }

}

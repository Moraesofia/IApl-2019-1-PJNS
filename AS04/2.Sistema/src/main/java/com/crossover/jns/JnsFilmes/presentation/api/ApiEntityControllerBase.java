package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.presentation.dto.RespostaSimplesDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

public abstract class ApiEntityControllerBase<TEntity, TId, TRepository extends JpaRepository<TEntity, TId>, TDto> {

    protected abstract EntityServiceBase<TEntity, TId, TRepository> getEntityService();

    protected abstract TDto convertToDto(TEntity entity);

    protected abstract TEntity convertToEntity(TDto dto);

    protected abstract TId getDtoId(TDto dto);

    protected abstract List<TDto> findAllDto();

    protected abstract String getEntityName();

    /**
     * Get all entities
     */
    @GetMapping({"", "/"})
    public List<TDto> getAll() {
        return findAllDto();
    }

    /**
     * Get entity by ID
     */
    @GetMapping("/{id}")
    public TDto get(@PathVariable TId id) {
        if (id == null)
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        TEntity entity = getEntityService().findById(id);
        if (entity == null)
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);

        return convertToDto(entity);
    }

    /**
     * Add new entity
     */
    @PostMapping({"", "/"})
    public TDto post(@RequestBody @Valid TDto dto) {
        if (getDtoId(dto) != null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "A new " + getEntityName() + " shouldn't have an ID");

        TEntity entity = convertToEntity(dto);
        entity = getEntityService().save(entity);
        return convertToDto(entity);
    }

    /**
     * Edit/replace existing entity
     */
    @PutMapping("/{id}")
    public TDto put(@RequestBody @Valid TDto dto,
                    @PathVariable TId id) {
        if (getDtoId(dto) == null || !getDtoId(dto).equals(id))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, getEntityName() + " and path should have the same ID");
        TEntity entity = getEntityService().findById(getDtoId(dto));
        if (entity == null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Couldn't find " + getEntityName() + " with the given ID");

        entity = convertToEntity(dto);
        entity = getEntityService().save(entity);
        return convertToDto(entity);
    }

    /**
     * Delete existing entity
     */
    @DeleteMapping("/{id}")
    public RespostaSimplesDto delete(@PathVariable TId id) {
        if (id == null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "The " + getEntityName() + "'s ID should be specified");
        TEntity award = getEntityService().findById(id);
        if (award == null)
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Couldn't find " + getEntityName() + " with the given ID");

        getEntityService().deleteById(id);
        return new RespostaSimplesDto(HttpStatus.OK, getEntityName() + " was deleted");
    }
}

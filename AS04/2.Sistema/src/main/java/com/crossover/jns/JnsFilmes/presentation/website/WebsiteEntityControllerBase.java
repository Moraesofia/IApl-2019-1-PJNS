package com.crossover.jns.JnsFilmes.presentation.website;

import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.exceptions.WebsiteException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public abstract class WebsiteEntityControllerBase<TEntity, TId, TRepository extends JpaRepository<TEntity, TId>, TDto> {

    protected abstract EntityServiceBase<TEntity, TId, TRepository> getEntityService();

    protected abstract TDto convertToDto(TEntity entity);

    protected abstract TEntity convertToEntity(TDto dto) throws PersistenceException, InvalidDtoException;

    protected abstract List<TDto> findAllDto() throws PersistenceException;

    protected abstract TId getDtoId(TDto dto);

    protected abstract Long getEntityId(TEntity entity);

    protected abstract String getEntityName();

    protected abstract String getControllerBasePath();

    protected abstract TEntity getNewEntity();
    
    protected abstract TDto getNewDto();

    // Retrieves all saved entities as DTOs
    @GetMapping({"", "/"})
    public String listAll(Model model) throws WebsiteException {
        try {
            model.addAttribute(getDtosName(), findAllDto());
            return getTemplatesBaseName();
        } catch (PersistenceException e) {
            throw WebsiteException.Internal(e);
        }
    }

    // Edit an entity by its ID
    @GetMapping("/edit/{id}")
    public String editEntity(@PathVariable TId id, Model model) throws WebsiteException {
        try {
            TEntity entity = getEntityService().findById(id);
            TDto dto = convertToDto(entity);
            model.addAttribute(getDtoName(), dto);
            return getTemplatesEditName();
        } catch (NotFoundException e) {
            throw WebsiteException.NotFound();
        } catch (PersistenceException e) {
            throw WebsiteException.Internal(e);
        }
    }

    // Delete an entity by its ID
    @GetMapping("/delete/{id}")
    public String deleteEntity(@PathVariable TId id) throws WebsiteException {
        try {
            getEntityService().deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            return "redirect:" + getControllerBasePath() + "?cantDeleteBecauseItsUsed";
        } catch (NotFoundException e) {
            throw WebsiteException.NotFound();
        } catch (PersistenceException e) {
            throw WebsiteException.Internal(e);
        }
        return "redirect:" + getControllerBasePath() + "?deleted";
    }

    // Create new entity action
    @GetMapping("/add")
    public String createEntity(Model model) {
        model.addAttribute(getDtoName(), getNewDto());
        return getTemplatesEditName();
    }

    // Save action from the form. It either creates or updates an entity
    @PostMapping("/save")
    public String saveEntityPost(@Valid TDto dto, BindingResult bindingResult) throws WebsiteException {
        if (bindingResult.hasErrors() || !validateEntity(dto, bindingResult)) {
            return getTemplatesEditName();
        }

        Long oldId, newId;
        try {
            TEntity entity = convertToEntity(dto);
            oldId = getEntityId(entity);
            entity = getEntityService().save(entity);
            newId = getEntityId(entity);
        } catch (InvalidDtoException e) {
            rejectBindingValue(bindingResult, e.getField(), e.getMessage());
            return getTemplatesEditName();
        } catch (Exception e) {
            throw WebsiteException.Internal(e);
        }

        if (Objects.equals(oldId, newId)) {
            return "redirect:" + getControllerBasePath() + "?updated";
        } else {
            return "redirect:" + getControllerBasePath() + "?created";
        }
    }

    protected boolean validateEntity(@Valid TDto dto, BindingResult bindingResult) {
        return true;
    }

    protected String getTemplatesBaseName() {
        return getEntityName().toLowerCase() + "s";
    }

    protected String getTemplatesEditName() {
        return getTemplatesBaseName() + "-edit";
    }

    protected String getDtoName() {
        return getEntityName().toLowerCase() + "Dto";
    }

    protected String getDtosName() {
        return getEntityName().toLowerCase() + "s";
    }

    protected void rejectBindingValue(BindingResult bindingResult, String field, String reason) {
        // TODO: How to internationalize those custom validation messages?
        bindingResult.rejectValue(field, "error." + getDtoName(), reason);
    }
}

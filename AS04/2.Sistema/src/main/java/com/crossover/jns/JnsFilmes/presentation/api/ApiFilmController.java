package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.business.service.FilmService;
import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.data.repository.FilmRepository;
import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.exceptions.RestApiException;
import com.crossover.jns.JnsFilmes.presentation.dto.AwardDto;
import com.crossover.jns.JnsFilmes.presentation.dto.FilmDto;
import com.crossover.jns.JnsFilmes.presentation.dto.RespostaSimplesDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiFilmController extends ApiEntityControllerBase<Film, Long, FilmRepository, FilmDto> {

    @Autowired
    private FilmService filmService;

    @Autowired
    private PersonService personService;

    @Override
    protected EntityServiceBase<Film, Long, FilmRepository> getEntityService() {
        return filmService;
    }

    @Override
    protected FilmDto convertToDto(Film film) {
        return FilmDto.fromFilm(film);
    }

    @Override
    protected Film convertToEntity(FilmDto filmDto) throws PersistenceException, InvalidDtoException {
        return filmDto.toFilm(personService);
    }

    @Override
    protected Long getDtoId(FilmDto filmDto) {
        return filmDto.getId();
    }

    @Override
    protected List<FilmDto> findAllDto() throws PersistenceException {
        return filmService.findAllDto();
    }

    @Override
    protected String getEntityName() {
        return Film.class.getSimpleName();
    }

    /**
     * Get all films
     */
    @ApiOperation(value = "Get all", notes = "Gets all films")
    @GetMapping("films")
    @Override
    public List<FilmDto> getAll() throws RestApiException {
        return super.getAll();
    }

    /**
     * Get film by ID
     */
    @ApiOperation(value = "Get by ID", notes = "Gets an existing film by its ID")
    @GetMapping("film/{id}")
    @Override
    public FilmDto getById(Long id) throws RestApiException {
        return super.getById(id);
    }

    /**
     * Add new film
     */
    @ApiOperation(value = "Add new", notes = "Add a new film. The new film's ID must be omitted or null.")
    @PostMapping("film")
    @Override
    public FilmDto post(@Valid FilmDto filmDto) throws RestApiException {
        return super.post(filmDto);
    }

    /**
     * Edit/replace existing film
     */
    @ApiOperation(value = "Edit", notes = "Edits (replaces) the film with the given ID")
    @PutMapping("film/{id}")
    @Override
    public FilmDto put(@Valid FilmDto filmDto, Long id) throws RestApiException {
        return super.put(filmDto, id);
    }

    /**
     * Delete existing film
     */
    @ApiOperation(value = "Delete", notes = "Deletes the film with the given ID")
    @DeleteMapping("film/{id}")
    @Override
    public RespostaSimplesDto delete(Long id) throws RestApiException {
        return super.delete(id);
    }
}

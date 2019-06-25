package com.crossover.jns.JnsFilmes.presentation.website;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.business.service.FilmService;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.data.repository.FilmRepository;
import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.presentation.dto.FilmDto;
import com.crossover.jns.JnsFilmes.presentation.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController extends WebsiteEntityControllerBase<Film, Long, FilmRepository, FilmDto> {

    @Autowired
    private FilmService filmService;

    @Autowired
    private PersonService personService;

    @Override
    protected String getControllerBasePath() {
        return "/films";
    }

    @Override
    protected EntityServiceBase<Film, Long, FilmRepository> getEntityService() {
        return filmService;
    }

    @Override
    protected FilmDto convertToDto(Film film) {
        return FilmDto.fromFilm(film);
    }

    @Override
    protected Film convertToEntity(FilmDto filmDto) throws InvalidDtoException, PersistenceException {
        return filmDto.toFilm(personService);
    }

    @Override
    protected List<FilmDto> findAllDto() throws PersistenceException {
        return filmService.findAllDto();
    }

    @Override
    protected Long getDtoId(FilmDto filmDto) {
        return filmDto.getId();
    }

    @Override
    protected Long getEntityId(Film film) {
        return film.getId();
    }

    @Override
    protected String getEntityName() {
        return Film.class.getSimpleName();
    }

    @Override
    protected Film getNewEntity() {
        return new Film();
    }

    @Override
    protected FilmDto getNewDto() {
        return new FilmDto();
    }

}

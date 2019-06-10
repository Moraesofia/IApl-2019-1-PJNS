package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.business.service.FilmService;
import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.data.repository.FilmRepository;
import com.crossover.jns.JnsFilmes.presentation.dto.FilmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/films")
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
    protected Film convertToEntity(FilmDto filmDto) {
        return filmDto.toFilm(personService);
    }

    @Override
    protected Long getDtoId(FilmDto filmDto) {
        return filmDto.getId();
    }

    @Override
    protected List<FilmDto> findAllDto() {
        return filmService.findAllDto();
    }

    @Override
    protected String getEntityName() {
        return Film.class.getSimpleName();
    }
}

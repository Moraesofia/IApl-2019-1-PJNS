package com.crossover.jns.JnsFilmes.presentation.dto;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FilmDto {

    @ApiModelProperty(notes = "Must be omitted when adding a new entity. Required otherwise.")
    private Long id;

    @ApiModelProperty(required = true, notes = "Between 2 and 128 caracters")
    @NotBlank
    @Size(min = 2, max = 128)
    private String title;

    @ApiModelProperty(required = true)
    @NotNull
    private Integer year;

    @ApiModelProperty(required = true, notes = "The film's genre (comedy, drama etc.)")
    @NotBlank
    private String genre;

    @ApiModelProperty(required = true, notes = "The ID of the person that directed the film")
    @NotNull
    private Long idDirector;

    @ApiModelProperty(required = true, notes = "The ID of the person that was the film's leading male actor")
    @NotNull
    private Long idActor;

    @ApiModelProperty(required = true, notes = "The ID of the person that was the film's leading female actress")
    @NotNull
    private Long idActress;


    public static FilmDto fromFilm(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.setId(film.getId());
        filmDto.setTitle(film.getTitle());
        filmDto.setYear(film.getYear());
        filmDto.setGenre(film.getGenre());

        Person director = film.getDirector();
        filmDto.setIdDirector(director == null ? null : director.getId());
        Person actress = film.getActress();
        filmDto.setIdActress(actress == null ? null : actress.getId());
        Person actor = film.getActor();
        filmDto.setIdActor(actor == null ? null : actor.getId());

        return filmDto;
    }

    public Film toFilm(PersonService personService) throws PersistenceException, InvalidDtoException {
        Film film = new Film();
        film.setId(this.id);
        film.setTitle(this.title);
        film.setYear(this.year);
        film.setGenre(this.genre);
        try {
            film.setDirector(personService.findById(getIdDirector()));
        } catch (NotFoundException e) {
            throw new InvalidDtoException("idDirector", "not found");
        }
        try {
            film.setActress(personService.findById(getIdActress()));
        } catch (NotFoundException e) {
            throw new InvalidDtoException("idActress", "not found");
        }
        try {
            film.setActor(personService.findById(getIdActor()));
        } catch (NotFoundException e) {
            throw new InvalidDtoException("idActor", "not found");
        }
        return film;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Long idDirector) {
        this.idDirector = idDirector;
    }

    public Long getIdActor() {
        return idActor;
    }

    public void setIdActor(Long idActor) {
        this.idActor = idActor;
    }

    public Long getIdActress() {
        return idActress;
    }

    public void setIdActress(Long idActress) {
        this.idActress = idActress;
    }
}

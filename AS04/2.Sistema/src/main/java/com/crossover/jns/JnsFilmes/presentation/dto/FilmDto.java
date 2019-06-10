package com.crossover.jns.JnsFilmes.presentation.dto;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.service.PersonService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FilmDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 128)
    private String title;

    @NotNull
    private Integer year;

    @NotBlank
    private String genre;

    @NotNull
    private Long idDirector;

    @NotNull
    private Long idActor;

    @NotNull
    private Long idActress;


    public static FilmDto fromFilm (Film film) {
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

    public Film toFilm(PersonService personService) {
        Film film = new Film();
        film.setId(this.id);
        film.setTitle(this.title);
        film.setYear(this.year);
        film.setGenre(this.genre);

        film.setDirector(personService.findById(getIdDirector()));
        film.setActress(personService.findById(getIdActress()));
        film.setActor(personService.findById(getIdActor()));

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

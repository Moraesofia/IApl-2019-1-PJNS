package com.crossover.jns.JnsFilmes.presentation.dto;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.business.entity.Person;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FilmDto {

    private Long id;

    @Size(min = 2, max = 128)
    private String title;

    @NotNull
    private int year;

    @NotBlank
    private String genre;

    @NotNull
    private Person director;

    @NotNull
    private Person actress;

    @NotNull
    private Person actor;

    public static FilmDto fromFilm (Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.setId(film.getId());
        filmDto.setTitle(film.getTitle());
        filmDto.setYear(film.getYear());
        filmDto.setGenre(film.getGenre());
        filmDto.setDirector(film.getDirector());
        filmDto.setActress(film.getActress());
        filmDto.setActor(film.getActor());
        return filmDto;
    }

    public Film toFilm() {
        Film film = new Film();
        film.setId(this.id);
        film.setTitle(this.title);
        film.setYear(this.year);
        film.setGenre(this.genre);
        film.setDirector(this.director);
        film.setActress(this.actress);
        film.setActor(this.actor);
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public Person getActress() {
        return actress;
    }

    public void setActress(Person actress) {
        this.actress = actress;
    }

    public Person getActor() {
        return actor;
    }

    public void setActor(Person actor) {
        this.actor = actor;
    }

}

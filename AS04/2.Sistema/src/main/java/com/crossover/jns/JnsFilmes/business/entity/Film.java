package com.crossover.jns.JnsFilmes.business.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue
    private int id;

    private String title;

    private int year;

    private String genre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person director;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person actress;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person actor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Film filme = (Film) o;

        return id == filme.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

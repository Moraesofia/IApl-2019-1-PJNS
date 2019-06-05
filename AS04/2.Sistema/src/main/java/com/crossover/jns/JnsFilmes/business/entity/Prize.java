package com.crossover.jns.JnsFilmes.business.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.crossover.jns.JnsFilmes.business.enums.CategoryEnum;

@Entity
@Table(name = "prize")
public class Prize {

    @Id
    @GeneratedValue
    private int id;

    private CategoryEnum category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person winner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    private Award award;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public Person getWinner() {
        return winner;
    }

    public void setWinner(Person winner) {
        this.winner = winner;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Prize premio = (Prize) o;

        return id == premio.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}

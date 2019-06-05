package com.crossover.jns.JnsFilmes.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.crossover.jns.JnsFilmes.business.enums.GenreEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String birth;

    private JobEnum job;

    private GenreEnum genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public String getNascimento() {
        return birth;
    }

    public void setNascimento(String birth) {
        this.birth = birth;
    }

    public JobEnum getCargo() {
        return job;
    }

    public void setCargo(JobEnum job) {
        this.job = job;
    }

    public GenreEnum getGenero() {
        return genre;
    }

    public void setGenero(GenreEnum genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Person pessoa = (Person) o;

        return id == pessoa.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

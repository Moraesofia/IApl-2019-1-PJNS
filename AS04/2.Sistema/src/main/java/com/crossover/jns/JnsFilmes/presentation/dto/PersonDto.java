package com.crossover.jns.JnsFilmes.presentation.dto;

import com.crossover.jns.JnsFilmes.business.enums.GenreEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;

import javax.validation.constraints.NotBlank;

public class PersonDto {

    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String birth;

    @NotBlank
    private String job;

    @NotBlank
    private String genre;

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

    public String getCargo() {
        return job;
    }

    public void setCargo(String job) {
        this.job = job;
    }

    public String getGenero() {
        return genre;
    }

    public void setGenero(String genre) {
        this.genre = genre;
    }
}

package com.crossover.jns.JnsFilmes.presentation.dto;

import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.enums.GenreEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonDto {

    private Long id;

    @Size(min = 2, max = 128)
    private String name;

    @NotBlank
    private String birth;

    @NotBlank
    private String job;

    @NotBlank
    private String genre;

    public static PersonDto fromPerson(Person p){
        PersonDto dto = new PersonDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setBirth(p.getBirth());
        dto.setGenre(p.getGenre().getText());
        dto.setJob(p.getJob().getText());
        return dto;
    }

    public Person toPerson(){
        Person p = new Person();
        p.setId(this.id);
        p.setName(this.name);
        p.setBirth(this.birth);
        p.setGenre(GenreEnum.fromText(this.genre));
        p.setJob(JobEnum.fromText(this.job));
        return p;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

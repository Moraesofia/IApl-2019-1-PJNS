package com.crossover.jns.JnsFilmes.presentation.dto;

import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.enums.GenderEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonDto {

    @ApiModelProperty(notes = "Must be omitted when adding a new entity. Required otherwise.")
    private Long id;

    @ApiModelProperty(required = true, notes = "Between 2 and 128 caracters")
    @Size(min = 2, max = 128)
    private String name;

    @ApiModelProperty(required = true, notes = "dd / MM / yyyy", example = "23 / 03 / 1904")
    @NotBlank
    private String birth;

    @ApiModelProperty(required = true, allowableValues = "DIRECTOR,ACTOR_OR_ACTRESS,WRITER")
    @NotBlank
    private String job;

    @ApiModelProperty(required = true, allowableValues = "FEMALE,MALE")
    @NotBlank
    private String gender;

    public static PersonDto fromPerson(Person p) {
        PersonDto dto = new PersonDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setBirth(p.getBirth());
        dto.setGender(p.getGender().name());
        dto.setJob(p.getJob().name());
        return dto;
    }

    public Person toPerson() {
        Person p = new Person();
        p.setId(this.id);
        p.setName(this.name);
        p.setBirth(this.birth);
        p.setGender(GenderEnum.valueOf(this.gender));
        p.setJob(JobEnum.valueOf(this.job));
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

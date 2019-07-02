package com.crossover.jns.JnsFilmes.presentation.dto.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class StateDto {

    private Long id = null;
    private String name = null;
    private CountryDto country = new CountryDto();

    public StateDto() throws InvalidDtoException {
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

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "StateDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}

package com.crossover.jns.JnsFilmes.presentation.dto.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;

public class CityDto {

    private Long id = null;
    private String name = null;
    private String abbreviation = null;
    private StateDto state = new StateDto();
    private CountryDto country = new CountryDto();

    public CityDto() throws InvalidDtoException {
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getState() {
        return state.getName();
    }

    public void setState(StateDto state) {
        this.state = state;
    }

    public String getCountry() {
        return country.getName();
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "CityDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", state=" + state +
                ", country=" + country +
                '}';
    }
}

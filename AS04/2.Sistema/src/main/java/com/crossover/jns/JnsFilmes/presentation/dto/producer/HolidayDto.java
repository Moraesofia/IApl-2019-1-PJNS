package com.crossover.jns.JnsFilmes.presentation.dto.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class HolidayDto {

    private Long id = 0L;

    private String city = "";

    private String state = "";

    private String country = "Brazil";

    @NotBlank
    private String description = "";

    @NotBlank
    @Size(min = 10, max = 10)
    private String date = "01-01-2019";

    @Override
    public String toString() {
        return "HolidayDto{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    public HolidayDto() throws InvalidDtoException {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

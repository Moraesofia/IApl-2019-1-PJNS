package com.crossover.jns.JnsFilmes.presentation.dto.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;

import java.util.ArrayList;
import java.util.List;

public class AppliedHolidayDto {

    private String city = null;
    private String country = null;
    private String date = null;
    private String description = null;
    private String state = null;

    public AppliedHolidayDto() throws InvalidDtoException {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AppliedHolidayDto{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

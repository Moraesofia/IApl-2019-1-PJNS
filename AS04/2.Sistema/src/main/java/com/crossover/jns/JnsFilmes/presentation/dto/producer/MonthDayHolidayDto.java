package com.crossover.jns.JnsFilmes.presentation.dto.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MonthDayHolidayDto {

    private Long id = null;
    private String city = "";
    private String state = "";

    @NotBlank
    private String country = "Brazil";

    @NotNull
    @Min(1)
    @Max(31)
    private Integer dayOfMonth = 1;

    @NotNull
    @Min(1)
    @Max(12)
    private Integer month = 1;

    @NotBlank
    private String description = "";

    public MonthDayHolidayDto() throws InvalidDtoException {

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

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MonthDayHolidayDto{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", dayOfMonth=" + dayOfMonth +
                ", month=" + month +
                '}';
    }
}

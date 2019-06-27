package com.crossover.jns.JnsFilmes.presentation.dto.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;

import java.util.ArrayList;
import java.util.List;

public class HolidaysDto {

    private Integer year = null;

    private List<HolidayDto> holidays = new ArrayList<>();

    public HolidaysDto() throws InvalidDtoException {

    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<HolidayDto> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<HolidayDto> holidays) {
        this.holidays = holidays;
    }

    @Override
    public String toString() {
        return "HolidaysDto{" +
                "year=" + year +
                ", holidays=" + holidays +
                '}';
    }
}

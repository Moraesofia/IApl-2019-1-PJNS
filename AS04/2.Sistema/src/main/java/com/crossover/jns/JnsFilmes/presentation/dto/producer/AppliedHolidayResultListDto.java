package com.crossover.jns.JnsFilmes.presentation.dto.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;

import java.util.ArrayList;
import java.util.List;

public class AppliedHolidayResultListDto {

    private List<AppliedHolidayDto> holidays = new ArrayList<>();
    private Long total = null;
    private Integer year = null;

    public AppliedHolidayResultListDto() throws InvalidDtoException {

    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<AppliedHolidayDto> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<AppliedHolidayDto> holidays) {
        this.holidays = holidays;
    }

    @Override
    public String toString() {
        return "AppliedHolidayResultListDto{" +
                "year=" + year +
                ", total=" + total +
                ", holidays=" + holidays +
                '}';
    }
}

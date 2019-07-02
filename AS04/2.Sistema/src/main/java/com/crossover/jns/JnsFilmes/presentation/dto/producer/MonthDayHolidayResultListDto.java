package com.crossover.jns.JnsFilmes.presentation.dto.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;

import java.util.ArrayList;
import java.util.List;

public class MonthDayHolidayResultListDto {

    private List<MonthDayHolidayDto> holidays = new ArrayList<>();
    private Long total = null;
    private Integer year = null;

    public MonthDayHolidayResultListDto() throws InvalidDtoException {

    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<MonthDayHolidayDto> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<MonthDayHolidayDto> holidays) {
        this.holidays = holidays;
    }

    @Override
    public String toString() {
        return "MonthDayHolidayResultListDto{" +
                "year=" + year +
                ", total=" + total +
                ", holidays=" + holidays +
                '}';
    }
}

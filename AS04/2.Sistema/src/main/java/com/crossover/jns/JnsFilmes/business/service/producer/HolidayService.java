package com.crossover.jns.JnsFilmes.business.service.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.HolidayDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.HolidaysDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class HolidayService {

    // Known API bugs/remarks (as of 27/6):
    // 1 - Year is ignored
    // 2 - ID is ignored (so it's not possible to edit existing holidays)
    // 3 - City, state and country names must be in english and exist in real life.
    // 4 - If there's a city, state and country are ignored. They are fetched based on the cities in the world.
    // 5 - If the city name belongs to multiple cities in the world, an exception is thrown.
    // 6 - If there's only country and state, it saves wrong (state becomes country and country becomes null).

    private final static String API_BASE_URL = "https://calendarioeventos.herokuapp.com/";

    @Autowired
    private RestTemplate rest;

    public HolidaysDto getHolidays() throws ProducerApiException {
        try {
            return rest.getForObject(API_BASE_URL + "holiday", HolidaysDto.class);
        } catch (Exception ex) {
            throw new ProducerApiException(ex);
        }
    }

    public HolidayDto getHoliday(Long id) throws NotFoundException, ProducerApiException {
        if (id == null)
            throw new NotFoundException();
        try {
            ResponseEntity<HolidaysDto> response = rest.getForEntity(API_BASE_URL + "holiday/" + id, HolidaysDto.class);
            if (response.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new NotFoundException();
            else if (response.getStatusCode() != HttpStatus.OK)
                throw new ProducerApiException();
            HolidaysDto body = response.getBody();
            if (body == null || body.getHolidays() == null || body.getHolidays().size() < 1)
                throw new NotFoundException();
            return body.getHolidays().get(0);
        } catch (Exception ex) {
            if (ex instanceof NotFoundException)
                throw ex;
            throw new ProducerApiException(ex);
        }
    }

    public HolidaysDto saveHolidays(HolidaysDto holidaysDTO) throws ProducerApiException {
        try {
            ResponseEntity<HolidaysDto> resp = rest.postForEntity(API_BASE_URL + "holiday", holidaysDTO, HolidaysDto.class);
            if (resp.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
                throw new ProducerApiException("Internal server error");
            }
            return resp.getBody();
        } catch (Exception ex) {
            throw new ProducerApiException(ex);
        }
    }
}

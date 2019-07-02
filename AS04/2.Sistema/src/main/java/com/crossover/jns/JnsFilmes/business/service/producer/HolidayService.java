package com.crossover.jns.JnsFilmes.business.service.producer;

import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.AppliedHolidayResultListDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.MonthDayHolidayDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.MonthDayHolidayResultListDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@Transactional
public class HolidayService {

    private final static String API_BASE_URL = "https://calendarioeventos.herokuapp.com/";

    @Autowired
    private RestTemplate rest;

    public MonthDayHolidayResultListDto getHolidays() throws NotFoundException, ProducerApiException {
        try {
            String uri = "holiday?page=0&size=99999";
            ResponseEntity<MonthDayHolidayResultListDto> response = rest.getForEntity(API_BASE_URL + uri, MonthDayHolidayResultListDto.class);
            if (response.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new NotFoundException();
            else if (response.getStatusCode() != HttpStatus.OK)
                throw new ProducerApiException(response.getStatusCode().getReasonPhrase());
            MonthDayHolidayResultListDto body = response.getBody();
            body.setHolidays(body.getHolidays().stream()
                    .sorted(Comparator.comparing(MonthDayHolidayDto::getMonth)
                            .thenComparing(MonthDayHolidayDto::getDayOfMonth))
                    .collect(Collectors.toList()));
            return body;

        } catch (Exception ex) {
            if (ex instanceof NotFoundException)
                throw ex;
            if (ex instanceof ProducerApiException)
                throw ex;
            throw new ProducerApiException(ex);
        }
    }

    public MonthDayHolidayDto getHoliday(Long id) throws NotFoundException, ProducerApiException {
        try {
            if (id == null)
                throw new NotFoundException();

            String uri = API_BASE_URL + "holiday/" + id;
            ResponseEntity<MonthDayHolidayDto> response = rest.getForEntity(uri, MonthDayHolidayDto.class);
            if (response.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new NotFoundException();
            else if (response.getStatusCode() != HttpStatus.OK)
                throw new ProducerApiException();
            return response.getBody();

        } catch (Exception ex) {
            if (ex instanceof NotFoundException)
                throw ex;
            if (ex instanceof ProducerApiException)
                throw ex;
            throw new ProducerApiException(ex);
        }
    }

    public MonthDayHolidayDto addHoliday(MonthDayHolidayDto holidayDto) throws NotFoundException, ProducerApiException {
        try {
            if (holidayDto == null)
                throw new NotFoundException();
            holidayDto.setId(null);

            String uri = API_BASE_URL + "holiday";
            ResponseEntity<MonthDayHolidayDto> response = rest.postForEntity(uri, holidayDto, MonthDayHolidayDto.class);
            if (response.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new NotFoundException();
            else if (response.getStatusCode() != HttpStatus.OK)
                throw new ProducerApiException(response.getStatusCode().getReasonPhrase());
            return response.getBody();

        } catch (Exception ex) {
            if (ex instanceof NotFoundException)
                throw ex;
            if (ex instanceof ProducerApiException)
                throw ex;
            throw new ProducerApiException(ex);
        }
    }

    public MonthDayHolidayDto updateHoliday(MonthDayHolidayDto holidayDto) throws NotFoundException, ProducerApiException {
        try {
            if (holidayDto == null || holidayDto.getId() == null)
                throw new NotFoundException();

            String uri = API_BASE_URL + "holiday";
            ResponseEntity<MonthDayHolidayDto> response = putForEntity(uri, holidayDto, MonthDayHolidayDto.class);
            if (response.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new NotFoundException();
            else if (response.getStatusCode() != HttpStatus.OK)
                throw new ProducerApiException(response.getStatusCode().getReasonPhrase());
            return response.getBody();

        } catch (Exception ex) {
            if (ex instanceof NotFoundException)
                throw ex;
            if (ex instanceof ProducerApiException)
                throw ex;
            throw new ProducerApiException(ex);
        }
    }

    public void deleteHoliday(Long id) throws ProducerApiException {
        try {
            String uri = API_BASE_URL + "holiday/" + id;
            rest.delete(uri);
        } catch (Exception ex) {
            throw new ProducerApiException(ex);
        }
    }

    private <T> ResponseEntity<T> putForEntity(String uri, Object obj, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = null;
        try {
            requestBody = new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
        return rest.exchange(uri, HttpMethod.PUT, entity, responseType);
    }
}

package com.crossover.jns.JnsFilmes.business.service.producer;

import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.CountryDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.MonthDayHolidayDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.MonthDayHolidayResultListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CountryService {

    private final static String API_BASE_URL = "https://calendarioeventos.herokuapp.com/";

    @Autowired
    private RestTemplate rest;

    public List<CountryDto> getCountries() throws NotFoundException, ProducerApiException {
        try {
            String uri = API_BASE_URL + "country";
            ResponseEntity<List<CountryDto>> response = rest.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CountryDto>>() {
                    });
            if (response.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new NotFoundException();
            else if (response.getStatusCode() != HttpStatus.OK)
                throw new ProducerApiException(response.getStatusCode().getReasonPhrase());

            return response.getBody().stream().sorted(Comparator.comparing(CountryDto::getName)).collect(Collectors.toList());

        } catch (Exception ex) {
            if (ex instanceof NotFoundException)
                throw ex;
            if (ex instanceof ProducerApiException)
                throw ex;
            throw new ProducerApiException(ex);
        }
    }

}

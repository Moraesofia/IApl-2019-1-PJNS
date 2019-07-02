package com.crossover.jns.JnsFilmes.business.service.producer;

import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.CityDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.CountryDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.StateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityService {

    private final static String API_BASE_URL = "https://calendarioeventos.herokuapp.com/";

    @Autowired
    private RestTemplate rest;

    public List<CityDto> getCities() throws NotFoundException, ProducerApiException {
        try {
            String uri = API_BASE_URL + "city";
            ResponseEntity<List<CityDto>> response = rest.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CityDto>>(){});
            if (response.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new NotFoundException();
            else if (response.getStatusCode() != HttpStatus.OK)
                throw new ProducerApiException(response.getStatusCode().getReasonPhrase());

            return response.getBody().stream().sorted(Comparator.comparing(CityDto::getName)).collect(Collectors.toList());

        } catch (Exception ex) {
            if (ex instanceof NotFoundException)
                throw ex;
            if (ex instanceof ProducerApiException)
                throw ex;
            throw new ProducerApiException(ex);
        }
    }

    public List<CityDto> getCitiesByState(String state) throws NotFoundException, ProducerApiException {
        try {
            String uri = API_BASE_URL + "city/" + state;
            ResponseEntity<List<CityDto>> response = rest.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CityDto>>(){});
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
}

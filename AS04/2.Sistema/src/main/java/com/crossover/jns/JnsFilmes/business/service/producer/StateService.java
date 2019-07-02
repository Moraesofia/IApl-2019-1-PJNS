package com.crossover.jns.JnsFilmes.business.service.producer;

import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
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

import javax.swing.plaf.nimbus.State;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StateService {

    private final static String API_BASE_URL = "https://calendarioeventos.herokuapp.com/";

    @Autowired
    private RestTemplate rest;

    public List<StateDto> getStates() throws NotFoundException, ProducerApiException {
        try {
            String uri = API_BASE_URL + "state";
            ResponseEntity<List<StateDto>> response = rest.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<StateDto>>() {
                    });
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

    public List<StateDto> getStatesByCountry(String country) throws NotFoundException, ProducerApiException {
        try {
            String uri = API_BASE_URL + "state/" + country;
            ResponseEntity<List<StateDto>> response = rest.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<StateDto>>() {
                    });
            if (response.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new NotFoundException();
            else if (response.getStatusCode() != HttpStatus.OK)
                throw new ProducerApiException(response.getStatusCode().getReasonPhrase());

            return response.getBody().stream().sorted(Comparator.comparing(StateDto::getName)).collect(Collectors.toList());

        } catch (Exception ex) {
            if (ex instanceof NotFoundException)
                throw ex;
            if (ex instanceof ProducerApiException)
                throw ex;
            throw new ProducerApiException(ex);
        }
    }

    public boolean containState(String name){
        boolean contain = false;
        try {
            for (StateDto s: getStates()) {
                if(s.getName().equals(name))
                    contain = true;
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ProducerApiException e) {
            e.printStackTrace();
        }

        return contain;
    }
}

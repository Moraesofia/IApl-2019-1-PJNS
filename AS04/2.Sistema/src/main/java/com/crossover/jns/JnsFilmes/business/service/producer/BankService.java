package com.crossover.jns.JnsFilmes.business.service.producer;

import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.BankDto;
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
public class BankService {

    private final static String API_BASE_URL = "https://calendarioeventos.herokuapp.com/";

    @Autowired
    private RestTemplate rest;

    public List<BankDto> getBanks() throws NotFoundException, ProducerApiException {
        try {
            String uri = API_BASE_URL + "external";
            ResponseEntity<List<BankDto>> responseEntity = rest.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<BankDto>>() {});
            if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException();
            } else if (responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new ProducerApiException(responseEntity.getStatusCode().getReasonPhrase());
            }
            return responseEntity.getBody().stream().sorted(Comparator.comparing(BankDto::getTitular)).collect(Collectors.toList());
        } catch (Exception e) {
            if (e instanceof  NotFoundException) {
                throw e;
            }
            if (e instanceof  ProducerApiException) {
                throw  e;
            }
            throw new ProducerApiException(e);
        }

    }

    public BankDto getBank(Long id) throws NotFoundException, ProducerApiException {
        try {
            if (id == null) {
                throw new NotFoundException();
            }
            String uri = API_BASE_URL + "external/" + id;
            ResponseEntity<BankDto> responseEntity = rest.getForEntity(uri, BankDto.class);
            if (responseEntity.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NotFoundException();
            } else if (responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new ProducerApiException(responseEntity.getStatusCode().getReasonPhrase());
            }
            return responseEntity.getBody();
        } catch (Exception e) {
            if (e instanceof  NotFoundException) {
                throw e;
            }
            if (e instanceof  ProducerApiException) {
                throw  e;
            }
            throw new ProducerApiException(e);
        }
    }

}

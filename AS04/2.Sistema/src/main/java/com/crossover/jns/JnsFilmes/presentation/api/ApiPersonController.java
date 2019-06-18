package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.data.repository.PersonRepository;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.presentation.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class ApiPersonController extends ApiEntityControllerBase<Person, Long, PersonRepository, PersonDto> {

    @Autowired
    private PersonService personService;

    @Override
    protected EntityServiceBase<Person, Long, PersonRepository> getEntityService() {
        return personService;
    }

    @Override
    protected PersonDto convertToDto(Person person) {
        return PersonDto.fromPerson(person);
    }

    @Override
    protected Person convertToEntity(PersonDto personDto) {
        return personDto.toPerson();
    }

    @Override
    protected Long getDtoId(PersonDto personDto) {
        return personDto.getId();
    }

    @Override
    protected List<PersonDto> findAllDto() throws PersistenceException {
        return personService.findAllDto();
    }

    @Override
    protected String getEntityName() {
        return Person.class.getSimpleName();
    }
}

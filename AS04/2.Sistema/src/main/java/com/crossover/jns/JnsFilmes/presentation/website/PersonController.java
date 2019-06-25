package com.crossover.jns.JnsFilmes.presentation.website;

import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.enums.GenderEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;
import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.data.repository.PersonRepository;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.presentation.dto.PersonDto;
import com.crossover.jns.JnsFilmes.presentation.dto.PrizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/persons")
public class PersonController extends WebsiteEntityControllerBase<Person, Long, PersonRepository, PersonDto> {

    @Autowired
    private PersonService personService;

    @Override
    protected String getControllerBasePath() {
        return "/persons";
    }

    @Override
    protected EntityServiceBase<Person, Long, PersonRepository> getEntityService() {
        return personService;
    }

    @Override
    protected PersonDto convertToDto(Person person) {
        return PersonDto.fromPerson(person);
    }

    @Override
    protected Person convertToEntity(PersonDto personDto) throws PersistenceException {
        return personDto.toPerson();
    }

    @Override
    protected List<PersonDto> findAllDto() throws PersistenceException {
        return personService.findAllDto();
    }

    @Override
    protected Long getDtoId(PersonDto personDto) {
        return personDto.getId();
    }

    @Override
    protected Long getEntityId(Person person) {
        return person.getId();
    }

    @Override
    protected String getEntityName() {
        return Person.class.getSimpleName();
    }

    @Override
    protected Person getNewEntity() {
        return new Person();
    }

    @Override
    protected PersonDto getNewDto() {
        return new PersonDto();
    }

    @Override
    protected boolean validateEntity(@Valid PersonDto personDto, BindingResult bindingResult) {
        if (Arrays.stream(JobEnum.values()).noneMatch(v -> Objects.equals(v.name(), personDto.getJob()))) {
            rejectBindingValue(bindingResult, "job", "unknown job");
            return false;
        }
        if (Arrays.stream(GenderEnum.values()).noneMatch(v -> Objects.equals(v.name(), personDto.getGender()))) {
            rejectBindingValue(bindingResult, "gender", "unknown gender");
            return false;
        }
        return true;
    }
}

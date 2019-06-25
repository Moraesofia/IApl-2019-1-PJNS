package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.data.repository.PersonRepository;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.exceptions.RestApiException;
import com.crossover.jns.JnsFilmes.presentation.dto.PersonDto;
import com.crossover.jns.JnsFilmes.presentation.dto.PersonDto;
import com.crossover.jns.JnsFilmes.presentation.dto.RespostaSimplesDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/person")
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

    /**
     * Get all persons
     */
    @ApiOperation(value = "Get all", notes = "Gets all persons")
    @GetMapping("persons")
    @Override
    public List<PersonDto> getAll() throws RestApiException {
        return super.getAll();
    }

    /**
     * Get person by ID
     */
    @ApiOperation(value = "Get by ID", notes = "Gets an existing person by its ID")
    @GetMapping("person/{id}")
    @Override
    public PersonDto getById(Long id) throws RestApiException {
        return super.getById(id);
    }

    /**
     * Add new person
     */
    @ApiOperation(value = "Add new", notes = "Add a new person. The new person's ID must be omitted or null.")
    @PostMapping("person")
    @Override
    public PersonDto post(@Valid PersonDto personDto) throws RestApiException {
        return super.post(personDto);
    }

    /**
     * Edit/replace existing person
     */
    @ApiOperation(value = "Edit", notes = "Edits (replaces) the person with the given ID")
    @PutMapping("person/{id}")
    @Override
    public PersonDto put(@Valid PersonDto personDto, Long id) throws RestApiException {
        return super.put(personDto, id);
    }

    /**
     * Delete existing person
     */
    @ApiOperation(value = "Delete", notes = "Deletes the person with the given ID")
    @DeleteMapping("person/{id}")
    @Override
    public RespostaSimplesDto delete(Long id) throws RestApiException {
        return super.delete(id);
    }
}

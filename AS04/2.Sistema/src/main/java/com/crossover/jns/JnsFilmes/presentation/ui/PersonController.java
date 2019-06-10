package com.crossover.jns.JnsFilmes.presentation.ui;

import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.enums.GenderEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.presentation.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Objects;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    //Retrieves all saved entities as DTOs
    @GetMapping("/persons/list")
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.findAllDto());
        return "persons";
    }

    @GetMapping("/persons/{id}")
    public String getPerson(@PathVariable Long id, Model model) {
        Person person = personService.findById(id);
        if (person == null) {
            throw new IllegalArgumentException("Person not found");
        }
        model.addAttribute("personDto", PersonDto.fromPerson(person));
        return "persons/person";
    }

    //Edit a entity by its ID
    @GetMapping("/person/edit/{id}")
    public String editEntity(@PathVariable Long id, Model model) {
        model.addAttribute("personDto", PersonDto.fromPerson(personService.findById(id)));
        return "persons-edit";
    }

    //Delete an entity by its ID
    @GetMapping("/person/delete/{id}")
    public String deleteEntity(@PathVariable Long id) {
        try {
            personService.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            return "redirect:/persons/list?cantDeleteBecauseItsUsed";
        }
        return "redirect:/persons/list?deleted";
    }

    //Create new entity action
    @GetMapping("/person/add")
    public String createEntity(Model model) {
        model.addAttribute("personDto", new PersonDto());
        return "persons-edit";
    }

    // Save action from the form. It either creates or updates a person
    @PostMapping("/person/save")
    public String saveEntity(@Valid PersonDto personDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "persons-edit";
        }

        //Validating the DTO's attributes
        if (Arrays.stream(JobEnum.values()).noneMatch(v -> Objects.equals(v.name(), personDto.getJob()))) {
            bindingResult.rejectValue("job", "error.personDto", "invalid job");
            return "persons-edit";
        }
        if (Arrays.stream(GenderEnum.values()).noneMatch(v -> Objects.equals(v.name(), personDto.getGender()))) {
            bindingResult.rejectValue("gender", "error.personDto", "invalid gender");
            return "persons-edit";
        }

        //Creating an entity from the DTO
        Person person = personDto.toPerson();
        Long oldId = person.getId();
        person = personService.save(person);
        Long newId = person.getId();

        if (Objects.equals(oldId, newId)) {
            return "redirect:/persons/list?updated";
        } else {
            return "redirect:/persons/list?created";
        }
    }

}

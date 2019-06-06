package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PersonController {

    /**
     * TODO refactor to use DTO
     */

    @Autowired
    private PersonService personService;

    @GetMapping("/persons/list")
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "persons";
    }

    @GetMapping("/person/edit/{id}")
    public String editEntity(@PathVariable long id, Model model) {
        Person exemplo = personService.findByid(id);
        Person person = personService.findByid(id);
        model.addAttribute("person", person);
        return "persons-edit";
    }

    @GetMapping("/person/delete/{id}")
    public String deleteEntity(@PathVariable long id) {
        personService.deleteById(id);
        return "redirect:/persons/list";
    }

    @GetMapping("/person/add")
    public String createEntity(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "persons-edit";
    }

    @PostMapping("/person/save")
    public String saveEntity(Person person) {
        personService.save(person);
        return "redirect:/persons/list";
    }

    // Create/update template
    @GetMapping("/edit")
    public String CreateUpdateScreen() {
        return "persons-edit";
    }



}

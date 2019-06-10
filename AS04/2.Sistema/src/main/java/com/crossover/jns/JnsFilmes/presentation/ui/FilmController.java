package com.crossover.jns.JnsFilmes.presentation.ui;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.business.service.FilmService;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.presentation.dto.FilmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private PersonService personService;

    //Retrieves all saved entities as DTOs
    @GetMapping("/films/list")
    public String listFilms(Model model) {
        model.addAttribute("films", filmService.findAllDto());
        return "films";
    }

    //Edit an entity by its ID
    @GetMapping("/film/edit/{id}")
    public String editEntity(@PathVariable Long id, Model model) {
        model.addAttribute("filmDto", FilmDto.fromFilm(filmService.findById(id)));
        return "films-edit";
    }

    //Delete an entity by its ID
    @GetMapping("/film/delete/{id}")
    public String deleteEntity(@PathVariable Long id) {
        try {
            filmService.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            return "redirect:/films/list?cantDeleteBecauseItsUsed";
        }
        return "redirect:/films/list?deleted";
    }

    //Create new entity action
    @GetMapping("/film/add")
    public String createEntity(Model model) {
        model.addAttribute("filmDto", new FilmDto());
        return "films-edit";
    }

    // Save action from the form. It either creates or updates a film
    @PostMapping("/film/save")
    public String saveEntity(@Valid FilmDto filmDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "films-edit";
        }

        Film film = filmDto.toFilm(personService);
        Long oldId = film.getId();
        film = filmService.save(film);
        Long newId = film.getId();

        if (Objects.equals(oldId, newId)) {
            return "redirect:/films/list?updated";
        } else {
            return "redirect:/films/list?created";
        }

    }

}

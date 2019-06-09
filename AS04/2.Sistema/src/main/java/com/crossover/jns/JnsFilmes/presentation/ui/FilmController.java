package com.crossover.jns.JnsFilmes.presentation.ui;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.business.service.FilmService;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.presentation.dto.FilmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        filmService.deleteById(id);
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
    public String saveEntity(FilmDto filmDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "films-edit";
        }

        //Validating the DTO's attributes
        if (filmDto.getTitle() == null || filmDto.getTitle() == "") {
            bindingResult.rejectValue("title", "error.filmDto", "Invalid title");
            return "films-edit";
        }

        if (filmDto.getYear() == null) {
            bindingResult.rejectValue("year", "error.filmDto", "Invalid year");
            return "films-edit";
        }

        if (filmDto.getGenre() == null || filmDto.getGenre() == "") {
            bindingResult.rejectValue("genre", "error.filmDto", "Invalid genre");
            return "films-edit";
        }

        if (filmDto.getIdDirector() == null) {
            bindingResult.rejectValue("director", "error.filmDto", "Invalid director");
            return "films-edit";
        }

        if (filmDto.getIdActress() == null) {
            bindingResult.rejectValue("actress", "error.filmDto", "Invalid actress");
            return "films-edit";
        }

        if (filmDto.getIdActor() == null) {
            bindingResult.rejectValue("actor", "error.filmDto", "Invalid actor");
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

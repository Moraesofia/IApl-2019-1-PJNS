package com.crossover.jns.JnsFilmes.presentation.ui;

import com.crossover.jns.JnsFilmes.business.entity.Prize;
import com.crossover.jns.JnsFilmes.business.enums.CategoryEnum;
import com.crossover.jns.JnsFilmes.business.service.AwardService;
import com.crossover.jns.JnsFilmes.business.service.FilmService;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.business.service.PrizeService;
import com.crossover.jns.JnsFilmes.presentation.dto.PrizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private PersonService personService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private AwardService awardService;

    //Retrieves all saved entities as DTOs
    @GetMapping("/prizes/list")
    public String listPrizes(Model model) {
        model.addAttribute("prizes", prizeService.findAllDto());
        return "prizes";
    }

    @GetMapping("/prizes/{id}")
    public String getPrize(@PathVariable Long id, Model model) {
        Prize prize = prizeService.findById(id);
        if (prize == null) {
            throw new IllegalArgumentException("Prize not found");
        }
        model.addAttribute("prizeDto", PrizeDto.fromPrize(prize));
        return "prizes/prize";
    }

    //Edit a entity by its ID
    @GetMapping("/prize/edit/{id}")
    public String editEntity(@PathVariable Long id, Model model) {
        model.addAttribute("prizeDto", PrizeDto.fromPrize(prizeService.findById(id)));
        return "prizes-edit";
    }

    //Delete an entity by its ID
    @GetMapping("/prize/delete/{id}")
    public String deleteEntity(@PathVariable Long id) {
        prizeService.deleteById(id);
        return "redirect:/prizes/list?deleted";
    }

    //Create new entity action
    @GetMapping("/prize/add")
    public String createEntity(Model model) {
        model.addAttribute("prizeDto", new PrizeDto());
        return "prizes-edit";
    }

    //Save action from the form. It either creates or updates a prize
    @PostMapping("/prize/save")
    public String saveEntity(PrizeDto prizeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "prizes-edit";
        }

        if (CategoryEnum.fromText(prizeDto.getCategory()) == null) {
            bindingResult.rejectValue("category", "error.prizeDto", "Invalid category");
            return "prizes-edit";
        }

        if (prizeDto.getIdWinner() == null) {
            bindingResult.rejectValue("IdWinner", "error.prizeDto", "Invalid winner");
            return "prizes-edit";
        }

        if (prizeDto.getIdAward() == null) {
            bindingResult.rejectValue("IdAward", "error.prizeDto", "Invalid award");
            return "prizes-edit";
        }

        if (prizeDto.getIdFilm() == null) {
            bindingResult.rejectValue("IdFilm", "error.prizeDto", "Invalid film");
            return "prizes-edit";
        }

        //Creating an entity from the DTO
        Prize prize = prizeDto.toPrize(personService, filmService, awardService);
        Long oldId = prize.getId();
        prize = prizeService.save(prize);
        Long newId = prize.getId();

        if (Objects.equals(oldId, newId)) {
            return "redirect:/prizes/list?updated";
        } else {
            return "redirect:/prizes/list?created";
        }
    }



}

package com.crossover.jns.JnsFilmes.presentation.ui;

import com.crossover.jns.JnsFilmes.business.entity.Award;
import com.crossover.jns.JnsFilmes.business.service.AwardService;
import com.crossover.jns.JnsFilmes.presentation.dto.AwardDto;
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
public class AwardController {

    @Autowired
    private AwardService awardService;

    // Retrieves all saved entities as DTOs
    @GetMapping("/awards/list")
    public String listAwards(Model model) {
        model.addAttribute("awards", awardService.findAllDto());
        return "awards";
    }

    // Edit an entity by its ID
    @GetMapping("/award/edit/{id}")
    public String editEntity(@PathVariable Long id, Model model) {
        model.addAttribute("awardDto", AwardDto.fromAward(awardService.findById(id)));
        return "awards-edit";
    }

    // Delete an entity by its ID
    @GetMapping("/award/delete/{id}")
    public String deleteEntity(@PathVariable Long id) {
        try {
            awardService.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            return "redirect:/awards/list?cantDeleteBecauseItsUsed";
        }
        return "redirect:/awards/list?deleted";
    }

    // Create new entity action
    @GetMapping("/award/add")
    public String createEntity(Model model) {
        model.addAttribute("awardDto", new AwardDto());
        return "awards-edit";
    }

    // Save action from the form. It either creates or updates a award
    @PostMapping("/award/save")
    public String saveEntityPost(@Valid AwardDto awardDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "awards-edit";
        }

        Award award = awardDto.toAward();
        Long oldId = award.getId();
        award = awardService.save(award);
        Long newId = award.getId();

        if (Objects.equals(oldId, newId)) {
            return "redirect:/awards/list?updated";
        } else {
            return "redirect:/awards/list?created";
        }
    }

}

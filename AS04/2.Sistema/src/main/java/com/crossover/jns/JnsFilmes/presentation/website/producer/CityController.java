package com.crossover.jns.JnsFilmes.presentation.website.producer;

import com.crossover.jns.JnsFilmes.business.service.producer.CityService;
import com.crossover.jns.JnsFilmes.business.service.producer.StateService;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
import com.crossover.jns.JnsFilmes.exceptions.WebsiteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private StateService stateService;

    @GetMapping({"", "/"})
    public String listAll(Model model) throws WebsiteException {
        return "cities";
    }

    @PostMapping({"/search"})
    public String listPerState(Model model, @RequestParam("state") String state) throws WebsiteException {
        if (state.isEmpty() || !stateService.containState(state)) {
            model.addAttribute("err", "⚠");
            return "cities";
        }

        try {
            model.addAttribute("cities", cityService.getCitiesByState(state));
            return "cities";
        } catch (NotFoundException e) {
            throw WebsiteException.NotFound();
        } catch (ProducerApiException e) {
            throw WebsiteException.Internal(e);
        }
    }

}

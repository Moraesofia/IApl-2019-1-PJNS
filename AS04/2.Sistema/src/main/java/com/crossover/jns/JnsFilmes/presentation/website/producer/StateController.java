package com.crossover.jns.JnsFilmes.presentation.website.producer;

import com.crossover.jns.JnsFilmes.business.service.producer.CountryService;
import com.crossover.jns.JnsFilmes.business.service.producer.StateService;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
import com.crossover.jns.JnsFilmes.exceptions.WebsiteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    CountryService countryService;

    @GetMapping({"", "/"})
    public String listAll(Model model) throws WebsiteException {
        return "states";
    }

    @PostMapping({"/search"})
    public String listPerState(Model model, @RequestParam("country") String country) throws WebsiteException {
        if (country.isEmpty() || !countryService.containCountry(country)) {
            model.addAttribute("err", "⚠");
            return "states";
        }

        try {
            model.addAttribute("states", stateService.getStatesByCountry(country));
            return "states";
        } catch (NotFoundException e) {
            throw WebsiteException.NotFound();
        } catch (ProducerApiException e) {
            throw WebsiteException.Internal(e);
        }
    }

}

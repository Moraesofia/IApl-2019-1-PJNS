package com.crossover.jns.JnsFilmes.presentation.website.producer;

import com.crossover.jns.JnsFilmes.business.service.producer.CityService;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
import com.crossover.jns.JnsFilmes.exceptions.WebsiteException;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.MonthDayHolidayResultListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping({"", "/"})
    public String listAll(Model model) throws WebsiteException {
            return "cities";
    }

    @GetMapping({"/{state}"})
    public String listPerState(Model model, @PathVariable String state) throws WebsiteException {
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

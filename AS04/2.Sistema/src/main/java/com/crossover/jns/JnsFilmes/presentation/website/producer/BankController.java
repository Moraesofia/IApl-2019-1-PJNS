package com.crossover.jns.JnsFilmes.presentation.website.producer;

import com.crossover.jns.JnsFilmes.business.service.producer.BankService;
import com.crossover.jns.JnsFilmes.business.service.producer.CityService;
import com.crossover.jns.JnsFilmes.business.service.producer.HolidayService;
import com.crossover.jns.JnsFilmes.business.service.producer.StateService;
import com.crossover.jns.JnsFilmes.config.Messages;
import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
import com.crossover.jns.JnsFilmes.exceptions.WebsiteException;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping({"", "/"})
    public String listAll(Model model) throws WebsiteException {
        try {
            List<BankDto> banks = bankService.getBanks();
            model.addAttribute("banks", banks);
            return "banks";
        } catch (NotFoundException e) {
            throw WebsiteException.NotFound();
        } catch (ProducerApiException e) {
            throw WebsiteException.Internal(e);
        }
    }

    @GetMapping({"/{id}"})
    public String listById(Model model, @PathVariable Long id) throws WebsiteException {
        try {
            model.addAttribute("banks", bankService.getBank(id));
            return "banks";
        } catch (NotFoundException e) {
            throw WebsiteException.NotFound();
        } catch (ProducerApiException e) {
            throw WebsiteException.Internal(e);
        }
    }

}

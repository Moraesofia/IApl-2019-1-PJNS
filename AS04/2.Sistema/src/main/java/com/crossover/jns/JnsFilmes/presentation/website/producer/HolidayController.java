package com.crossover.jns.JnsFilmes.presentation.website.producer;

import com.crossover.jns.JnsFilmes.business.service.producer.CityService;
import com.crossover.jns.JnsFilmes.business.service.producer.HolidayService;
import com.crossover.jns.JnsFilmes.business.service.producer.StateService;
import com.crossover.jns.JnsFilmes.config.Messages;
import com.crossover.jns.JnsFilmes.exceptions.*;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.CityDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.MonthDayHolidayDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.MonthDayHolidayResultListDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.StateDto;
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
@RequestMapping("/holidays")
public class HolidayController {

    private static final String AJAX_HEADER_NAME = "X-Requested-With";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @Autowired
    private Messages messages;

    // Retrieves all saved entities as DTOs
    @GetMapping({"", "/"})
    public String listAll(Model model) throws WebsiteException {
        try {
            MonthDayHolidayResultListDto holidays = holidayService.getHolidays();
            model.addAttribute("year", holidays.getYear());
            model.addAttribute("holidays", holidays.getHolidays());
            return "holidays";
        } catch (NotFoundException e) {
            throw WebsiteException.NotFound();
        } catch (ProducerApiException e) {
            throw WebsiteException.Internal(e);
        }
    }

    // Edit an entity by its ID
    @GetMapping("/edit/{id}")
    public String editEntity(@PathVariable Long id, Model model) throws WebsiteException {
        try {
            MonthDayHolidayDto entity = holidayService.getHoliday(id);
            model.addAttribute("monthDayHolidayDto", entity);
            return "holidays-edit";
        } catch (NotFoundException e) {
            throw WebsiteException.NotFound();
        } catch (ProducerApiException e) {
            throw WebsiteException.Internal(e);
        }
    }

    // Delete an entity by its ID
    @GetMapping("/delete/{id}")
    public String deleteEntity(@PathVariable Long id) throws WebsiteException {
        try {
            holidayService.deleteHoliday(id);
        } catch (ProducerApiException e) {
            throw WebsiteException.Internal(e);
        }
        return "redirect:/holidays?deleted";
    }

    // Create new entity action
    @GetMapping("/add")
    public String createEntity(Model model) throws InvalidDtoException {
        model.addAttribute("monthDayHolidayDto", new MonthDayHolidayDto());
        return "holidays-edit";
    }

    // Save action from the form. It either creates or updates an entity
    @PostMapping("/save")
    public String saveEntityPost(@Valid MonthDayHolidayDto monthDayHolidayDto,
                                 BindingResult bindingResult,
                                 HttpServletRequest req) throws WebsiteException {

        if (bindingResult.hasErrors() || !validateEntity(monthDayHolidayDto, bindingResult, req)) {
            return "holidays-edit";
        }

        boolean isAdd = monthDayHolidayDto.getId() == null;
        try {
            if (isAdd) {
                holidayService.addHoliday(monthDayHolidayDto);
            } else {
                holidayService.updateHoliday(monthDayHolidayDto);
            }
        } catch (Exception e) {
            throw WebsiteException.Internal(e);
        }

        if (isAdd) {
            return "redirect:/holidays?created";
        } else {
            return "redirect:/holidays?updated";
        }
    }

    private boolean validateEntity(MonthDayHolidayDto holiday, BindingResult bindingResult, HttpServletRequest req) {
        boolean temCity = holiday.getCity() != null && !holiday.getCity().isEmpty();
        boolean temState = holiday.getState() != null && !holiday.getState().isEmpty();
        if (temState && !temCity) {
            rejectBindingValue(bindingResult, "city", messages.get("error_holiday_missingcity", req));
            return false;
        }
        return true;
    }

    protected void rejectBindingValue(BindingResult bindingResult, String field, String reason) {
        bindingResult.rejectValue(field, "error.holiday", reason);
    }

    @GetMapping(path = {"/add", "/save", "/edit/{id}"}, params = {"obterStates"})
    public String obterStates(@RequestParam(value = "obterStates") String obterStates,
                              Model model,
                              HttpServletRequest request) {

        List<StateDto> states;
        try {
            states = stateService.getStatesByCountry(obterStates);
        } catch (NotFoundException | ProducerApiException e) {
            states = new ArrayList<>();
        }
        model.addAttribute("states", states);
        if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
            return "holidays-edit :: state-fragment";
        } else {
            return "holidays-edit";
        }
    }

    @GetMapping(path = {"/add", "/save", "/edit/{id}"}, params = {"obterCities"})
    public String obterCities(@RequestParam(value = "obterCities") String obterCities,
                              Model model,
                              HttpServletRequest request) {

        List<CityDto> cities;
        try {
            cities = cityService.getCitiesByState(obterCities);
        } catch (NotFoundException | ProducerApiException e) {
            cities = new ArrayList<>();
        }
        model.addAttribute("cities", cities);
        if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
            return "holidays-edit :: city-fragment";
        } else {
            return "holidays-edit";
        }
    }
}

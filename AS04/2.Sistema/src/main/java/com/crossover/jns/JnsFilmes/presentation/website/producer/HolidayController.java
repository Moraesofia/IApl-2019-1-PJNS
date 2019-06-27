package com.crossover.jns.JnsFilmes.presentation.website.producer;

import com.crossover.jns.JnsFilmes.business.service.producer.HolidayService;
import com.crossover.jns.JnsFilmes.config.Messages;
import com.crossover.jns.JnsFilmes.exceptions.*;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.HolidayDto;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.HolidaysDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;

@Controller
@RequestMapping("/holidays")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @Autowired
    private Messages messages;

    // Retrieves all saved entities as DTOs
    @GetMapping({"", "/"})
    public String listAll(Model model) throws WebsiteException {
        try {
            HolidaysDto holidays = holidayService.getHolidays();
            model.addAttribute("year", holidays.getYear());
            model.addAttribute("holidays", holidays.getHolidays());
            return "holidays";
        } catch (ProducerApiException e) {
            throw WebsiteException.Internal(e);
        }
    }

    // Edit an entity by its ID
    @GetMapping("/edit/{id}")
    public String editEntity(@PathVariable Long id, Model model) throws WebsiteException {
        try {
            HolidayDto entity = holidayService.getHoliday(id);
            model.addAttribute("holidayDto", entity);
            return "holidays-edit";
        } catch (NotFoundException e) {
            throw WebsiteException.NotFound();
        } catch (ProducerApiException e) {
            throw WebsiteException.Internal(e);
        }
    }

    // TODO: Make this when producer the API supports it
    // Delete an entity by its ID
//    @GetMapping("/delete/{id}")
//    public String deleteEntity(@PathVariable TId id) throws WebsiteException {
//        try {
//            holidayService.deleteById(id);
//        } catch (DataIntegrityViolationException ex) {
//            return "redirect:/holidays?cantDeleteBecauseItsUsed";
//        } catch (NotFoundException e) {
//            throw WebsiteException.NotFound();
//        } catch (PersistenceException e) {
//            throw WebsiteException.Internal(e);
//        }
//        return "redirect:/holidays?deleted";
//    }

    // Create new entity action
    @GetMapping("/add")
    public String createEntity(Model model) throws InvalidDtoException {
        model.addAttribute("holidayDto", new HolidayDto());
        return "holidays-edit";
    }

    // Save action from the form. It either creates or updates an entity
    @PostMapping("/save")
    public String saveEntityPost(@Valid HolidayDto holidayDto,
                                 BindingResult bindingResult,
                                 HttpServletRequest req) throws WebsiteException {
        if (bindingResult.hasErrors() || !validateEntity(holidayDto, bindingResult, req)) {
            return "holidays-edit";
        }

        boolean isNew = holidayDto.getId() == null || holidayDto.getId() == 0;
        try {
            HolidaysDto holidays = new HolidaysDto();
            holidays.setHolidays(Collections.singletonList(holidayDto));
            holidayService.saveHolidays(holidays);
        } catch (InvalidDtoException e) {
            rejectBindingValue(bindingResult, e.getField(), e.getMessage());
            return "holidays-edit";
        } catch (Exception e) {
            throw WebsiteException.Internal(e);
        }

        if (isNew) {
            return "redirect:/holidays?created";
        } else {
            return "redirect:/holidays?updated";
        }
    }

    private boolean validateEntity(HolidayDto holiday, BindingResult bindingResult, HttpServletRequest req) {
        if (holiday.getCity().isEmpty() && holiday.getCountry().isEmpty()) {
            rejectBindingValue(bindingResult, "country", messages.get("error_holiday_emptycitycountry", req));
            rejectBindingValue(bindingResult, "city", messages.get("error_holiday_atleastone", req));
            return false;
        }
        if (!holiday.getCity().isEmpty() && !holiday.getCountry().isEmpty()) {
            rejectBindingValue(bindingResult, "country", messages.get("error_holiday_bothcitycountry", req));
            rejectBindingValue(bindingResult, "city", messages.get("error_holiday_bothcitycountry", req));
            return false;
        }
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate dt = LocalDate.parse(holiday.getDate(), fomatter);
        } catch (DateTimeParseException e) {
            rejectBindingValue(bindingResult, "date", messages.get("error_holiday_dateformat", req));
            return false;
        }
        return true;
    }

    protected void rejectBindingValue(BindingResult bindingResult, String field, String reason) {
        bindingResult.rejectValue(field, "error.holiday", reason);
    }
}

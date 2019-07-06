package com.crossover.jns.JnsFilmes.presentation.website.producer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crossover.jns.JnsFilmes.business.service.producer.CountryService;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.ProducerApiException;
import com.crossover.jns.JnsFilmes.exceptions.WebsiteException;
import com.crossover.jns.JnsFilmes.presentation.dto.producer.CountryDto;

@Controller
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping({ "", "/" })
	public String listAll(Model model) throws WebsiteException {
		try {
			List<CountryDto> countries = countryService.getCountries();
			model.addAttribute("countries", countries);
			return "countries";
		} catch (NotFoundException e) {
			throw WebsiteException.NotFound();
		} catch (ProducerApiException e) {
			throw WebsiteException.Internal(e);
		}
	}
}

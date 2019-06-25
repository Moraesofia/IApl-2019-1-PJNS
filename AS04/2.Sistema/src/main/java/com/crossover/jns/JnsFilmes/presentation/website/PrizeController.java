package com.crossover.jns.JnsFilmes.presentation.website;

import com.crossover.jns.JnsFilmes.business.entity.Prize;
import com.crossover.jns.JnsFilmes.business.entity.Prize;
import com.crossover.jns.JnsFilmes.business.enums.CategoryEnum;
import com.crossover.jns.JnsFilmes.business.service.*;
import com.crossover.jns.JnsFilmes.data.repository.PrizeRepository;
import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.presentation.dto.PrizeDto;
import com.crossover.jns.JnsFilmes.presentation.dto.PrizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/prizes")
public class PrizeController extends WebsiteEntityControllerBase<Prize, Long, PrizeRepository, PrizeDto> {

    @Autowired
    private PrizeService prizeService;
    //
    @Autowired
    private PersonService personService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private AwardService awardService;

    @Override
    protected String getControllerBasePath() {
        return "/prizes";
    }

    @Override
    protected EntityServiceBase<Prize, Long, PrizeRepository> getEntityService() {
        return prizeService;
    }

    @Override
    protected PrizeDto convertToDto(Prize prize) {
        return PrizeDto.fromPrize(prize);
    }

    @Override
    protected Prize convertToEntity(PrizeDto prizeDto) throws PersistenceException, InvalidDtoException {
        return prizeDto.toPrize(personService, filmService, awardService);
    }

    @Override
    protected List<PrizeDto> findAllDto() throws PersistenceException {
        return prizeService.findAllDto();
    }

    @Override
    protected Long getDtoId(PrizeDto prizeDto) {
        return prizeDto.getId();
    }

    @Override
    protected Long getEntityId(Prize prize) {
        return prize.getId();
    }

    @Override
    protected String getEntityName() {
        return Prize.class.getSimpleName();
    }

    @Override
    protected Prize getNewEntity() {
        return new Prize();
    }

    @Override
    protected PrizeDto getNewDto() {
        return new PrizeDto();
    }

    @Override
    protected boolean validateEntity(@Valid PrizeDto prizeDto, BindingResult bindingResult) {
        if (Arrays.stream(CategoryEnum.values()).noneMatch(v -> Objects.equals(v.name(), prizeDto.getCategory()))) {
            rejectBindingValue(bindingResult, "category", "unknown category");
            return false;
        }
        return true;
    }

}

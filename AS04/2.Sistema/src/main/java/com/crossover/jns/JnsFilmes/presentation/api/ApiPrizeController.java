package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.Prize;
import com.crossover.jns.JnsFilmes.business.service.*;
import com.crossover.jns.JnsFilmes.data.repository.PrizeRepository;
import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.presentation.dto.PrizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prizes")
public class ApiPrizeController extends ApiEntityControllerBase<Prize, Long, PrizeRepository, PrizeDto> {

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private PersonService personService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private AwardService awardService;

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
    protected Long getDtoId(PrizeDto prizeDto) {
        return prizeDto.getId();
    }

    @Override
    protected List<PrizeDto> findAllDto() throws PersistenceException {
        return prizeService.findAllDto();
    }

    @Override
    protected String getEntityName() {
        return Prize.class.getSimpleName();
    }
}

package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.Prize;
import com.crossover.jns.JnsFilmes.business.service.*;
import com.crossover.jns.JnsFilmes.data.repository.PrizeRepository;
import com.crossover.jns.JnsFilmes.exceptions.InvalidDtoException;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.exceptions.RestApiException;
import com.crossover.jns.JnsFilmes.presentation.dto.PrizeDto;
import com.crossover.jns.JnsFilmes.presentation.dto.PrizeDto;
import com.crossover.jns.JnsFilmes.presentation.dto.RespostaSimplesDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/prize")
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

    /**
     * Get all prizes
     */
    @ApiOperation(value = "Get all", notes = "Gets all prizes")
    @GetMapping("prizes")
    @Override
    public List<PrizeDto> getAll() throws RestApiException {
        return super.getAll();
    }

    /**
     * Get prize by ID
     */
    @ApiOperation(value = "Get by ID", notes = "Gets an existing prize by its ID")
    @GetMapping("prize/{id}")
    @Override
    public PrizeDto getById(Long id) throws RestApiException {
        return super.getById(id);
    }

    /**
     * Add new prize
     */
    @ApiOperation(value = "Add new", notes = "Add a new prize. The new prize's ID must be omitted or null.")
    @PostMapping("prize")
    @Override
    public PrizeDto post(@Valid PrizeDto prizeDto) throws RestApiException {
        return super.post(prizeDto);
    }

    /**
     * Edit/replace existing prize
     */
    @ApiOperation(value = "Edit", notes = "Edits (replaces) the prize with the given ID")
    @PutMapping("prize/{id}")
    @Override
    public PrizeDto put(@Valid PrizeDto prizeDto, Long id) throws RestApiException {
        return super.put(prizeDto, id);
    }

    /**
     * Delete existing prize
     */
    @ApiOperation(value = "Delete", notes = "Deletes the prize with the given ID")
    @DeleteMapping("prize/{id}")
    @Override
    public RespostaSimplesDto delete(Long id) throws RestApiException {
        return super.delete(id);
    }
}

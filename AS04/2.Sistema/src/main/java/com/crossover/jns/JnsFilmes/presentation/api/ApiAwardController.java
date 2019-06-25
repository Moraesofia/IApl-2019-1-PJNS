package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.Award;
import com.crossover.jns.JnsFilmes.business.service.AwardService;
import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.data.repository.AwardRepository;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.exceptions.RestApiException;
import com.crossover.jns.JnsFilmes.presentation.dto.AwardDto;
import com.crossover.jns.JnsFilmes.presentation.dto.RespostaSimplesDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiAwardController extends ApiEntityControllerBase<Award, Long, AwardRepository, AwardDto> {

    @Autowired
    private AwardService awardService;

    @Override
    protected EntityServiceBase<Award, Long, AwardRepository> getEntityService() {
        return awardService;
    }

    @Override
    protected AwardDto convertToDto(Award award) {
        return AwardDto.fromAward(award);
    }

    @Override
    protected Award convertToEntity(AwardDto awardDto) {
        return awardDto.toAward();
    }

    @Override
    protected Long getDtoId(AwardDto awardDto) {
        return awardDto.getId();
    }

    @Override
    protected List<AwardDto> findAllDto() throws PersistenceException {
        return awardService.findAllDto();
    }

    @Override
    protected String getEntityName() {
        return Award.class.getSimpleName();
    }

    /**
     * Get all awards
     */
    @ApiOperation(value = "Get all", notes = "Gets all awards")
    @GetMapping("awards")
    @Override
    public List<AwardDto> getAll() throws RestApiException {
        return super.getAll();
    }

    /**
     * Get award by ID
     */
    @ApiOperation(value = "Get by ID", notes = "Gets an existing award by its ID")
    @GetMapping("award/{id}")
    @Override
    public AwardDto getById(Long id) throws RestApiException {
        return super.getById(id);
    }

    /**
     * Add new award
     */
    @ApiOperation(value = "Add new", notes = "Add a new award. The new award's ID must be omitted or null.")
    @PostMapping("award")
    @Override
    public AwardDto post(@Valid AwardDto awardDto) throws RestApiException {
        return super.post(awardDto);
    }

    /**
     * Edit/replace existing award
     */
    @ApiOperation(value = "Edit", notes = "Edits (replaces) the award with the given ID")
    @PutMapping("award/{id}")
    @Override
    public AwardDto put(@Valid AwardDto awardDto, Long id) throws RestApiException {
        return super.put(awardDto, id);
    }

    /**
     * Delete existing award
     */
    @ApiOperation(value = "Delete", notes = "Deletes the award with the given ID")
    @DeleteMapping("award/{id}")
    @Override
    public RespostaSimplesDto delete(Long id) throws RestApiException {
        return super.delete(id);
    }
}

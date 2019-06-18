package com.crossover.jns.JnsFilmes.presentation.website;

import com.crossover.jns.JnsFilmes.business.entity.Award;
import com.crossover.jns.JnsFilmes.business.service.AwardService;
import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.data.repository.AwardRepository;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.exceptions.WebsiteException;
import com.crossover.jns.JnsFilmes.presentation.dto.AwardDto;
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
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/awards")
public class AwardController extends WebsiteEntityControllerBase<Award, Long, AwardRepository, AwardDto> {

    @Autowired
    private AwardService awardService;

    @Override
    protected String getControllerBasePath() {
        return "/awards";
    }

    @Override
    protected EntityServiceBase<Award, Long, AwardRepository> getEntityService() {
        return awardService;
    }

    @Override
    protected AwardDto convertToDto(Award award) {
        return AwardDto.fromAward(award);
    }

    @Override
    protected Award convertToEntity(AwardDto awardDto) throws PersistenceException {
        return awardDto.toAward();
    }

    @Override
    protected List<AwardDto> findAllDto() throws PersistenceException {
        return awardService.findAllDto();
    }

    @Override
    protected Long getDtoId(AwardDto awardDto) {
        return awardDto.getId();
    }

    @Override
    protected Long getEntityId(Award award) {
        return award.getId();
    }

    @Override
    protected String getEntityName() {
        return Award.class.getSimpleName();
    }

    @Override
    protected Award getNewEntity() {
        return new Award();
    }

}

package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.Award;
import com.crossover.jns.JnsFilmes.business.entity.User;
import com.crossover.jns.JnsFilmes.business.service.AwardService;
import com.crossover.jns.JnsFilmes.business.service.EntityServiceBase;
import com.crossover.jns.JnsFilmes.business.service.UserService;
import com.crossover.jns.JnsFilmes.data.repository.AwardRepository;
import com.crossover.jns.JnsFilmes.presentation.dto.AwardDto;
import com.crossover.jns.JnsFilmes.presentation.dto.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/awards")
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
    protected List<AwardDto> findAllDto() {
        return awardService.findAllDto();
    }

    @Override
    protected String getEntityName() {
        return Award.class.getSimpleName();
    }
}

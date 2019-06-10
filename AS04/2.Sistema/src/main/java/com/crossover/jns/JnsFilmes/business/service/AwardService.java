package com.crossover.jns.JnsFilmes.business.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.jns.JnsFilmes.business.entity.Award;
import com.crossover.jns.JnsFilmes.data.repository.AwardRepository;
import com.crossover.jns.JnsFilmes.presentation.dto.AwardDto;

@Service
@Transactional
public class AwardService extends EntityServiceBase<Award, Long, AwardRepository> {

	@Autowired
	private AwardRepository awardRepository;

	public List<AwardDto> findAllDto() {
		return findAll().stream().map(AwardDto::fromAward).collect(Collectors.toList());
	}
}

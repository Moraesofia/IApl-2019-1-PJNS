package com.crossover.jns.JnsFilmes.business.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.jns.JnsFilmes.business.entity.Award;
import com.crossover.jns.JnsFilmes.data.repository.AwardRepository;

@Service
@Transactional
public class AwardService extends EntityServiceBase<Award, Long, AwardRepository> {

}

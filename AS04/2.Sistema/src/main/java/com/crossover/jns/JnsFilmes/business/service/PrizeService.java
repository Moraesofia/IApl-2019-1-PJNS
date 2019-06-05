package com.crossover.jns.JnsFilmes.business.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.jns.JnsFilmes.business.entity.Prize;
import com.crossover.jns.JnsFilmes.data.repository.PrizeRepository;

@Service
@Transactional
public class PrizeService extends EntityServiceBase<Prize, Long, PrizeRepository> {

}

package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.business.enums.CategoryEnum;
import com.crossover.jns.JnsFilmes.presentation.dto.PrizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.jns.JnsFilmes.business.entity.Prize;
import com.crossover.jns.JnsFilmes.data.repository.PrizeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrizeService extends EntityServiceBase<Prize, Long, PrizeRepository> {

    @Autowired
    private PrizeRepository prizeRepository;

    public List<Prize> findAll() {
        return prizeRepository.findAll();
    }

    public Prize findById(long id) {
        Optional<Prize> prize = prizeRepository.findById(id);
        return prize.get();
    }

    public void deleteById(long id) {
        prizeRepository.deleteById(id);
    }

    public Prize save(Prize prize) {
        return prizeRepository.save(prize);
    }

    public List<String> getCategoriesText() {
        CategoryEnum[] category = CategoryEnum.values();
        return Arrays.stream(category).map(CategoryEnum::getText).collect(Collectors.toList());
    }

    public Collection<Prize> findByCategory(CategoryEnum category) {
        if (category == null) {
            return Collections.emptyList();
        }
        return prizeRepository.findByCategory(category);
    }

    public List<PrizeDto> findAllDto() {
        return findAll().stream().map(PrizeDto::fromPrize).collect(Collectors.toList());
    }

}

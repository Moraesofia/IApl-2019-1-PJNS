package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.business.enums.CategoryEnum;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
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

    public List<String> getCategoriesText() {
        CategoryEnum[] category = CategoryEnum.values();
        return Arrays.stream(category).map(CategoryEnum::name).collect(Collectors.toList());
    }

    public Collection<Prize> findByCategory(CategoryEnum category) throws PersistenceException {
        try {
            if (category == null) {
                return Collections.emptyList();
            }
            return prizeRepository.findByCategory(category);
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

    public List<PrizeDto> findAllDto() throws PersistenceException {
        return findAll().stream().map(PrizeDto::fromPrize).collect(Collectors.toList());
    }

    @Override
    public <S extends Prize> S save(S item) throws PersistenceException {
        // Remove winner if category is films only
        if (item.getCategory().equals(CategoryEnum.FILM) || item.getCategory().equals(CategoryEnum.SCRIPT)) {
            item.setWinner(null);
        }
        return super.save(item);
    }
}

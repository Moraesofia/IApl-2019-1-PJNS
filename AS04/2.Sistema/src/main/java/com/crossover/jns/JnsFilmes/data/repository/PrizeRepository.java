package com.crossover.jns.JnsFilmes.data.repository;

import com.crossover.jns.JnsFilmes.business.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crossover.jns.JnsFilmes.business.entity.Prize;

import java.util.Collection;

@Repository
public interface PrizeRepository extends JpaRepository<Prize, Long> {

    Collection<Prize> findByCategory(CategoryEnum category);
}

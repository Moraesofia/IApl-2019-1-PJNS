package com.crossover.jns.JnsFilmes.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crossover.jns.JnsFilmes.business.entity.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

}

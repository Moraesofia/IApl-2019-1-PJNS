package com.crossover.jns.JnsFilmes.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crossover.jns.JnsFilmes.business.entity.Film;

import java.util.Collection;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    Collection<Film> findByDirector_Id(Long idDirector);

    Collection<Film> findByActress_Id(Long idActress);

    Collection<Film> findByActor_Id(Long idActor);

}

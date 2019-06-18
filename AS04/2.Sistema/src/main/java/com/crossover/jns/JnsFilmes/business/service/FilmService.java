package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.presentation.dto.FilmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.data.repository.FilmRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmService extends EntityServiceBase<Film, Long, FilmRepository> {

    @Autowired
    private FilmRepository filmRepository;

    public Collection<Film> findAllByDirector(Long idDirector) throws PersistenceException {
        try {
            return filmRepository.findByDirector_Id(idDirector);
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

    public Collection<Film> findAllByActress(Long idActress) throws PersistenceException {
        try {
            return filmRepository.findByActress_Id(idActress);
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

    public Collection<Film> findAllByActor(Long idActor) throws PersistenceException {
        try {
            return filmRepository.findByActor_Id(idActor);
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

    public List<FilmDto> findAllDto() throws PersistenceException {
        return findAll().stream().map(FilmDto::fromFilm).collect(Collectors.toList());
    }

}

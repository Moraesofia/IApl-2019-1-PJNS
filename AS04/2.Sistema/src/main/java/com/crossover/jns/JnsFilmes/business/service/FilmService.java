package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.presentation.dto.FilmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.data.repository.FilmRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmService extends EntityServiceBase<Film, Long, FilmRepository> {

    @Autowired
    private FilmRepository filmRepository;

    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    public Film findById(long id) {
        Optional<Film> film = filmRepository.findById(id);
        return film.get();
    }

    public void deleteById(long id) {
        filmRepository.deleteById(id);
    }

    public Film save(Film film) {
        return filmRepository.save(film);
    }

    public Collection<Film> findByDirector(Long idDirector) {
        return filmRepository.findByDirector_Id(idDirector);
    }

    public Collection<Film> findByActress(Long idActress) {
        return filmRepository.findByActress_Id(idActress);
    }

    public Collection<Film> findByActor(Long idActor) {
        return filmRepository.findByActor_Id(idActor);
    }

    public List<FilmDto> findAllDto() {
        return findAll().stream().map(FilmDto::fromFilm).collect(Collectors.toList());
    }

}

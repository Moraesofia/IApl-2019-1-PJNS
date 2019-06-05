package com.crossover.jns.JnsFilmes.business.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.data.repository.FilmRepository;

@Service
@Transactional
public class FilmService extends EntityServiceBase<Film, Long, FilmRepository> {

}

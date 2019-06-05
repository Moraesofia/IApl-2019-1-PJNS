package com.crossover.jns.JnsFilmes.business.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.data.repository.PersonRepository;

@Service
@Transactional
public class PersonService extends EntityServiceBase<Person, Long, PersonRepository> {

}

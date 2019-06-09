package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.business.enums.GenreEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;
import com.crossover.jns.JnsFilmes.presentation.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.data.repository.PersonRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService extends EntityServiceBase<Person, Long, PersonRepository> {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findByid(long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.get();
    }

    public void deleteById(long id) {
        personRepository.deleteById(id);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<String> getJobsText() {
        JobEnum[] jobs = JobEnum.values();
        return Arrays.stream(jobs).map(JobEnum::getText).collect(Collectors.toList());
    }

    public List<String> getGenresText() {
        GenreEnum[] genres = GenreEnum.values();
        return Arrays.stream(genres).map(GenreEnum::getText).collect(Collectors.toList());
    }

    public Collection<Person> findByJob(JobEnum job) {
        if (job == null) {
            return Collections.emptyList();
        }
        return personRepository.findByJob(job);
    }

    public List<PersonDto> findAllDirectorsDto() {
        return findByJob(JobEnum.DIRECTOR).stream().map(PersonDto::fromPerson).collect(Collectors.toList());
    }

    public List<PersonDto> findAllActressesDto() {
        return findByJob(JobEnum.ACTRESS).stream().map(PersonDto::fromPerson).collect(Collectors.toList());
    }

    public List<PersonDto> findAllActorsDto() {
        return findByJob(JobEnum.ACTOR).stream().map(PersonDto::fromPerson).collect(Collectors.toList());
    }

    public List<PersonDto> findAllDto() {
        return findAll().stream().map(PersonDto::fromPerson).collect(Collectors.toList());
    }
    
}

package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.business.enums.GenderEnum;
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

    public List<String> getJobsText() {
        JobEnum[] jobs = JobEnum.values();
        return Arrays.stream(jobs).map(JobEnum::name).collect(Collectors.toList());
    }

    public List<String> getGendersText() {
        GenderEnum[] genders = GenderEnum.values();
        return Arrays.stream(genders).map(GenderEnum::name).collect(Collectors.toList());
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

    public List<PersonDto> findAllWritersDto() {
        return findByJob(JobEnum.WRITER).stream().map(PersonDto::fromPerson).collect(Collectors.toList());
    }

    public List<PersonDto> findAllActorsAndActressesDto() {
        return findByJob(JobEnum.ACTOR_OR_ACTRESS)
                .stream()
                .map(PersonDto::fromPerson).collect(Collectors.toList());
    }

    public List<PersonDto> findAllActressesDto() {
        return findByJob(JobEnum.ACTOR_OR_ACTRESS)
                .stream()
                .filter(p -> p.getGender() == GenderEnum.FEMALE)
                .map(PersonDto::fromPerson).collect(Collectors.toList());
    }

    public List<PersonDto> findAllActorsDto() {
        return findByJob(JobEnum.ACTOR_OR_ACTRESS)
                .stream()
                .filter(p -> p.getGender() == GenderEnum.MALE)
                .map(PersonDto::fromPerson)
                .collect(Collectors.toList());
    }

    public List<PersonDto> findAllDto() {
        return findAll().stream().map(PersonDto::fromPerson).collect(Collectors.toList());
    }

}

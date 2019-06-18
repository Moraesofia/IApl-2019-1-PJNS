package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.business.enums.GenderEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
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

    public Collection<Person> findAllByJob(JobEnum job) throws PersistenceException {
        if (job == null) {
            return Collections.emptyList();
        }
        try {
            return personRepository.findByJob(job);
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

    public List<PersonDto> findAllDirectorsDto() throws PersistenceException {
        try {
            return findAllByJob(JobEnum.DIRECTOR).stream()
                    .map(PersonDto::fromPerson).collect(Collectors.toList());
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

    public List<PersonDto> findAllWritersDto() throws PersistenceException {
        try {
            return findAllByJob(JobEnum.WRITER).stream()
                    .map(PersonDto::fromPerson).collect(Collectors.toList());
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

    public List<PersonDto> findAllActorsAndActressesDto() throws PersistenceException {
        try {
            return findAllByJob(JobEnum.ACTOR_OR_ACTRESS).stream()
                    .map(PersonDto::fromPerson).collect(Collectors.toList());
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

    public List<PersonDto> findAllActressesDto() throws PersistenceException {
        try {
            return findAllByJob(JobEnum.ACTOR_OR_ACTRESS).stream()
                    .filter(p -> p.getGender() == GenderEnum.FEMALE)
                    .map(PersonDto::fromPerson).collect(Collectors.toList());
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

    public List<PersonDto> findAllActorsDto() throws PersistenceException {
        try {
            return findAllByJob(JobEnum.ACTOR_OR_ACTRESS)
                    .stream()
                    .filter(p -> p.getGender() == GenderEnum.MALE)
                    .map(PersonDto::fromPerson)
                    .collect(Collectors.toList());
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

    public List<PersonDto> findAllDto() throws PersistenceException {
        return findAll().stream().map(PersonDto::fromPerson).collect(Collectors.toList());
    }

}

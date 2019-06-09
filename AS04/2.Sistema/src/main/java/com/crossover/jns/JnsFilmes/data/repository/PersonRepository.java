package com.crossover.jns.JnsFilmes.data.repository;

import com.crossover.jns.JnsFilmes.business.enums.JobEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crossover.jns.JnsFilmes.business.entity.Person;

import java.util.Collection;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Collection<Person> findByJob(JobEnum job);
}

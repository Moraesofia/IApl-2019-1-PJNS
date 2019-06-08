package com.crossover.jns.JnsFilmes.data.config;

import com.crossover.jns.JnsFilmes.business.entity.Film;
import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.entity.User;
import com.crossover.jns.JnsFilmes.business.enums.GenreEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;
import com.crossover.jns.JnsFilmes.business.service.FilmService;
import com.crossover.jns.JnsFilmes.business.service.PersonService;
import com.crossover.jns.JnsFilmes.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * Adds initial data to the database when the system is initialized.
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserService userService;

    @Autowired
    private PersonService personService;

    @Autowired
    private FilmService filmService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!alreadySetup) {
            createInitialUsers();
            createInitalPersons();
            alreadySetup = true;
        }
    }

    private void createInitialUsers() {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        user1 = userService.save(user1);
    }

    private void createInitalPersons(){
        Person person1 = new Person();
        person1.setName("Bette Davis");
        person1.setJob(JobEnum.ACTRESS);
        person1.setGenre(GenreEnum.F);
        person1.setBirth("05 / 04 / 1908");
        person1 = personService.save(person1);

        Person person2 = new Person();
        person2.setName("Joan Crawford");
        person2.setJob(JobEnum.ACTRESS);
        person2.setGenre(GenreEnum.F);
        person2.setBirth("23 / 03 / 1904");
        person2 = personService.save(person2);

        Person person3 = new Person();
        person3.setName("Robert Aldrich");
        person3.setJob(JobEnum.DIRECTOR);
        person3.setGenre(GenreEnum.M);
        person3.setBirth("09 / 08 / 1918");
        person3 = personService.save(person3);

        Person person4 = new Person();
        person4.setName("Victor Buono");
        person4.setJob(JobEnum.ACTOR);
        person4.setGenre(GenreEnum.M);
        person4.setBirth("03 / 02 / 1938");
        person4 = personService.save(person4);

        Film film = new Film();
        film.setTitle("What Ever Happened to Baby Jane?");
        film.setGenre("Horror");
        film.setYear(1962);
        film.setDirector(person3);
        film.setActress(person1);
        film.setActor(person4);
        film = filmService.save(film);
    }


}

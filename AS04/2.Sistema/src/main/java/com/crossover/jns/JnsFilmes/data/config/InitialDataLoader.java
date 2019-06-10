package com.crossover.jns.JnsFilmes.data.config;

import com.crossover.jns.JnsFilmes.business.entity.*;
import com.crossover.jns.JnsFilmes.business.enums.CategoryEnum;
import com.crossover.jns.JnsFilmes.business.enums.GenderEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;
import com.crossover.jns.JnsFilmes.business.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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

    @Autowired
    private AwardService awardService;

    @Autowired
    private PrizeService prizeService;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!alreadySetup) {
            createInitialUsers();
            createEntities();
            alreadySetup = true;
        }
    }

    private void createInitialUsers() {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        user1 = userService.save(user1);
    }

    private void createEntities() {
        Person person1 = new Person();
        person1.setName("Bette Davis");
        person1.setJob(JobEnum.ACTOR_OR_ACTRESS);
        person1.setGender(GenderEnum.FEMALE);
        person1.setBirth("05 / 04 / 1908");
        person1 = personService.save(person1);

        Person person2 = new Person();
        person2.setName("Joan Crawford");
        person2.setJob(JobEnum.ACTOR_OR_ACTRESS);
        person2.setGender(GenderEnum.FEMALE);
        person2.setBirth("23 / 03 / 1904");
        person2 = personService.save(person2);

        Person person3 = new Person();
        person3.setName("Robert Aldrich");
        person3.setJob(JobEnum.DIRECTOR);
        person3.setGender(GenderEnum.MALE);
        person3.setBirth("09 / 08 / 1918");
        person3 = personService.save(person3);

        Person person4 = new Person();
        person4.setName("Victor Buono");
        person4.setJob(JobEnum.ACTOR_OR_ACTRESS);
        person4.setGender(GenderEnum.MALE);
        person4.setBirth("03 / 02 / 1938");
        person4 = personService.save(person4);

        Person person5 = new Person();
        person5.setName("William Wyler");
        person5.setJob(JobEnum.DIRECTOR);
        person5.setGender(GenderEnum.MALE);
        person5.setBirth("01 / 07 / 1902");
        person5 = personService.save(person5);

        Person person6 = new Person();
        person6.setName("Henry Fonda");
        person6.setJob(JobEnum.ACTOR_OR_ACTRESS);
        person6.setGender(GenderEnum.MALE);
        person6.setBirth("16 / 04 / 1905");
        person6 = personService.save(person6);

        Film film = new Film();
        film.setTitle("What Ever Happened to Baby Jane?");
        film.setGenre("Horror");
        film.setYear(1962);
        film.setDirector(person3);
        film.setActress(person1);
        film.setActor(person4);
        film = filmService.save(film);

        Film film2 = new Film();
        film2.setTitle("Jezebel");
        film2.setGenre("Drama");
        film2.setYear(1938);
        film2.setDirector(person5);
        film2.setActress(person1);
        film2.setActor(person6);
        film2 = filmService.save(film2);

        Award award1 = new Award();
        award1.setName("The 11th Oscars");
        award1.setYear(1938);
        award1 = awardService.save(award1);

        Award award2 = new Award();
        award2.setName("The 6th Venice Film Festival");
        award2.setYear(1938);
        award2 = awardService.save(award2);

        Prize prize = new Prize();
        prize.setAward(award1);
        prize.setWinner(person1);
        prize.setCategory(CategoryEnum.ACTRESS);
        prize.setFilm(film2);
        prize = prizeService.save(prize);

        Prize prize2 = new Prize();
        prize2.setAward(award2);
        prize2.setWinner(person5);
        prize2.setCategory(CategoryEnum.DIRECTOR);
        prize2.setFilm(film2);
        prize2 = prizeService.save(prize2);
    }


}

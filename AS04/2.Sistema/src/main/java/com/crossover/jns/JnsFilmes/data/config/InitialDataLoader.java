package com.crossover.jns.JnsFilmes.data.config;

import com.crossover.jns.JnsFilmes.business.entity.*;
import com.crossover.jns.JnsFilmes.business.enums.CategoryEnum;
import com.crossover.jns.JnsFilmes.business.enums.GenderEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;
import com.crossover.jns.JnsFilmes.business.service.*;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
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
            try {
                createInitialUsers();
                createEntities();
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
            alreadySetup = true;
        }
    }

    private void createInitialUsers() throws PersistenceException {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        user1 = userService.save(user1);
    }

    private void createEntities() throws PersistenceException {
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

        Person person7 = new Person();
        person7.setName("Olivia Colman");
        person7.setJob(JobEnum.ACTOR_OR_ACTRESS);
        person7.setGender(GenderEnum.FEMALE);
        person7.setBirth("30 / 01 / 1974");
        person7 = personService.save(person7);

        Person person8 = new Person();
        person8.setName("Nicholas Hoult");
        person8.setJob(JobEnum.ACTOR_OR_ACTRESS);
        person8.setGender(GenderEnum.MALE);
        person8.setBirth("07 / 12 / 1989");
        person8 = personService.save(person8);

        Person person9 = new Person();
        person9.setName("Yorgos Lanthimos");
        person9.setJob(JobEnum.DIRECTOR);
        person9.setGender(GenderEnum.MALE);
        person9.setBirth("27 / 05 / 1973");
        person9 = personService.save(person9);

        Person person10 = new Person();
        person10.setName("Isabelle Huppert");
        person10.setJob(JobEnum.ACTOR_OR_ACTRESS);
        person10.setGender(GenderEnum.FEMALE);
        person10.setBirth("16 / 03 / 1953");
        person10 = personService.save(person10);

        Person person11 = new Person();
        person11.setName("Laurent Lafitte");
        person11.setJob(JobEnum.ACTOR_OR_ACTRESS);
        person11.setGender(GenderEnum.MALE);
        person11.setBirth("22 / 08 / 1973");
        person11 = personService.save(person11);

        Person person12 = new Person();
        person12.setName("Paul Verhoeven");
        person12.setJob(JobEnum.DIRECTOR);
        person12.setGender(GenderEnum.MALE);
        person12.setBirth("18 / 07 / 1938");
        person12 = personService.save(person12);

        Person person13 = new Person();
        person13.setName("Oliver Assayas");
        person13.setJob(JobEnum.DIRECTOR);
        person13.setGender(GenderEnum.MALE);
        person13.setBirth("25 / 01 / 1955");
        person13 = personService.save(person13);

        Person person14 = new Person();
        person14.setName("Kristen Stewart");
        person14.setJob(JobEnum.ACTOR_OR_ACTRESS);
        person14.setGender(GenderEnum.FEMALE);
        person14.setBirth("09 / 04 / 1990");
        person14 = personService.save(person14);

        Person person15 = new Person();
        person15.setName("Lars Eidinger");
        person15.setJob(JobEnum.ACTOR_OR_ACTRESS);
        person15.setGender(GenderEnum.MALE);
        person15.setBirth("21 / 01 / 1976");
        person15 = personService.save(person15);

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

        Film film3 = new Film();
        film3.setTitle("The Favourite");
        film3.setGenre("Comedy");
        film3.setYear(2018);
        film3.setDirector(person9);
        film3.setActress(person7);
        film3.setActor(person8);
        film3 = filmService.save(film3);

        Film film4 = new Film();
        film4.setTitle("Elle");
        film4.setGenre("Thriller");
        film4.setYear(2016);
        film4.setDirector(person12);
        film4.setActress(person10);
        film4.setActor(person11);
        film4 = filmService.save(film4);

        Film film5 = new Film();
        film5.setTitle("Personal Shopper");
        film5.setGenre("Thriller");
        film5.setYear(2017);
        film5.setDirector(person13);
        film5.setActress(person14);
        film5.setActor(person15);
        film5 = filmService.save(film5);

        Award award1 = new Award();
        award1.setName("The 11th Oscars");
        award1.setYear(1938);
        award1 = awardService.save(award1);

        Award award2 = new Award();
        award2.setName("The 6th Venice Film Festival");
        award2.setYear(1938);
        award2 = awardService.save(award2);

        Award award3 = new Award();
        award3.setName("The 72nd BAFTA");
        award3.setYear(2019);
        award3 = awardService.save(award3);

        Award award4 = new Award();
        award4.setName("The 74th Golden Globes");
        award4.setYear(2017);
        award4 = awardService.save(award4);

        Award award5 = new Award();
        award5.setName("43e César du cinéma");
        award5.setYear(2018);
        award5 = awardService.save(award5);

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

        Prize prize3 = new Prize();
        prize3.setAward(award3);
        prize3.setWinner(person7);
        prize3.setCategory(CategoryEnum.ACTRESS);
        prize3.setFilm(film3);
        prize3 = prizeService.save(prize3);

        Prize prize4 = new Prize();
        prize4.setAward(award5);
        prize4.setWinner(person14);
        prize4.setCategory(CategoryEnum.ACTRESS);
        prize4.setFilm(film5);
        prize4 = prizeService.save(prize4);

        Prize prize5 = new Prize();
        prize5.setAward(award4);
        prize5.setWinner(person10);
        prize5.setCategory(CategoryEnum.ACTRESS);
        prize5.setFilm(film4);
        prize5 = prizeService.save(prize5);

    }


}

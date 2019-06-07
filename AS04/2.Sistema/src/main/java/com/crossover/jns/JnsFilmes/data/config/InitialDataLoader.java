package com.crossover.jns.JnsFilmes.data.config;

import com.crossover.jns.JnsFilmes.business.entity.Person;
import com.crossover.jns.JnsFilmes.business.entity.User;
import com.crossover.jns.JnsFilmes.business.enums.GenreEnum;
import com.crossover.jns.JnsFilmes.business.enums.JobEnum;
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
    }

}

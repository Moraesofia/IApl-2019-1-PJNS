package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.business.entity.User;
import com.crossover.jns.JnsFilmes.data.repository.UserRepository;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.exceptions.WrongCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService extends EntityServiceBase<User, Long, UserRepository> {

    public User findByUsername(String username) throws NotFoundException, PersistenceException {
        try {
            User entity = repository.findByUsername(username).orElse(null);
            if (entity == null)
                throw new NotFoundException("Couldn't find entity");
            return entity;
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex);
        }
    }

    public User authenticate(String username, String password) throws WrongCredentialsException, PersistenceException, NotFoundException {
        try {
            User user = findByUsername(username);
            if (!Objects.equals(user.getPassword(), password))
                throw new WrongCredentialsException();
            return user;
        } catch (javax.persistence.PersistenceException pex) {
            throw new PersistenceException(pex.getMessage(), pex);
        }
    }

}

package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.business.entity.User;
import com.crossover.jns.JnsFilmes.data.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@Service
@Transactional
public class UserService extends EntityServiceBase<User, Long, UserRepository> {

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    public User authenticate(String username, String password) throws HttpClientErrorException {
        User user = findByUsername(username);
        if (user == null || !Objects.equals(user.getPassword(), password))
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Wrong credentials");
        return user;
    }

}

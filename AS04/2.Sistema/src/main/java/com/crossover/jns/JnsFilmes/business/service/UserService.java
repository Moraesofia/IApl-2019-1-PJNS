package com.crossover.jns.JnsFilmes.business.service;

import com.crossover.jns.JnsFilmes.business.entity.User;
import com.crossover.jns.JnsFilmes.data.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends EntityServiceBase<User, Long, UserRepository> {

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

}

package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.User;
import com.crossover.jns.JnsFilmes.business.service.UserService;
import com.crossover.jns.JnsFilmes.presentation.dto.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserService userService;

    /**
     * Tries to authenticate the posted user.
     *
     * @return The authenticated user if the credentials were valid. An exception if not valid.
     */
    @PostMapping("/users/authenticate")
    public User authenticate(@RequestBody UserCredentialsDto userCredentialsDto) {
        return userService.authenticate(userCredentialsDto.getUsername(), userCredentialsDto.getPassword());
    }

}

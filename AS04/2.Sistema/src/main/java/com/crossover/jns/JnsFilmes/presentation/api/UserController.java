package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.User;
import com.crossover.jns.JnsFilmes.business.service.UserService;
import com.crossover.jns.JnsFilmes.presentation.dto.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UserController {

    // TODO: Configure an interceptor/handler for exceptions, so they are presented
    // in a generic 'response' object with the corresponding data.

    @Autowired
    private UserService userService;

    /**
     * Tries to authenticate the posted user.
     *
     * @return The authenticated user if the credentials were valid.
     */
    @PostMapping("/users/authenticate")
    private User authenticate(@RequestBody UserCredentialsDto userCredentialsDto) {
        User user = userService.findByUsername(userCredentialsDto.getUsername());
        if (user == null || !Objects.equals(user.getPassword(), userCredentialsDto.getPassword()))
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Wrong credentials");
        return user;
    }

}

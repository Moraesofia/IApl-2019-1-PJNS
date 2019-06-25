package com.crossover.jns.JnsFilmes.presentation.api;

import com.crossover.jns.JnsFilmes.business.entity.User;
import com.crossover.jns.JnsFilmes.business.service.UserService;
import com.crossover.jns.JnsFilmes.exceptions.NotFoundException;
import com.crossover.jns.JnsFilmes.exceptions.PersistenceException;
import com.crossover.jns.JnsFilmes.exceptions.RestApiException;
import com.crossover.jns.JnsFilmes.exceptions.WrongCredentialsException;
import com.crossover.jns.JnsFilmes.presentation.dto.UserCredentialsDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired
    private UserService userService;

    /**
     * Tries to authenticate the posted user.
     *
     * @return The authenticated user if the credentials were valid. An exception if not valid.
     */
    @ApiOperation(value = "Authenticate", notes = "Tries to authenticate an user by username and password. Returns the user if the authentication is successful.")
    @PostMapping("/authenticate")
    public User authenticate(@ApiParam(name = "userCredentialsDto", required = true, value = "The credentials used to authenticate")
                             @RequestBody UserCredentialsDto userCredentialsDto) throws RestApiException {
        try {
            return userService.authenticate(userCredentialsDto.getUsername(), userCredentialsDto.getPassword());
        } catch (NotFoundException e) {
            throw new RestApiException(HttpStatus.NOT_FOUND, "Couldn't find User with the Username=" + userCredentialsDto.getUsername());
        } catch (WrongCredentialsException e) {
            throw new RestApiException(HttpStatus.BAD_REQUEST, "Invalid argument(s) - Bad credentials");
        } catch (PersistenceException e) {
            throw new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Persistence error: " + e.getMessage());
        }
    }

}

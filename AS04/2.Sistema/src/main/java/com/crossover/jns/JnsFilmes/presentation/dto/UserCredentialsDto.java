package com.crossover.jns.JnsFilmes.presentation.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * DTO that only contains an User's raw credentials.
 */
public class UserCredentialsDto {

    @ApiModelProperty(required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(required = true)
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

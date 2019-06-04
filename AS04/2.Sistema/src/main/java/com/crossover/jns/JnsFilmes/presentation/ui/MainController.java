package com.crossover.jns.JnsFilmes.presentation.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class MainController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }

}

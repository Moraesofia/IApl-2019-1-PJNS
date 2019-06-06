package com.crossover.jns.JnsFilmes.presentation.ui;

import com.crossover.jns.JnsFilmes.business.entity.User;
import com.crossover.jns.JnsFilmes.business.service.UserService;
import com.crossover.jns.JnsFilmes.presentation.dto.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userCredentialsDto", new UserCredentialsDto());
        return "auth";
    }

    @PostMapping("/login")
    public String loginSubmit(UserCredentialsDto userCredentialsDto) {
        try {
            User user = userService.authenticate(
                    userCredentialsDto.getUsername(),
                    userCredentialsDto.getPassword());
            return "redirect:/home";
        } catch (Exception ex) {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

}

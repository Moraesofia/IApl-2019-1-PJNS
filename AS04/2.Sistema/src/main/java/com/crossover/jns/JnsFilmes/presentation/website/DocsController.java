package com.crossover.jns.JnsFilmes.presentation.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocsController {

    @GetMapping("docs")
    public String docs() {
        return "redirect:/swagger-ui.html";
    }

}

package com.crossover.jns.JnsFilmes.config;

import com.crossover.jns.JnsFilmes.JnsFilmesApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JnsFilmesApplication.class);
    }

}

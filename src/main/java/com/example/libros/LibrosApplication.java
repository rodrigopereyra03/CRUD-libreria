package com.example.libros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LibrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrosApplication.class, args);
    }

}

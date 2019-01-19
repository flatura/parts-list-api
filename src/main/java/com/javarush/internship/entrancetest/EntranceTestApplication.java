package com.javarush.internship.entrancetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EntranceTestApplication {

    public static void main(String[] args) {
        System.out.println("Welcome to Parts list application (API-side) by Morozov Dmitry @2019!");
        System.out.println("Used technologies: Spring Boot, JPA, MySQL and others! (thanks JavaRush!)");
        System.out.println("Ensure the MySQL is running on local port 3306!");
        SpringApplication.run(EntranceTestApplication.class, args);
    }

}


package com.sldpersonal.leitnersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LeitnerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeitnerSystemApplication.class, args);
    }

}

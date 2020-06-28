package com.dk.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DkChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DkChallengeApplication.class, args);
    }

    @Bean
    public SwingDataService swingDataService() {
        return new SwingDataService();
    }

    @Bean
    public SearchUtil searchUtil() {
        return new SearchUtil();
    }

}

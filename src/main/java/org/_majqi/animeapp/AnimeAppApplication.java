package org._majqi.animeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AnimeAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnimeAppApplication.class, args);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
package com.brwnsugr.webice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application { // Main Class of this project
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}



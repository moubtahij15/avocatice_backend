package com.example.userservice;

import com.example.userservice.dtos.UserInputDto;
import com.example.userservice.enums.Gender;
//import com.example.userservice.services.AvocatServiceImpl;
import com.example.userservice.services.AvocatServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example"})
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

//    @Bean
    CommandLineRunner start(AvocatServiceImpl avocatService) {
        return args -> {
            int i = 914;
            UserInputDto avocat = new UserInputDto();

            avocat.setEmail("avocat" + i + "@gmail.com");
            avocat.setCin("WA123" + i);
            avocat.setGender(Math.random() * 10 > 5 ? Gender.MALE.toString() : Gender.FEMALE.toString());
            avocat.setNom("avocat " + i);
            avocat.setRole("AVOCAT");
            avocat.setPrénom("avocat Prenom :" + i);
            avocat.setPassword("11111" + i);
            avocat.setVille("casa" + i);
            avocat.setSpécialité("avocat SP " + i);
            avocat.setImage_url("azertyuiop");

            avocatService.createUser(avocat);

        };
    }
}

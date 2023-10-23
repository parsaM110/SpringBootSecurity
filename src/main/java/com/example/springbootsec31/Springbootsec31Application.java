package com.example.springbootsec31;

import com.example.springbootsec31.entity.Role;
import com.example.springbootsec31.entity.User;
import com.example.springbootsec31.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Springbootsec31Application {

    public static void main(String[] args) {
        SpringApplication.run(Springbootsec31Application.class, args);
    }

    //in the video he implemented , command line runner but i am not implementing it
    //here I use the bean to run the code

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository
    ) {
        return args -> {

            User adminAccount = userRepository.findByRole(Role.ADMIN);
            if (adminAccount == null) {
                User user = new User();
                user.setEmail("admin@gmail.com");
                user.setFirstName("admin");
                user.setLastName("admin");
                user.setRole(Role.ADMIN);
                user.setPassword(new BCryptPasswordEncoder().encode("admin"));

                userRepository.save(user);
            }
        };
    }
}
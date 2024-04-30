package com.example.configuration;

import com.example.entity.Size;
import com.example.repository.SizeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(SizeRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Size(1L, "S"));
                repository.save(new Size(2L, "M"));
                repository.save(new Size(3L, "L"));
            }
        };
    }
}
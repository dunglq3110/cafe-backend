package com.example.configuration;

import com.example.entity.Size;
import com.example.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    @Autowired
    SizeRepository sizeRepository;
    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            if (sizeRepository.count() == 0) {
                sizeRepository.save(new Size(1L, "S"));
                sizeRepository.save(new Size(2L, "M"));
                sizeRepository.save(new Size(3L, "L"));
            }
        };
    }
}
package com.example.configuration;

import com.example.entity.Staff;
import com.example.repository.StaffRepository;
import com.example.util.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppInitConfig {

    PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(StaffRepository staffRepository){
        return args -> {
            if (staffRepository.findStaffByUsername("admin").isEmpty()){
                Staff user = new Staff();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRole(Role.ADMIN);
                staffRepository.save(user);
            }
        };
    }
}

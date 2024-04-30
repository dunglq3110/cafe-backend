package com.example.entity;


import com.example.util.Gender;
import com.example.util.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    Gender gender;
    Role role;
    String address;
    String phoneNumber;
    String email;
    String password;
    String username;
    @OneToMany(mappedBy = "staff")
    List<Receipt> receipts;

}

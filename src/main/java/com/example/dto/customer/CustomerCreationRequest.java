package com.example.dto.customer;

import com.example.util.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerCreationRequest {

    String name;
    Date dateOfBirth;
    Gender gender;
    String phoneNumber;
}

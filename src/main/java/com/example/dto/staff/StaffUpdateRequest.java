package com.example.dto.staff;

import com.example.util.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffUpdateRequest {
    String firstName;
    String lastName;
    Gender gender;
    String address;
    String phoneNumber;
    String email;
    String password;
}

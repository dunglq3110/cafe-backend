package com.example.dto.staff;

import com.example.util.Gender;
import com.example.util.Role;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StaffCreationRequest {

    @Size(min = 8, max = 20, message = "PASSWORD_INVALID")
    String password;
    @Size(min = 8, max = 20, message = "USERNAME_INVALID")
    String username;
    String firstName;
    String lastName;
    Gender gender;
    String address;
    String phoneNumber;
    String email;

}

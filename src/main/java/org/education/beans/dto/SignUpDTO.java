package org.education.beans.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDTO {

    @Size(min = 5, message = "Too short email")
    String email;

    @Size(min = 5, max = 30, message = "The username size must be no less than 5 and no more than 30")
    String username;

    @Size(min = 8, max = 15, message = "The password size must be no less than 8 and no more than 15")
    String password;
}

package org.education.beans.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInDTO {

    @Size(min = 5, message = "Too short email")
    String email;

    @Size(min = 8, max = 15, message = "The password size must be no less than 8 and no more than 15")
    String password;
}

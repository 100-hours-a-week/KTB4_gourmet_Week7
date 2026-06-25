package KTB4_gourmet_Week7.Assignment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserPasswordUpdateRequestDto {

    @NotBlank(message = "password is required")
    private String password;
}
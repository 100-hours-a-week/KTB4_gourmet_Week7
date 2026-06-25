package KTB4_gourmet_Week7.Assignment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    @NotBlank(message = "nickname is required")
    private String nickname;

    public UserUpdateRequestDto(String nickname) {
        this.nickname = nickname;
    }


}
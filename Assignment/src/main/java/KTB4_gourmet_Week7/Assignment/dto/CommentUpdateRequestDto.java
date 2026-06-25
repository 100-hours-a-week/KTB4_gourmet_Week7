package KTB4_gourmet_Week7.Assignment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentUpdateRequestDto {

    @NotBlank(message = "content is required")
    private String content;
}
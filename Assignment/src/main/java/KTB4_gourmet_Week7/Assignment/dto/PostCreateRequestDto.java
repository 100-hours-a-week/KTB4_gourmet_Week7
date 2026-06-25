package KTB4_gourmet_Week7.Assignment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequestDto {

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "content is required")
    private String content;

    public PostCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
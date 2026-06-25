package KTB4_gourmet_Week7.Assignment.controller;

import KTB4_gourmet_Week7.Assignment.dto.LoginRequestDto;
import KTB4_gourmet_Week7.Assignment.dto.UserResponseDto;
import KTB4_gourmet_Week7.Assignment.dto.UserSignupRequestDto;
import KTB4_gourmet_Week7.Assignment.dto.UserUpdateRequestDto;
import KTB4_gourmet_Week7.Assignment.service.UserService;
import KTB4_gourmet_Week7.Assignment.dto.UserPasswordUpdateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto signup(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String nickname,
            @RequestPart(required = false) MultipartFile profileImage
    ) {
        UserSignupRequestDto request = new UserSignupRequestDto(email, password, nickname);

        return userService.signup(request, profileImage);
    }
    @PostMapping("/login")
    public UserResponseDto login(@Valid @RequestBody LoginRequestDto request) {
        return userService.login(request);
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PatchMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserResponseDto updateUser(
            @PathVariable Long userId,
            @RequestParam String nickname,
            @RequestPart(required = false) MultipartFile profileImage
    ) {
        UserUpdateRequestDto request = new UserUpdateRequestDto(nickname);

        return userService.updateUser(userId, request, profileImage);
    }

    @PatchMapping("/{userId}/password")
    public UserResponseDto updatePassword(
            @PathVariable Long userId,
            @Valid @RequestBody UserPasswordUpdateRequestDto request
    ) {
        return userService.updatePassword(userId, request);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
package KTB4_gourmet_Week7.Assignment.service;

import KTB4_gourmet_Week7.Assignment.dto.*;
import KTB4_gourmet_Week7.Assignment.entity.Post;
import KTB4_gourmet_Week7.Assignment.entity.User;
import KTB4_gourmet_Week7.Assignment.exception.DuplicateEmailException;
import KTB4_gourmet_Week7.Assignment.exception.InvalidLoginException;
import KTB4_gourmet_Week7.Assignment.exception.UserNotFoundException;
import KTB4_gourmet_Week7.Assignment.repository.CommentRepository;
import KTB4_gourmet_Week7.Assignment.repository.PostImageRepository;
import KTB4_gourmet_Week7.Assignment.repository.PostLikeRepository;
import KTB4_gourmet_Week7.Assignment.repository.PostRepository;
import KTB4_gourmet_Week7.Assignment.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    public UserResponseDto signup(UserSignupRequestDto request, MultipartFile profileImage) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("이미 사용 중인 이메일입니다.");
        }

        String profileImageUrl = fileStorageService.saveFile(profileImage, "profile");

        User user = new User(
                request.getEmail(),
                request.getPassword(),
                request.getNickname(),
                profileImageUrl
        );

        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser);
    }

    public UserResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmailAndPassword(
                request.getEmail(),
                request.getPassword()
        ).orElseThrow(() -> new InvalidLoginException("이메일 또는 비밀번호가 일치하지 않습니다."));

        if (user.getDeletedAt() != null) {
            throw new InvalidLoginException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return new UserResponseDto(user);
    }

    public List<UserResponseDto> getUsers() {
        return userRepository.findAllByOrderByIdAsc()
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto getUser(Long userId) {
        User user = findUserById(userId);

        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(Long userId, UserUpdateRequestDto request, MultipartFile profileImage) {
        User user = findUserById(userId);

        user.update(request.getNickname());

        String profileImageUrl = fileStorageService.saveFile(profileImage, "profile");

        if (profileImageUrl != null) {
            user.updateProfileImage(profileImageUrl);
        }

        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto updatePassword(Long userId, UserPasswordUpdateRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        user.updatePassword(request.getPassword());

        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = findUserById(userId);

        user.delete();
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("회원을 찾을 수 없습니다."));
    }
}
# KTB4_gourmet_Week7

Spring Boot + JPA + Vanilla JavaScript를 사용해 구현한 커뮤니티 서비스 과제입니다.
회원, 게시글, 댓글, 좋아요, 이미지 업로드 기능을 구현하고 Fetch API를 사용해 프론트엔드와 백엔드를 연결했습니다.

## 프로젝트 구조

```text
project_assignment_7
├── src
│   └── main
│       ├── java
│       │   └── KTB4_gourmet_Week7.Assignment
│       │       ├── config
│       │       ├── controller
│       │       ├── dto
│       │       ├── entity
│       │       ├── exception
│       │       ├── repository
│       │       └── service
│       └── resources
│           └── application.yaml
├── frontend
│   ├── css
│   ├── js
│   ├── login.html
│   ├── signup.html
│   ├── posts.html
│   ├── post-detail.html
│   ├── post-create.html
│   ├── post-edit.html
│   ├── edit-profile.html
│   └── edit-password.html
├── build.gradle
└── README.md
```

## 주요 기능

### 회원 기능

* 회원가입
* 로그인
* 회원정보 조회
* 회원 목록 조회
* 회원정보 수정
* 프로필 이미지 업로드 및 변경
* 비밀번호 수정
* 회원 탈퇴
* 로그아웃

### 게시글 기능

* 게시글 작성
* 게시글 목록 조회
* 게시글 상세 조회
* 게시글 수정
* 게시글 삭제
* 게시글 이미지 다중 업로드
* 게시글 목록 썸네일 표시
* 게시글 상세 이미지 표시
* 게시글 조회수 증가

### 댓글 기능

* 댓글 작성
* 댓글 목록 조회
* 댓글 상세 조회
* 댓글 수정
* 댓글 삭제
* 댓글 작성자 프로필 이미지 표시

### 좋아요 기능

* 게시글 좋아요 상태 조회
* 게시글 좋아요 등록
* 게시글 좋아요 취소
* 게시글 좋아요 수 표시

## 실행 방법

### 1. Backend 실행

IntelliJ에서 Spring Boot 애플리케이션을 실행합니다.

```text
http://localhost:8080
```

### 2. H2 Console 접속

```text
http://localhost:8080/h2-console
```

접속 정보는 다음과 같습니다.

```text
JDBC URL: jdbc:h2:mem:community
User Name: sa
Password: 비워두기
```

### 3. Frontend 실행

`frontend` 폴더의 HTML 파일을 VS Code Live Server로 실행합니다.

예시:

```text
http://127.0.0.1:5500/posts.html
```

## API 목록

### User API

| Method | URL                        | 설명       |
| ------ | -------------------------- | -------- |
| POST   | `/users/signup`            | 회원가입     |
| POST   | `/users/login`             | 로그인      |
| GET    | `/users?page=0&size=10`    | 회원 목록 조회 |
| GET    | `/users/{userId}`          | 회원 상세 조회 |
| PATCH  | `/users/{userId}`          | 회원정보 수정  |
| PATCH  | `/users/{userId}/password` | 비밀번호 수정  |
| DELETE | `/users/{userId}`          | 회원 탈퇴    |

### Post API

| Method | URL                               | 설명                        |
| ------ | --------------------------------- | ------------------------- |
| POST   | `/users/{userId}/posts`           | 게시글 작성                    |
| GET    | `/posts?page=0&size=10`           | 게시글 목록 조회                 |
| GET    | `/posts/{postId}`                 | 게시글 상세 조회                 |
| GET    | `/posts/{postId}?userId={userId}` | 게시글 상세 조회 및 회원별 조회수 증가 처리 |
| PATCH  | `/posts/{postId}`                 | 게시글 수정                    |
| DELETE | `/posts/{postId}`                 | 게시글 삭제                    |

### Comment API

| Method | URL                                    | 설명       |
| ------ | -------------------------------------- | -------- |
| POST   | `/posts/{postId}/comments`             | 댓글 작성    |
| GET    | `/posts/{postId}/comments`             | 댓글 목록 조회 |
| GET    | `/posts/{postId}/comments/{commentId}` | 댓글 상세 조회 |
| PATCH  | `/posts/{postId}/comments/{commentId}` | 댓글 수정    |
| DELETE | `/posts/{postId}/comments/{commentId}` | 댓글 삭제    |

### Like API

| Method | URL                                    | 설명        |
| ------ | -------------------------------------- | --------- |
| GET    | `/posts/{postId}/likes/users/{userId}` | 좋아요 상태 조회 |
| POST   | `/posts/{postId}/likes/users/{userId}` | 좋아요 토글    |

## 페이징 응답 구조

게시글 목록과 회원 목록은 페이징 메타데이터를 포함한 응답 DTO로 반환합니다.

### 게시글 목록 응답 예시

```json
{
  "content": [
    {
      "id": 1,
      "userId": 1,
      "nickname": "작성자",
      "profileImage": "/uploads/profile/example.png",
      "title": "게시글 제목",
      "content": "게시글 내용",
      "viewCount": 0,
      "likeCount": 0,
      "commentCount": 0,
      "createdAt": "2026-06-25T18:00:00",
      "updatedAt": null,
      "imageUrls": [
        "/uploads/posts/example.png"
      ]
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1,
  "hasNext": false,
  "hasPrevious": false
}
```

### 회원 목록 응답 예시

회원 목록에서는 개인정보 노출을 줄이기 위해 이메일을 제외하고 응답합니다.

```json
{
  "content": [
    {
      "id": 1,
      "nickname": "사용자",
      "profileImage": "/uploads/profile/example.png"
    }
  ],
  "page": 0,
  "size": 10,
  "totalElements": 1,
  "totalPages": 1,
  "hasNext": false,
  "hasPrevious": false
}
```

## 이미지 업로드 방식

이미지 파일은 DB에 직접 저장하지 않고 서버의 `uploads` 폴더에 저장합니다.
DB에는 이미지 파일의 접근 경로만 저장합니다.

```text
프로필 이미지: uploads/profile
게시글 이미지: uploads/posts
```

저장 경로 예시는 다음과 같습니다.

```text
/uploads/profile/uuid.png
/uploads/posts/uuid.png
```

프론트엔드에서는 백엔드 주소와 이미지 경로를 조합해 이미지를 표시합니다.

```javascript
`${API_BASE_URL}${imageUrl}`
```

## 입력값 검증

DTO와 Controller에서 입력값 검증을 적용했습니다.

### 적용한 검증

* 이메일 필수 입력
* 이메일 형식 검증
* 이메일 길이 제한
* 비밀번호 필수 입력
* 비밀번호 길이 제한
* 닉네임 필수 입력
* 닉네임 길이 제한
* 게시글 제목 필수 입력
* 게시글 제목 길이 제한
* 게시글 내용 필수 입력
* 댓글 내용 필수 입력

### 검증 방식

일반 JSON 요청은 DTO에 `@Valid`, `@NotBlank`, `@Email`, `@Size` 등을 적용했습니다.

이미지 업로드가 포함된 `multipart/form-data` 요청은 `@RequestParam`으로 값을 받기 때문에 Controller 파라미터에도 검증 어노테이션을 적용하고, Controller 클래스에 `@Validated`를 추가했습니다.

## 예외 처리

공통 예외 처리를 위해 `GlobalExceptionHandler`를 사용했습니다.

### 처리한 예외

* 회원 없음
* 로그인 실패
* 게시글 없음
* 댓글 없음
* 이메일 중복
* 닉네임 중복
* DTO 검증 실패
* RequestParam 검증 실패
* DB 제약 조건 위반
* 잘못된 JSON 요청
* 예상하지 못한 서버 내부 오류

예외 발생 시 공통 `ErrorResponse` 형식으로 응답하도록 구성했습니다.

## 프론트엔드 페이지

| 파일명                  | 설명      |
| -------------------- | ------- |
| `signup.html`        | 회원가입    |
| `login.html`         | 로그인     |
| `posts.html`         | 게시글 목록  |
| `post-detail.html`   | 게시글 상세  |
| `post-create.html`   | 게시글 작성  |
| `post-edit.html`     | 게시글 수정  |
| `edit-profile.html`  | 회원정보 수정 |
| `edit-password.html` | 비밀번호 수정 |

## 강사 피드백 반영 사항

6주차 강사 피드백을 바탕으로 일부 코드를 수정했습니다.

### 1. 닉네임 중복 검사 추가

기존에는 이메일 중복만 애플리케이션 레벨에서 검사하고, 닉네임 중복은 DB UNIQUE 제약에 의존하고 있었습니다.

수정 후에는 회원가입과 회원정보 수정 시 닉네임 중복을 먼저 검사하도록 개선했습니다.

* `existsByNickname`
* `existsByNicknameAndIdNot`
* `DuplicateNicknameException`

중복 닉네임이 발생하면 409 Conflict 응답을 반환하도록 수정했습니다.

### 2. 회원 목록 응답에서 이메일 제외

기존에는 `GET /users` 회원 목록 응답에서도 이메일이 함께 내려갔습니다.

이메일은 개인정보에 해당할 수 있으므로 회원 목록용 DTO를 분리했습니다.

* `UserResponseDto`: 회원 단건 조회, 로그인, 회원정보 수정 응답
* `UserListResponseDto`: 회원 목록 응답

회원 목록에서는 `id`, `nickname`, `profileImage`만 내려주도록 수정했습니다.

### 3. 게시글 목록 페이징 메타데이터 추가

기존에는 `PageRequest`로 게시글을 조회했지만, 응답에서는 `getContent()`만 반환하여 전체 게시글 수, 전체 페이지 수, 다음 페이지 여부 같은 정보가 사라졌습니다.

수정 후에는 `PostPageResponseDto`를 추가하여 게시글 목록과 함께 페이징 메타데이터를 내려주도록 개선했습니다.

응답에 포함되는 정보는 다음과 같습니다.

* `content`
* `page`
* `size`
* `totalElements`
* `totalPages`
* `hasNext`
* `hasPrevious`

### 4. 회원 목록 페이징 적용

기존 회원 목록 조회는 전체 회원을 한 번에 조회하는 방식이었습니다.

수정 후에는 `GET /users?page=0&size=10` 형태로 페이징 조회가 가능하도록 변경했습니다.

또한 `UserPageResponseDto`를 추가하여 회원 목록도 페이징 메타데이터와 함께 응답하도록 개선했습니다.

### 5. DTO 길이 제한 추가

기존 DTO에는 `@NotBlank`, `@Email`, `@NotNull` 위주의 검증만 있고 길이 제한이 부족했습니다.

수정 후에는 Entity 컬럼 길이에 맞춰 `@Size`를 추가했습니다.
이를 통해 DB 제약에 도달하기 전에 애플리케이션 레벨에서 먼저 검증되도록 개선했습니다.

### 6. 예외 응답 형식 일관화

기존에는 직접 만든 예외와 일부 검증 예외 중심으로만 처리하고 있었습니다.

수정 후에는 다음 예외 처리를 추가하여 공통 `ErrorResponse` 형식으로 응답하도록 개선했습니다.

* `DataIntegrityViolationException`
* `HttpMessageNotReadableException`
* `ConstraintViolationException`
* `Exception`

## 추후 개선 사항

### 1. BaseTimeEntity 또는 JPA Auditing 적용

현재 여러 Entity에서 `createdAt`, `updatedAt`과 `@PrePersist`, `@PreUpdate` 코드가 반복되고 있습니다.

추후 공통 시간 필드를 `BaseTimeEntity`로 분리하거나 JPA Auditing을 적용하여 중복을 줄일 예정입니다.

### 2. 삭제 정책 정리

현재 일부 Entity에는 `deletedAt` 필드가 있지만, 전체 삭제 정책이 완전히 통일되어 있지는 않습니다.

추후 물리 삭제와 Soft Delete 중 하나의 정책으로 일관되게 정리할 예정입니다.

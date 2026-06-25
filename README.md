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
| GET    | `/users`                   | 회원 목록 조회 |
| GET    | `/users/{userId}`          | 회원 상세 조회 |
| PATCH  | `/users/{userId}`          | 회원정보 수정  |
| PATCH  | `/users/{userId}/password` | 비밀번호 수정  |
| DELETE | `/users/{userId}`          | 회원 탈퇴    |

### Post API

| Method | URL                               | 설명        |
| ------ | --------------------------------- | --------- |
| POST   | `/users/{userId}/posts`           | 게시글 작성    |
| GET    | `/posts?page=0&size=10`           | 게시글 목록 조회 |
| GET    | `/posts/{postId}?userId={userId}` | 게시글 상세 조회 |
| PATCH  | `/posts/{postId}`                 | 게시글 수정    |
| DELETE | `/posts/{postId}`                 | 게시글 삭제    |

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

```

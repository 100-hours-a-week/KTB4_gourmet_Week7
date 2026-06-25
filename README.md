# KTB4_gourmet_Week7

Spring Boot + JPA + Vanilla JavaScript 기반 커뮤니티 서비스 과제입니다.
회원, 게시글, 댓글, 좋아요, 이미지 업로드 기능을 구현하고 Fetch API로 프론트엔드와 백엔드를 연결했습니다.

## Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Data JPA
* H2 Database
* Lombok
* Gradle

### Frontend

* HTML
* CSS
* JavaScript
* Fetch API
* Live Server

## Project Structure

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

## Features

### User

* Sign up
* Login
* Get user profile
* Update user profile
* Upload and update profile image
* Update password
* Delete user
* Logout

### Post

* Create post
* Get post list
* Get post detail
* Update post
* Delete post
* Upload multiple post images
* Show thumbnail image on post list
* Show post images on detail page
* Increase view count

### Comment

* Create comment
* Get comment list
* Update comment
* Delete comment
* Show comment writer profile image

### Like

* Get like status
* Toggle like
* Show like count

## Run Backend

Run the Spring Boot application in IntelliJ.

```text
http://localhost:8080
```

## H2 Console

```text
http://localhost:8080/h2-console
```

Connection information:

```text
JDBC URL: jdbc:h2:mem:community
User Name: sa
Password:
```

## Run Frontend

Open the `frontend` folder with VS Code and run HTML files using Live Server.

Example:

```text
http://127.0.0.1:5500/posts.html
```

## API

### User API

| Method | URL                        | Description         |
| ------ | -------------------------- | ------------------- |
| POST   | `/users/signup`            | Sign up             |
| POST   | `/users/login`             | Login               |
| GET    | `/users`                   | Get user list       |
| GET    | `/users/{userId}`          | Get user detail     |
| PATCH  | `/users/{userId}`          | Update user profile |
| PATCH  | `/users/{userId}/password` | Update password     |
| DELETE | `/users/{userId}`          | Delete user         |

### Post API

| Method | URL                               | Description     |
| ------ | --------------------------------- | --------------- |
| POST   | `/users/{userId}/posts`           | Create post     |
| GET    | `/posts?page=0&size=10`           | Get post list   |
| GET    | `/posts/{postId}?userId={userId}` | Get post detail |
| PATCH  | `/posts/{postId}`                 | Update post     |
| DELETE | `/posts/{postId}`                 | Delete post     |

### Comment API

| Method | URL                                    | Description        |
| ------ | -------------------------------------- | ------------------ |
| POST   | `/posts/{postId}/comments`             | Create comment     |
| GET    | `/posts/{postId}/comments`             | Get comment list   |
| GET    | `/posts/{postId}/comments/{commentId}` | Get comment detail |
| PATCH  | `/posts/{postId}/comments/{commentId}` | Update comment     |
| DELETE | `/posts/{postId}/comments/{commentId}` | Delete comment     |

### Like API

| Method | URL                                    | Description     |
| ------ | -------------------------------------- | --------------- |
| GET    | `/posts/{postId}/likes/users/{userId}` | Get like status |
| POST   | `/posts/{postId}/likes/users/{userId}` | Toggle like     |

## Image Upload

Images are stored in the server `uploads` directory.
The database stores only the image path.

```text
Profile images: uploads/profile
Post images: uploads/posts
```

Example:

```text
/uploads/profile/uuid.png
/uploads/posts/uuid.png
```

## Frontend Pages

| Page                 | Description         |
| -------------------- | ------------------- |
| `signup.html`        | Sign up             |
| `login.html`         | Login               |
| `posts.html`         | Post list           |
| `post-detail.html`   | Post detail         |
| `post-create.html`   | Create post         |
| `post-edit.html`     | Update post         |
| `edit-profile.html`  | Update user profile |
| `edit-password.html` | Update password     |

## Notes

H2 Memory Database is used, so data is reset when the backend server restarts.

Uploaded image files are not intended to be committed to Git.

Recommended `.gitignore`:

```gitignore
uploads/
build/
.gradle/
.idea/
*.iml
```

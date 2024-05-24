### Spring Boot 게시판 구현

<hr>


#### 구현 목록



1. 게시물 목록 보기

   - URL: ` [GET] "/list", "/list?page=2"`

     URL 입력 시 게시물 목록 페이지별로 보여줌
     
     한 페이지 당 게시물 5개 표기
     
     page 파라미터가 없으면 기본적으로 첫번째 페이지를 보여줌



2. 게시물 상세 조회

   - URL: ` [GET] "/view?id=아이디"`

     특정 게시물의 상세 내용 출력

     삭제, 수정 링크를 제공하여 해당 기능을 수행할 수 있는 페이지로 이동 가능



3. 게시물 등록 form

   - URL: ` [GET] "/writeform"`

     게시물을 작성하기 위한 form 제공

     사용자는 이름, 제목, 내용, 암호를 입력하여 게시물 작성 가능

     입력이 완료될 경우 게시물 목록으로 redirect



4. 게시물 삭제 form

   - URL: ` [GET] "/deletefrom?id=아이디"`

     게시물을 삭제하기 위한 form 제공

     사용자는 암호를 입력하여 삭제 가능

     작성할 때 입력했던 비밀번호와 일치하지 않으면 알림

     비밀번호가 일치할 경우 게시물 목록으로 redirect



5. 게시물 수정 form

   - URL: ` [GET] "/updateform?id=아이디"`

     게시물을 수정하기 위한 form 제공

     사용자는 이름, 제목, 내용, 암호를 입력하여 게시물 수정 가능

     작성 시 입력했던 비밀번호와 일치하지 않으면 알림

     비밀번호가 일치할 경우 게시물 목록으로 redirect



<hr>

#### 실행 방법

1. 터미널에서 아래 명령어 실행

   `.\gradlew build`

2. 빌드 이후 SNAPSHOT.jar 파일이 있는 디렉토리로 이동

3. 적당한 디렉토리에 `application.yml` 파일 생성

   ```yml
   spring:
     application:
       name: boardProejct
   
     datasource:
       url: jdbc:mysql://localhost:3306/[dbname]
       username: [database_username]
       password: [database_password]
       driver-class-name: com.mysql.cj.jdbc.Driver
   ```

4. 작성한 yml 파일 기반 실행 명령어

   `java -jar [jar파일명] --spring.config.location=file://[yml 파일]`
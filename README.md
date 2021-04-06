# StudyHelper 프로젝트 [![Build Status](https://travis-ci.com/byeunsungjae/Studyhelper-real.svg?branch=master)]

##### VERSION 0.0.1 prototype
##### 운영중 URL [studyhelper](http://3.36.141.69:8081/)
## 개요

스터디 매칭 후 스터디 관리 및 스터디원들의 소통을 도와주는 웹 서비스  

## 기술스텍
### 백엔드
- Spring boot 2.4.3 version with (websocket, batch, lombook, devtools, data, jpa, webMVC, security) 
- Mysql (JPA)
- Redis (single + pubsub)
- java 1.8 Maven
- Kafka
### 프론트
- Html, Css, Javascript, Thymeleaf, freemarker
- Vue
### 데브옵스
- CI/CD : Github + Travis Ci
- Docker
### 환경
- 확장성을 위한 멀티모듈 사용 -> web , batch -> MSA 아키텍쳐
- local -> Window 10 ,eclipse
- deploy -> Aws ec2 ubuntu 18
- Nginx(예정)

![전체흐름이미지](https://user-images.githubusercontent.com/47214171/109663535-90935500-7baf-11eb-9171-99efd6632d39.png)

![캡처](https://user-images.githubusercontent.com/47214171/112754024-37cfa480-9015-11eb-9602-9b97fb65f9b4.JPG)


## API
Swagger 구현 -> /swagger-ui.html
![asd](https://user-images.githubusercontent.com/47214171/112744511-bc053600-8fdb-11eb-84be-be75b086b5b9.JPG)
<br>
## 동작

### 시작페이지
<img src="https://user-images.githubusercontent.com/47214171/109777079-a869e800-7c46-11eb-84a5-d5b98266ef15.JPG" height="300px">

### 로그인페이지
<img src="https://user-images.githubusercontent.com/47214171/109777890-845ad680-7c47-11eb-9a24-b2e0683b67e4.JPG" height="300px">

### 회원가입
<img src="https://user-images.githubusercontent.com/47214171/109778275-f7fce380-7c47-11eb-99d2-095dd356ccbb.JPG" height="300px">

### 유저 메인페이지
<img src="https://user-images.githubusercontent.com/47214171/109778420-24186480-7c48-11eb-8352-f9792f977e23.JPG" height="300px">

### 팀리스트
<img src="https://user-images.githubusercontent.com/47214171/109778490-385c6180-7c48-11eb-8ac9-7949369ed468.JPG" height="300px">

### 팀 메인페이지
<img src="https://user-images.githubusercontent.com/47214171/109778504-3d211580-7c48-11eb-9bd3-fab06ac61264.JPG" height="300px">

### 게시판 리스트
<img src="https://user-images.githubusercontent.com/47214171/109778535-47431400-7c48-11eb-9f34-fbb5c38300ee.JPG" height="300px">

### 게시판 등록
<img src="https://user-images.githubusercontent.com/47214171/109778532-4611e700-7c48-11eb-8bf9-c016dd59e0c8.JPG" height="300px">

### 팀 채팅
<img src="https://user-images.githubusercontent.com/47214171/109778689-75285880-7c48-11eb-9ee4-d14c7e618d99.JPG" height="300px">

<br>

## 로그인
Spring boot Security 사용
(JWT 토큰 사용 예정)  

1. 회원(Member)를 User 클래스를 상속받은 SecurityUser에 감싸서 사용함  
2. 비밀번호 PasswordEncoder를 사용하여 encoding 하여 사용
3. SecurityUser안에 회원(member) 와 최근 접근한 팀의 정보(Team)를 넣어놓고 세션이 유지되는 동안에 각 컨트롤러에서 사용 

-----------------------------

## 배치 + 스케줄링
1. chunk 배치 사용
2. spring job scheduler 사용하여 scheduling
3. 매칭 신청 후 3일의 시간이 지난 매칭에 대해서 삭제작업 수행
-----------------------------
## 스터디 매칭 알고리즘
1. 트라이 알고리즘 사용
2. (현재는 arraylist 배열사용 -> 트라이로 바꿀예정 + 우선순위 사용예정)
3. 매칭 신청시 WEB 도메인 -> Batch 도매인으로 매칭 정보를 전송 -> 매칭 정보를 전송받으면 매칭알고리즘 실행 -> 
-----------------------------
## 채팅 구현 with Redis and Websocket
1. spring webSocket 사용
2. Redis pub/sub 사용 -> 서버 재실행시 채팅방 정보를 저장 + 여러 서버에서 같은 채팅방을 볼 수 있도록
-----------------------------
## JPA
1. MYSQL (8.0.2)
2. REDIS  

<img src="https://user-images.githubusercontent.com/47214171/109801644-18d23280-7c62-11eb-9478-0d2f3049c4f7.JPG" height="300">    
데이터베이스 ERD (MYSQL)  <br>   
<img src="https://user-images.githubusercontent.com/47214171/109802027-9e55e280-7c62-11eb-9cee-2fd9348fb167.JPG" height="300">  
Redis ERD (matchings + CHAT_ROOMS)

-----------------------------
## Exception Handler
1. board exception
2. user exception
-----------------------------
## CI/CD
1. Github
2. Traivs CI
3. aws s3 + code deploy
-----------------------------

<br><br><br><br>

# 트러블 이슈

#### 데이터베이스
1. JPA N+1 문제 발생 ()
2. 영속성 컨텍스트 연장부분
    - osiv~~
3. 동시성 문제 해결전략
    - 게시판의 경우 동시 수정이 가능하다 -> 낙관적인락 (@Version)으로 동시성을 높여준다
4. Redis
    - redis findby 뒤에 나올려면 @indexed어노테이션을 붙여줘야한다
    - 

#### Spring boot
1. rds key와같이 중요한 정보 저장 ()
2. logging 작업
3. Rest api
4. 패키징
5. 테스팅
#### 메시지 큐 (Kafka)

#### CI/CD + AWS ec2
#### 아키텍쳐
1. SOA 아키택쳐
   - 외부에서 매칭에 접근하지 못하도록
   - 확장성을 위해 만듬
   - 반응성을 위해 사용


## 테스트 정리
1. Junit5 사용
2. Mockmvc 사용 -> 가상 서블릿
3. Spring Security 적용시 권한이 필요한 페이지 테스트 -> @withUserDetail 어노테이션 사용




## 스토리 라인
1. 매칭
   1. 모놀리틱 구조로 웹페이지에서 매칭을 요청시 그 즉시 매칭알고리즘이 실행되어 매칭하는 방식
   2. MSA 아키텍쳐를 위해 WEB Domain과 Batch Domain을 나눈후 매칭 요청시 Kafka를 이용하여 매칭정보를 web->batch로 메시징 후 받으면 매칭 알고리즘 실행
   3. 매칭은 kafka를 통한 비동기식 매칭, 매칭 정보 삭제는 Batch를 사용하여 일정 시간 이후 3일이지난 매칭 정보는 삭제
   4. Kafka를 통해서 매칭 정보를 받아올 때마다 매칭알고리즘을 실행시켜 매칭을 진행했음 -> 동기화 문제가 발생가능(static 으로 선언되어 초기화된 Hashset에 정보를 올려놓고 사용하기 때문에 데이터베이스 단에서 동기화를 할 수없음) -> synchronize를 사용하게되면 동시성이 많이 떨어질거라고 예상
   5. 매칭정보를 받으면 매칭을 모아놓은 matchingtrie에 넣는다. 이때 넣을때와 인원이 맞아 팀을 만들 인원들을 뽑는 작업들에 synchronize 키워드를 사용해서 동기화를 해줌
   6. @Async 어노테이션을 사용해 해당 매칭들을 이용해 팀을 만드는 과정은 비동기,논블러킹을 사용함 -> 동시성을 높여주었다.
2. 같은 매칭 정보가 들어올 때 핸들링
   1. Matching 정보들에 모두 인덱스를 넣어서 한번에 서칭 후 비교 vs 해당 아이디가 매칭요청한 정보를 모두 가져온 후 비교 => 시간을 줄이는것이 더 좋을거라고 생각 -> indexed 추가 -> 매칭서버는 외부에서 접근 할 수없기 때문에 외부에서 요청한 중복된 매칭값을 막을 수 있다.
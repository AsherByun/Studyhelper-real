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
### 프론트
- Html, Css, Javascript, Thymeleaf, freemarker
- Vue
### 데브옵스
- CI/CD : Github + Travis Ci
- Docker
### 환경
- 확장성을 위한 멀티모듈 사용 -> web , batch
- local -> Window 10 ,eclipse
- deploy -> Aws ec2 ubuntu 18
- Nginx(예정)

![전체흐름이미지](https://user-images.githubusercontent.com/47214171/109663535-90935500-7baf-11eb-9171-99efd6632d39.png)



## API
![api1](https://user-images.githubusercontent.com/47214171/109776848-62148900-7c46-11eb-92fb-663766de91d1.JPG)
![api2](https://user-images.githubusercontent.com/47214171/109776853-6345b600-7c46-11eb-823e-8e21f1059d19.JPG)
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

-----------------------------

## 배치 + 스케줄링
1. chunk 배치 사용
2. spring job scheduler 사용하여 scheduling

-----------------------------
## 스터디 매칭 알고리즘
1. 트라이 알고리즘 사용
2. (현재는 arraylist 배열사용 -> 트라이로 바꿀예정 + 우선순위 사용예정)
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
#### Spring boot
1. rds key와같이 중요한 정보 저장 ()
2. logging 작업
3. Rest api
4. 패키징
5. 테스팅
#### CI/CD + AWS ec2



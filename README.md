

# <center>2022년 개인 KPI </center>

<p align='right'> 작성자 : 오픈서비스사업팀 양승은</p>

<p align='right'>작성일: 2022년 10월 28일</p>



### 목표

공공 정보 정보 Rest API 제공 서버 구현 



### 개요

공공데이터 포털 제공 API를 활용하여 전기충전소 정보를 전달받아 
DB에 저장, RestAPI 를 통한 데이터 제공 서비스 구현



### 기능 상세

* 한국전력공사 전기차충전소 정보 조회 I/F 구현
~~~
  http://openapi.kepco.co.kr/service/EvinfoServiceV2/getEvSearchList?addr=%EC%AO%84%EB%AO%A5%EB%A1%9C
~~~
* 전기차충전소 정보 데이터 저장 관리
~~~
/elecCharg/add 실행
json Data 를 전달 받아 MySQL DB 에 데이터 저장
~~~
* 전기차충전소 정보 조회 RestAPI 구현
~~~
1. 충전소 전체 조회 API
    요청 URL : /elecCharg/all

2. 위도, 경도에 해당되는 충전소 목록 조회 API
    요청 URL : /elecCharg/list
    요청 Parameter : longitude, latitude
~~~



### 진행

* 서비스 세부 기능 정의 : 6/1 ~ 6/6 (1W)
* 서비스 구현 : 6/7 ~ 8/31(8W)
* 정리 및 리포트 : 9/1 ~ 9/15(2W)



### 기반 기술

* Spring Boot
* Spring REST
* java
* MySQL
* Mybatis



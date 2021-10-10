# ✔ Check-List

<br>

# 📋 Intro
#### Hadoop을 이용한 가전제품 성능 비교 플랫폼
> \# 빅데이터 분산 도메인 <br>
> 🙋김선혜, 권영린, 김윤주, 이영주, 이상현

<br>

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136690631-94e02ff1-8f1f-4ac8-ad93-54e176ffed74.png" width="900px">
</p>

<br><br>

## 🗂 Content

1. [🔈 프로젝트 소개](#-프로젝트-소개)
   <br>
   - [📑 타겟 및 목표](#-타겟-및-목표)
   - [📑 개발 환경](#-개발-환경)
   - [📑 기술 스택](#-기술-스택)
   - [📑 서버 아키텍처](#-서버-아키텍처)
   - [📑 ERD](#-erd)
   - [📑 와이어프레임](#-와이어프레임)
   - [📑 스토리보드](#-스토리보드)
   - [📑 핵심 기능](#-핵심-기능)
   <br><br>
2. [🔈 구현 결과](#-구현-결과)
   <br><br>
4. [🔈 팀원 소개 및 역할](#-팀원-소개-및-역할)
   <br><br>
4. [🔈 개발 문서](#-개발-문서)

<br><br><br><br>

---

## 🥜 프로젝트 소개

### 🔔 타겟 및 목표

#### 🧿 타겟

    ☝ 어떤 게 좋은거지..? 제품간 성능비교가 어려운 사람
    ✌ 상품에 대한 실제 후기를 한 눈에 보고 싶은 사람
    🤟 최저가로 상품을 구매하고 싶은 사람

#### 🏃‍ 목표

    ✔ 상품별 성능 비교를 통한 합리적 소비 증대
    ✔ 워드클라우드를 통한 리뷰 분석 및 시각화
    ✔ 고객의 관심도를 기반으로 한 상품 추천
   
<br><br>

### 🔨 개발 환경

- OS : Windows 10

- Server : AWS EC2

  - Ubuntu 20.04.1
  - Docker 20.10.8
  - Jenkins

- Backend

  - Java : Java 1.8.0
  - Framework : SpringBoot 2.5.4
  - ORM : JPA(Hibernate)
  - Nginx : 1.18.0 (Ubuntu)
  - IDE : Intellij 2021.1.3 / Visual Studio Code 1.59
  - Dependency tool : gradle-7.1
  - Database : MySQL 8.0.26

- Frontend 

    - HTML5, CSS3, Javascript(Es6) 
    - Vue 3.0.0 
    - Bootstrap 5
    - IDE : Visual Studio Code
    
  <br><br>

### 🔨 기술 스택

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136690637-fc927dc3-2b52-43d0-b945-364d6fd664b8.png" width="900px">
</p>

<br><br>

### 🎈 서버 아키텍처

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136690767-45cf8c6d-38c1-4c9e-aace-8f14a39e6b4a.png" width="900px">
</p>

<br><br>

### 🔍 ERD

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136690629-26e8fab8-7dc6-49b0-b7f0-476f2b0a1700.png" width="900px">
</p>

<br><br>

### 💌 기능명세서

[📁 기능명세서 보러가기](https://dandelion-leopon-581.notion.site/ba08062f330a4aa29ab9fa934afb6b37)

<br><br>

### 🎨 와이어프레임

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136691052-529981ef-ab3f-4e8d-91d0-da4ba753191e.png" width="900px">
</p>

<br><br>

### 📖 스토리보드

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136691051-cc534d34-5508-43ad-b772-97a002de4013.png" width="900px">
</p>

<br><br>

### 💎 핵심 기능

    🔑 MapReduce 기반의 상품 성능 분석
    🔑 리뷰 키워드 분석
    🔑 성능 기반의 상품 추천

<p align="center">
  <img src = "https://user-images.githubusercontent.com/33833780/136690761-c6965a36-7f89-4bfe-84b8-27b3ef988eca.png" width="900px">
</p>

<br><br>

## 🥜 구현 결과

#### 🔗 [영상](exec/ucc.mp4)

<br><br>

## 🥜 실행 가이드

#### 💻 [포팅 매뉴얼](https://dandelion-leopon-581.notion.site/4dd8e0d678df4a3f80a2fa692f1e4508)


<br><br>

## 🥜 팀원 소개 및 역할

<br><br>
|Name|권영린|김윤주|김선혜|이영주|이상현|
|-----|-----|-----|------|----|-----|
|Profile|![member1](https://user-images.githubusercontent.com/33833780/136691053-54c100dd-5611-441b-aad2-b1126c7f69c0.png)|![member2](https://user-images.githubusercontent.com/33833780/136691046-e022b564-5f93-4217-8c76-14aafcbddfda.png)|![member3](https://user-images.githubusercontent.com/33833780/136691048-6a56006b-109f-4d0f-a3a4-37ecafae27ba.png)|![member4](https://user-images.githubusercontent.com/33833780/136691049-e5cd38aa-3e25-47f3-abfd-f1d0a1efd3fc.png)|![member5](https://user-images.githubusercontent.com/33833780/136691050-02676d05-4675-48f5-b811-8a2404304be6.png)|
|Position|Frontend, Backend, Data Analysis & Crawling |Frontend, Backend, Data Analysis & CI/CD| Frontend, Backend, Data Analysis & Crawling|Frontend, Backend, Data Analysis & Crawling |Frontend, Backend, Data Analysis & CI/CD |
|Git|@sqk8657| @yjyjk20|@wkadnsj | @clleo97| @zxd9857|

<br><br>

## 🥜 개발 문서

#### [ 🧾 기능명세서 ](https://dandelion-leopon-581.notion.site/ba08062f330a4aa29ab9fa934afb6b37)

#### [ 📑 Git 컨벤션 ](https://dandelion-leopon-581.notion.site/Git-9b70409611124cb2ac4db7fe57165cfd)

#### [ 📖 Code 컨벤션 ](https://dandelion-leopon-581.notion.site/4fd747f504164e88bb58bf398f8ac378)

<br>

# Mobile Programming Personal Project

2020년도 2학기 모바일 프로그래밍 중간고사 대체 과제

# 프로젝트 소개

코틀린 언어를 이용한 쇼핑몰 어플 구현<br>
파이어베이스 RealTime Database를 활용한 상품 장바구니 추가 및 삭제 기능

## AVD Development Environment
* Pixel 2 API 30 (다른 버전 사용 시 데이터베이스 연동 오류)

## 기능 설명
### MainActivity (상품 선택 페이지)
* 상품 체크 후 구매하기 버튼 클릭 시 intent를 이용하여 구매 페이지에 데이터 전달
* 상품 체크 후 장바구니 버튼 클릭 시 데이터베이스에 상품의 Key,Value 값이 상품 이름:상품 가격 형태로 저장
* 체크되어 있는 아이템이 없을 때 구매 버튼 클릭 시 오류 메시지 출력
### BucketActivity (장바구니 페이지)
* 상품 체크 후 제거 버튼 클릭 시 체크되어 있는 상품을 데이터베이스에서 삭제
* 상품 체크 후 구매 버튼 클릭 시 체크되어 있는 상품의 이름과 가격을 intent를 이용하여 구매 페이지에 데이터 전달
* 장바구니 페이지에서는 데이터가 변경될 때마다 데이터 값들을 새롭게 렌더링
* 체크되어 있는 아이템이 없을 때 구매 버튼 클릭 시 오류 메시지 출력
### PurchaseActivity (상품 구매 페이지)
* 구매 페이지와 장바구니 페이지에서 intent를 통해 넘어온 값들을 화면에 출력
* 주소와 연락처를 입력 후 구매 버튼 클릭 시 상품 구매 완료
* 주소 또는 연락처 미표기 시에 오류 메시지 출력
### Product lass (상품 정보 저장 클래스)
* 상품 이름, 상품 가격, 상품 이미지 파일 이름을 인자로 갖는 클래스
### MainListAdapter class
* BucketActivity 의 ListView와 연결하기 위한 Adapter Class
---
## 프로젝트 시연 영상
* Youtube Link : https://youtu.be/G5LYY0VxMlE
## Reference
* Firebase Document : https://firebase.google.com/docs?hl=ko

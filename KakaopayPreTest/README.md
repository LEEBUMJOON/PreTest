#  사전과제 2 - 쿠폰 시스템  

## 개발 환경  
- 기본 환경   
    - IDE: Eclipse  
    - OS: Window  
    - GIT  
- Server
    - Java8
    - Spring Boot 
    - JPA
    - H2
    - Gradle
    - Junit  


- 접속 Base URI: `http://localhost:8080`


## 빌드(Eclipse ide 환경 )
 1. Git Clone https://github.com/LEEBUMJOON/PreTest.git
 2. Gradle Project import
 3. Refresh Gradle proejct
 4. Build
 5. Run Spring Boot


## 제약사항(필수)
### 필수사항
- API 기능명세에서 제시한 기능을 개발하세요.
- 단위 테스트 (Unit Test) 코드를 개발하여 각 기능을 검증하세요.
- 프로그램 언어는 평가에 반영되지 않으니 자유롭게 선택하세요.
- 각 API의 HTTP Method들( GET | POST | PUT | DEL )은 자유롭게 선택하세요

## 도메인(ENTITY)
- Coupon(id code issuance applStartDate applEndDate ) 
- User(id userid couponCode useYn cancleYn excutionDate )


## Source 목록
### Controller
- CouponInfoCtrl.java 

### Domain
- Coupon.java
- User.java

### Repository
- CouponRepository.java
- UserRepository.java

### Service 
- CouponService.java

### DTO
- CouponLitDto.java
- UserListDto.java

### UTIL CLASS
- DateUtil.java
- StringUtil.java


## API 기능명세 
### 랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관하는 API
- count 갯수 만큼 쿠폰 생성
- 쿠폰 번호 형식 
- "문자(C)" +현재일자 +"브랜드코드 "+ 일련번호 17자리 =  C 20210320 0001 4966
- 쿠폰유효기간은 현재일자 ~ 현재일자 +3개월 
- Request (/coupon/create) 
- Controller METHOD 명 : createCoupon , @RequestParam(value = "count") int  count
- SERVICE : CouponService createCoupon
- UNIT TEST : CouponServiceTest.testCouponSave
- 출력 예시 
	
	```
		쿠폰번호 : C2021032100014508
		쿠폰번호 : C2021032100012687
		쿠폰번호 : C2021032100012248
		쿠폰번호 : C2021032100016136
		쿠폰번호 : C2021032100014594
		쿠폰번호 : C2021032100017627
		쿠폰번호 : C2021032100010025
		쿠폰번호 : C2021032100010270
		쿠폰번호 : C2021032100012794
		쿠폰번호 : C2021032100016573
	```
	
### 생성된 쿠폰중 하나를 사용자에게 지급하는 API
- Request ("/coupon/Issuance") 
- Controller : CouponInfoCtrl.issuanceCoupon , @RequestParam(value = "userId") String userId
- SERVICE : couponService.issanceCoupunForUser
- UNIT TEST : CouponServiceTest.testIssuanceCoupon
- 출력예시 
	
	```
		쿠폰번호 : C2021032100014723 사용자Id : A0001
	```

### 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회하는 API
- Request ("/coupon/getExpir") 
- Controller : CouponInfoCtrl.getCouponExpirByDate , @RequestParam(value  = "applDate") String applDate
- SERVICE : couponService.getCouponExpribyDate
- UNIT TEST : CouponServiceTest.testGetCouponExpirDate
- 출력예시
	
	```
		쿠폰번호 : C2021032100017050  // 만료일자 : 20210321
		쿠폰번호 : C2021032100016879  // 만료일자 : 20210321
		쿠폰번호 : C2021032100017945  // 만료일자 : 20210321
		쿠폰번호 : C2021032100016411  // 만료일자 : 20210321
		쿠폰번호 : C2021032100010723  // 만료일자 : 20210321
		쿠폰번호 : C2021032100012293  // 만료일자 : 20210321
		쿠폰번호 : C2021032100010044  // 만료일자 : 20210321
		쿠폰번호 : C2021032100017828  // 만료일자 : 20210321
		쿠폰번호 : C2021032100018582  // 만료일자 : 20210321
		쿠폰번호 : C2021032100015012  // 만료일자 : 20210321
		쿠폰번호 : C2021032100011664  // 만료일자 : 20210321
	```
	
### 사용자에게 지급된 쿠폰을 조회하는 API
- Request ("/coupon/getusercoupon") 
- Controller : CouponInfoCtrl.getUserCoupon , @RequestParam(value ="userid") String userId
- SERVICE : couponService.getUserCoupon
- UNIT TEST : CouponServiceTest.testGetUserCoupon
- 출력예시 

	```
		사용자 id : A0001  //   쿠폰번호 C2021032100015829
	```

### 지급된 쿠폰중 하나를 사용하는 API  (쿠폰 재사용은 불가)
- Request ("/coupon/useCoupon") 
- Controller : CouponInfoCtrl.useCoupon , @RequestParam(value ="code") String code 
- SERVICE : couponService.userCouponUseByCode
- UNIT TEST : CouponServiceTest.testUserCouponUseByCode
- 출력예시 

	```
		사용자 id : A0001  //   쿠폰번호 C202103210001370 // 사용여부: Y // 사용일자 : 20210321
	```

###  지급된 쿠폰중 하나를 사용 취소하는 API (취소된 쿠폰 재사용 가능)
- Request ("/coupon/cancleCoupon") 
- Controller : CouponInfoCtrl.cancleCoupon , @RequestParam(value ="code") String code 
- SERVICE : couponService.userCouponUseByCode
- UNIT TEST : CouponServiceTest.testUserCouponUseByCode
- 출력예시 

	```
		사용자 id : A0001  //   쿠폰번호 : C2021032100018897 // 사용여부 :   // 취소여부  : Y // 취소일자 : 20210321
	```

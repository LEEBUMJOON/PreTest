# īī������ �������� 3 - ���� �ý���  

## ���� ȯ��  
- �⺻ ȯ��   
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


- ���� Base URI: `http://localhost:8080`


## ����(Eclipse ide ȯ�� )
 1. Git Clone https://github.com/LEEBUMJOON/PreTest.git
 2. Gradle Project import
 3. Refresh Gradle proejct
 4. Build
 5. Run Spring Boot


## �������(�ʼ�)
### �ʼ�����
- API ��ɸ����� ������ ����� �����ϼ���.
- ���� �׽�Ʈ (Unit Test) �ڵ带 �����Ͽ� �� ����� �����ϼ���.
- ���α׷� ���� �򰡿� �ݿ����� ������ �����Ӱ� �����ϼ���.
- �� API�� HTTP Method��( GET | POST | PUT | DEL )�� �����Ӱ� �����ϼ���

## ������(ENTITY)
- Coupon(id code issuance applStartDate applEndDate ) 
- User(id userid couponCode useYn cancleYn excutionDate )

## API ��ɸ� 
### ������ �ڵ��� ������ N�� �����Ͽ� �����ͺ��̽��� �����ϴ� API
- count ���� ��ŭ ���� ����
- ���� ��ȣ ���� 
- "����(C)" +�������� +"�귣���ڵ� "+ �Ϸù�ȣ 17�ڸ� =  C 20210320 0001 4966
- ������ȿ�Ⱓ�� �������� ~ �������� +3���� 
- Request (/coupon/create) 
- Controller METHOD �� : createCoupon , @RequestParam(value = "count") int  count
- SERVICE : CouponService createCoupon
- UNIT TEST : CouponServiceTest.testCouponSave
- ��� ���� 
	
	```
		������ȣ : C2021032100014508
		������ȣ : C2021032100012687
		������ȣ : C2021032100012248
		������ȣ : C2021032100016136
		������ȣ : C2021032100014594
		������ȣ : C2021032100017627
		������ȣ : C2021032100010025
		������ȣ : C2021032100010270
		������ȣ : C2021032100012794
		������ȣ : C2021032100016573
	```
	
### ������ ������ �ϳ��� ����ڿ��� �����ϴ� API
- Request ("/coupon/Issuance") 
- Controller : CouponInfoCtrl.issuanceCoupon , @RequestParam(value = "userId") String userId
- SERVICE : couponService.issanceCoupunForUser
- UNIT TEST : CouponServiceTest.testIssuanceCoupon
- ��¿��� 
	
	```
		������ȣ : C2021032100014723 �����Id : A0001
	```

### �߱޵� ������ ���� ����� ��ü ���� ����� ��ȸ�ϴ� API
- Request ("/coupon/getExpir") 
- Controller : CouponInfoCtrl.getCouponExpirByDate , @RequestParam(value  = "applDate") String applDate
- SERVICE : couponService.getCouponExpribyDate
- UNIT TEST : CouponServiceTest.testGetCouponExpirDate
- ��¿���
	
	```
		������ȣ : C2021032100017050  // �������� : 20210321
		������ȣ : C2021032100016879  // �������� : 20210321
		������ȣ : C2021032100017945  // �������� : 20210321
		������ȣ : C2021032100016411  // �������� : 20210321
		������ȣ : C2021032100010723  // �������� : 20210321
		������ȣ : C2021032100012293  // �������� : 20210321
		������ȣ : C2021032100010044  // �������� : 20210321
		������ȣ : C2021032100017828  // �������� : 20210321
		������ȣ : C2021032100018582  // �������� : 20210321
		������ȣ : C2021032100015012  // �������� : 20210321
		������ȣ : C2021032100011664  // �������� : 20210321
	```
	
### ����ڿ��� ���޵� ������ ��ȸ�ϴ� API
- Request ("/coupon/getusercoupon") 
- Controller : CouponInfoCtrl.getUserCoupon , @RequestParam(value ="userid") String userId
- SERVICE : couponService.getUserCoupon
- UNIT TEST : CouponServiceTest.testGetUserCoupon
- ��¿��� 

	```
		����� id : A0001  //   ������ȣ C2021032100015829
	```

### ���޵� ������ �ϳ��� ����ϴ� API  (���� ������ �Ұ�)
- Request ("/coupon/useCoupon") 
- Controller : CouponInfoCtrl.useCoupon , @RequestParam(value ="code") String code 
- SERVICE : couponService.userCouponUseByCode
- UNIT TEST : CouponServiceTest.testUserCouponUseByCode
- ��¿��� 

	```
		����� id : A0001  //   ������ȣ C202103210001370 // ��뿩��: Y // ������� : 20210321
	```

###  ���޵� ������ �ϳ��� ��� ����ϴ� API (��ҵ� ���� ���� ����)
- Request ("/coupon/cancleCoupon") 
- Controller : CouponInfoCtrl.cancleCoupon , @RequestParam(value ="code") String code 
- SERVICE : couponService.userCouponUseByCode
- UNIT TEST : CouponServiceTest.testUserCouponUseByCode
- ��¿��� 

	```
		����� id : A0001  //   ������ȣ : C2021032100018897 // ��뿩�� :   // ��ҿ���  : Y // ������� : 20210321
	```
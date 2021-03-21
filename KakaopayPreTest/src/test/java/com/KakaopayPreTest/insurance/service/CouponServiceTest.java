package com.KakaopayPreTest.insurance.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.KakaopayPreTest.insurance.domain.Coupon;
import com.KakaopayPreTest.insurance.domain.User;
import com.KakaopayPreTest.insurance.repository.CouponRepository;
import com.KakaopayPreTest.insurance.repository.UserRepository;
import com.KakaopayPreTest.insurance.response.dto.CouponLitDto;
import com.KakaopayPreTest.insurance.response.dto.UserListDto;
import com.KakaopayPreTest.insurance.util.ConstantsVariable;
import com.KakaopayPreTest.insurance.util.DateUtil;
import com.KakaopayPreTest.insurance.util.StringUtil;


@SpringBootTest
@Transactional
class CouponServiceTest {

	
	@Autowired
	private CouponService couponService ;
	
	@Autowired
	private  CouponRepository couponRepository;
	
	@Autowired
	private  UserRepository userRepository; 
	
	@BeforeEach
	void setUp() throws Exception {
		couponService = new CouponService(couponRepository, userRepository);
	}

	/**
	 *  API .1 �����߱� 
	 *  ������ �ڵ��� ������ N�� �����Ͽ� �����ͺ��̽��� �����ϴ� API�� �����ϼ���
	 */
	@Test
	@Commit
	void testCouponSave() {

		CouponLitDto couponListDto = new CouponLitDto();  
		ArrayList<Coupon> couponList  = new ArrayList<Coupon>();
		String code = "";
		int cNum ;
		double  cRandomNumber;
		int count = 10;
		for (int i = 0; i <= count; i++) {
			Coupon coupon = new Coupon();
			cRandomNumber = Math.random();
			cNum = (int) (cRandomNumber * 10000) + 1;
			code = ConstantsVariable.DEFAULT_PRE_STRING + DateUtil.getCurrentDate("") + "0001" + String.valueOf(cNum);
			coupon.setCode(code);
			coupon.setApplStartDate(DateUtil.getCurrentDate(""));
			coupon.setApplEndDate(DateUtil.addMonth(DateUtil.getCurrentDate(""), 3));
			coupon.setIssuance("N");
			couponList.add(coupon);
		}
		
		couponListDto.setCouponList(couponList);
		
		couponService.couponSave(couponListDto);
		
		
		
	}
	
	/**
	 * �� ���� ���� 
	 * API2. ������ ������ �ϳ��� ����ڿ��� �����ϴ� API�� �����ϼ���
	 */
	@Test	
	void testIssuanceCoupon() {
		//���� �߱�
		CouponLitDto couponListDto = new CouponLitDto();  
		ArrayList<Coupon> couponList  = new ArrayList<Coupon>();
		String code = "";
		int cNum ;
		double  cRandomNumber;
		int count = 10;
		for (int i = 0; i <= count; i++) {
			Coupon coupon = new Coupon();
			cRandomNumber = Math.random();
			cNum = (int) (cRandomNumber * 10000) + 1;
			code = ConstantsVariable.DEFAULT_PRE_STRING + DateUtil.getCurrentDate("") + "0001" + String.valueOf(cNum);
			coupon.setCode(code);
			coupon.setApplStartDate(DateUtil.getCurrentDate(""));
			coupon.setApplEndDate(DateUtil.addMonth(DateUtil.getCurrentDate(""), 3));
			coupon.setIssuance("N");
			couponList.add(coupon);
		}
		
		couponListDto.setCouponList(couponList);
		
		couponService.couponSave(couponListDto);
		
		 // ���� ���� 
		User user = couponService.issanceCoupunForUser("A0001");
		
		// ���޵� ���� ��ȣ 
		System.out.println("������ȣ : " + user.getCouponCode());
				
	}
	
	/**
	 * ����� ���� ��� ��ȸ
	 * API6. �߱޵� ������ ���� ����� ��ü ���� ����� ��ȸ�ϴ� API�� �����ϼ���. 
	 */
	@Test
	void testGetCouponExpirDate() {
		//���� ���� ���� ����
		CouponLitDto couponListDto = new CouponLitDto();  
		ArrayList<Coupon> couponList  = new ArrayList<Coupon>();
		String code = "";
		int cNum ;
		double  cRandomNumber;
		int count = 10;
		for (int i = 0; i <= count; i++) {
			Coupon coupon = new Coupon();
			cRandomNumber = Math.random();
			cNum = (int) (cRandomNumber * 10000) + 1;
			String strNum = StringUtil.lpad(String.valueOf(cNum), 4, '0');
			code = ConstantsVariable.DEFAULT_PRE_STRING + DateUtil.getCurrentDate("") + "0001" + strNum;
			coupon.setCode(code);
			coupon.setApplStartDate(DateUtil.getCurrentDate(""));
			coupon.setApplEndDate(DateUtil.getCurrentDate(""));
			coupon.setIssuance("N");
			couponList.add(coupon);
		}
		
		couponListDto.setCouponList(couponList);
		
		couponService.couponSave(couponListDto);
		
		//���� ���� ���� ��� ��ȸ 
		CouponLitDto couponLitDtoExp = couponService.getCouponExpribyDate(DateUtil.getCurrentDate(""));
		
		ArrayList<Coupon> couponListExp = couponLitDtoExp.getCouponList();
		
		for(Coupon coupon : couponListExp) { 
			System.out.println("������ȣ : " + coupon.getCode()+"  // "+ "�������� : " +coupon.getApplEndDate() );
		}
	
	}
	
	/**
	 * ���� ���� ��� ��ȸ ��
	 * API3. ����ڿ��� ���޵� ������ ��ȸ�ϴ� API�� �����ϼ���
	 */
	@Test
	void testGetUserCoupon() {
		
		//���� �߱�
				CouponLitDto couponListDto = new CouponLitDto();  
				ArrayList<Coupon> couponList  = new ArrayList<Coupon>();
				String code = "";
				int cNum ;
				double  cRandomNumber;
				int count = 10;
				for (int i = 0; i <= count; i++) {
					Coupon coupon = new Coupon();
					cRandomNumber = Math.random();
					cNum = (int) (cRandomNumber * 10000) + 1;
					code = ConstantsVariable.DEFAULT_PRE_STRING + DateUtil.getCurrentDate("") + "0001" + String.valueOf(cNum);
					coupon.setCode(code);
					coupon.setApplStartDate(DateUtil.getCurrentDate(""));
					coupon.setApplEndDate(DateUtil.addMonth(DateUtil.getCurrentDate(""), 3));
					coupon.setIssuance("N");
					couponList.add(coupon);
				}
				
				couponListDto.setCouponList(couponList);
				
				couponService.couponSave(couponListDto);
				
				 // ���� ���� 
				User user = couponService.issanceCoupunForUser("A0001");
				
				// ���޵� ���� ��ȣ 
				System.out.println("������ȣ : " + user.getCouponCode());
		
				
				// ������ ���� ��� ��ȸ 
				UserListDto userListDto = couponService.getUserCoupon("A0001");
				ArrayList<User> userList = userListDto.getUserList();
				
				for(User userCopon : userList) {
					
					 System.out.println("����� id : "  + userCopon.getUserID() + "  //  " + " ������ȣ " + userCopon.getCouponCode());
					 
				}		
		
	}
	
	/**
	 * ���޵� ���� ��� API 
	 * API4 ���޵� ������ �ϳ��� ����ϴ� API�� �����ϼ���. (���� ������ �Ұ�)
	 */
	@Test
	void testUserCouponUseByCode() {
		
		//���� �߱�
		CouponLitDto couponListDto = new CouponLitDto();  
		ArrayList<Coupon> couponList  = new ArrayList<Coupon>();
		String code = "";
		int cNum ;
		double  cRandomNumber;
		int count = 10;
		for (int i = 0; i <= count; i++) {
			Coupon coupon = new Coupon();
			cRandomNumber = Math.random();
			cNum = (int) (cRandomNumber * 10000) + 1;
			code = ConstantsVariable.DEFAULT_PRE_STRING + DateUtil.getCurrentDate("") + "0001" + String.valueOf(cNum);
			coupon.setCode(code);
			coupon.setApplStartDate(DateUtil.getCurrentDate(""));
			coupon.setApplEndDate(DateUtil.addMonth(DateUtil.getCurrentDate(""), 3));
			coupon.setIssuance("N");
			couponList.add(coupon);
		}
		
		couponListDto.setCouponList(couponList);
		
		couponService.couponSave(couponListDto);
		
		 // ���� ���� 
		User user = couponService.issanceCoupunForUser("A0001");		
		// ���޵� ���� ��ȣ 
		System.out.println("������ȣ : " + user.getCouponCode());

		
		// ������ ���� ��� ��ȸ 
		UserListDto userListDto = couponService.getUserCoupon("A0001");
		ArrayList<User> userList = userListDto.getUserList();		
		
		// ���޵� ���� ��� API
		couponService.userCouponUseByCode(userList.get(0).getCouponCode(),"U");
		
		UserListDto userListDtortn = couponService.getUserCoupon("A0001");
		ArrayList<User> userListRtn = userListDtortn.getUserList();
		
		for(User userCopon : userListRtn) {
			
			 System.out.println("����� id : "  + userCopon.getUserID() + "  //  " + " ������ȣ " + userCopon.getCouponCode() + " // " + 
					 									  "��뿩��: " + userCopon.getUseYn() + " // " + "������� : " + userCopon.getExcutionDate());
			 
		}		
		
	}
	
	/**
	 * ���޵� ���� ��� ���  API 
	 * API 5 ���޵� ������ �ϳ��� ��� ����ϴ� API�� �����ϼ���. (��ҵ� ���� ���� ����)
	 */
	@Test
	void testUserCouponUseByCode2() {
		
		//���� �߱�
		CouponLitDto couponListDto = new CouponLitDto();  
		ArrayList<Coupon> couponList  = new ArrayList<Coupon>();
		String code = "";
		int cNum ;
		double  cRandomNumber;
		int count = 10;
		for (int i = 0; i <= count; i++) {
			Coupon coupon = new Coupon();
			cRandomNumber = Math.random();
			cNum = (int) (cRandomNumber * 10000) + 1;
			code = ConstantsVariable.DEFAULT_PRE_STRING + DateUtil.getCurrentDate("") + "0001" + String.valueOf(cNum);
			coupon.setCode(code);
			coupon.setApplStartDate(DateUtil.getCurrentDate(""));
			coupon.setApplEndDate(DateUtil.addMonth(DateUtil.getCurrentDate(""), 3));
			coupon.setIssuance("N");
			couponList.add(coupon);
		}
		
		couponListDto.setCouponList(couponList);
		
		couponService.couponSave(couponListDto);
		
		 // ���� ���� 
		User user = couponService.issanceCoupunForUser("A0001");		
		// ���޵� ���� ��ȣ 
		System.out.println("������ȣ : " + user.getCouponCode());

		
		// ������ ���� ��� ��ȸ 
		UserListDto userListDto = couponService.getUserCoupon("A0001");
		ArrayList<User> userList = userListDto.getUserList();		
		
		// ���� ��� API
		couponService.userCouponUseByCode(userList.get(0).getCouponCode(), "U");

		UserListDto userListDtortn = couponService.getUserCoupon("A0001");
		ArrayList<User> userListRtn = userListDtortn.getUserList();

		for (User userCopon : userListRtn) {			
			System.out.println("����� id : " + userCopon.getUserID() + "  //  " + " ������ȣ " + userCopon.getCouponCode()
					+ " // " + "��뿩��: " + userCopon.getUseYn() + " // " + "������� : " + userCopon.getExcutionDate());
			
			// ���� ��� ���  API
			couponService.userCouponUseByCode(userCopon.getCouponCode(), "C");
			
		}
		
		//��� ��ȸ 
		UserListDto userListDtoCancleRtn = couponService.getUserCoupon("A0001");
		ArrayList<User> userListCancleRtn = userListDtoCancleRtn.getUserList();
		for (User userCancleCopon : userListCancleRtn) {			
			System.out.println("����� id : " + userCancleCopon.getUserID() + "  //  " + " ������ȣ : " + userCancleCopon.getCouponCode()
					+ " // " + "��뿩�� :  " + userCancleCopon.getUseYn() + "��ҿ���  : " + userCancleCopon.getCancleYn()+  " // " + "������� : " + userCancleCopon.getExcutionDate());
				
		}
		
	}

}

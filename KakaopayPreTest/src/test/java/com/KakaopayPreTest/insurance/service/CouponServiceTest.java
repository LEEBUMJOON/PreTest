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
	 *  API .1 쿠폰발급 
	 *  랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관하는 API를 구현하세요
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
	 * 고객 쿠폰 지급 
	 * API2. 생성된 쿠폰중 하나를 사용자에게 지급하는 API를 구현하세요
	 */
	@Test	
	void testIssuanceCoupon() {
		//쿠폰 발급
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
		
		 // 쿠폰 지급 
		User user = couponService.issanceCoupunForUser("A0001");
		
		// 지급된 쿠폰 번호 
		System.out.println("쿠폰번호 : " + user.getCouponCode());
				
	}
	
	/**
	 * 만료된 쿠폰 목록 조회
	 * API6. 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회하는 API를 구현하세요. 
	 */
	@Test
	void testGetCouponExpirDate() {
		//당일 만료 쿠폰 생성
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
		
		//당일 만료 쿠폰 목록 조회 
		CouponLitDto couponLitDtoExp = couponService.getCouponExpribyDate(DateUtil.getCurrentDate(""));
		
		ArrayList<Coupon> couponListExp = couponLitDtoExp.getCouponList();
		
		for(Coupon coupon : couponListExp) { 
			System.out.println("쿠폰번호 : " + coupon.getCode()+"  // "+ "만료일자 : " +coupon.getApplEndDate() );
		}
	
	}
	
	/**
	 * 지급 쿠폰 목록 조회 고객
	 * API3. 사용자에게 지급된 쿠폰을 조회하는 API를 구현하세요
	 */
	@Test
	void testGetUserCoupon() {
		
		//쿠폰 발급
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
				
				 // 쿠폰 지급 
				User user = couponService.issanceCoupunForUser("A0001");
				
				// 지급된 쿠폰 번호 
				System.out.println("쿠폰번호 : " + user.getCouponCode());
		
				
				// 지급퇸 쿠폰 목록 조회 
				UserListDto userListDto = couponService.getUserCoupon("A0001");
				ArrayList<User> userList = userListDto.getUserList();
				
				for(User userCopon : userList) {
					
					 System.out.println("사용자 id : "  + userCopon.getUserID() + "  //  " + " 쿠폰번호 " + userCopon.getCouponCode());
					 
				}		
		
	}
	
	/**
	 * 지급된 쿠폰 사용 API 
	 * API4 지급된 쿠폰중 하나를 사용하는 API를 구현하세요. (쿠폰 재사용은 불가)
	 */
	@Test
	void testUserCouponUseByCode() {
		
		//쿠폰 발급
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
		
		 // 쿠폰 지급 
		User user = couponService.issanceCoupunForUser("A0001");		
		// 지급된 쿠폰 번호 
		System.out.println("쿠폰번호 : " + user.getCouponCode());

		
		// 지급퇸 쿠폰 목록 조회 
		UserListDto userListDto = couponService.getUserCoupon("A0001");
		ArrayList<User> userList = userListDto.getUserList();		
		
		// 지급된 쿠폰 사용 API
		couponService.userCouponUseByCode(userList.get(0).getCouponCode(),"U");
		
		UserListDto userListDtortn = couponService.getUserCoupon("A0001");
		ArrayList<User> userListRtn = userListDtortn.getUserList();
		
		for(User userCopon : userListRtn) {
			
			 System.out.println("사용자 id : "  + userCopon.getUserID() + "  //  " + " 쿠폰번호 " + userCopon.getCouponCode() + " // " + 
					 									  "사용여부: " + userCopon.getUseYn() + " // " + "사용일자 : " + userCopon.getExcutionDate());
			 
		}		
		
	}
	
	/**
	 * 지급된 쿠폰 사용 취소  API 
	 * API 5 지급된 쿠폰중 하나를 사용 취소하는 API를 구현하세요. (취소된 쿠폰 재사용 가능)
	 */
	@Test
	void testUserCouponUseByCode2() {
		
		//쿠폰 발급
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
		
		 // 쿠폰 지급 
		User user = couponService.issanceCoupunForUser("A0001");		
		// 지급된 쿠폰 번호 
		System.out.println("쿠폰번호 : " + user.getCouponCode());

		
		// 지급퇸 쿠폰 목록 조회 
		UserListDto userListDto = couponService.getUserCoupon("A0001");
		ArrayList<User> userList = userListDto.getUserList();		
		
		// 쿠폰 사용 API
		couponService.userCouponUseByCode(userList.get(0).getCouponCode(), "U");

		UserListDto userListDtortn = couponService.getUserCoupon("A0001");
		ArrayList<User> userListRtn = userListDtortn.getUserList();

		for (User userCopon : userListRtn) {			
			System.out.println("사용자 id : " + userCopon.getUserID() + "  //  " + " 쿠폰번호 " + userCopon.getCouponCode()
					+ " // " + "사용여부: " + userCopon.getUseYn() + " // " + "사용일자 : " + userCopon.getExcutionDate());
			
			// 쿠폰 사용 취소  API
			couponService.userCouponUseByCode(userCopon.getCouponCode(), "C");
			
		}
		
		//결과 조회 
		UserListDto userListDtoCancleRtn = couponService.getUserCoupon("A0001");
		ArrayList<User> userListCancleRtn = userListDtoCancleRtn.getUserList();
		for (User userCancleCopon : userListCancleRtn) {			
			System.out.println("사용자 id : " + userCancleCopon.getUserID() + "  //  " + " 쿠폰번호 : " + userCancleCopon.getCouponCode()
					+ " // " + "사용여부 :  " + userCancleCopon.getUseYn() + "취소여부  : " + userCancleCopon.getCancleYn()+  " // " + "사용일자 : " + userCancleCopon.getExcutionDate());
				
		}
		
	}

}

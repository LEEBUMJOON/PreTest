package com.KakaopayPreTest.insurance.service;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.KakaopayPreTest.insurance.domain.Coupon;
import com.KakaopayPreTest.insurance.repository.CouponRepository;
import com.KakaopayPreTest.insurance.response.dto.CouponLitDto;
import com.KakaopayPreTest.insurance.util.ConstantsVariable;
import com.KakaopayPreTest.insurance.util.DateUtil;


@SpringBootTest
@Transactional
class CouponServiceTest {

	
	@Autowired
	private CouponService couponService ;
	
	@Autowired
	private  CouponRepository couponRepository; 
	
	@BeforeEach
	void setUp() throws Exception {
		couponService = new CouponService(couponRepository);
	}

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
			couponList.add(coupon);
		}
		
		couponListDto.setCouponList(couponList);
		
		couponService.couponSave(couponListDto);
		
	}

}

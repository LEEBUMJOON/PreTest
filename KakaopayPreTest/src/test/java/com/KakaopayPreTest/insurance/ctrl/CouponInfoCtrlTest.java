package com.KakaopayPreTest.insurance.ctrl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.KakaopayPreTest.insurance.controller.CouponInfoCtrl;

@SpringBootTest
@Transactional
class CouponInfoCtrlTest {
	
	@Autowired
	private CouponInfoCtrl couponInfoCtrl;

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testCreateCoupon() {
//		fail("Not yet implemented");
		
		couponInfoCtrl.issuanceCoupon("A0001");
		
	}
	
	

}

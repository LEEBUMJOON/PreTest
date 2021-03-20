package com.KakaopayPreTest.insurance.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KakaopayPreTest.insurance.domain.Coupon;
import com.KakaopayPreTest.insurance.response.dto.CouponLitDto;
import com.KakaopayPreTest.insurance.service.CouponService;
import com.KakaopayPreTest.insurance.util.ConstantsVariable;
import com.KakaopayPreTest.insurance.util.DateUtil;

@RestController
public class CouponInfoCtrl {
	
	private final CouponService couponService;

	/**
	 * @param couponService
	 */
	public CouponInfoCtrl(CouponService couponService) {
		super();
		this.couponService = couponService;
	}
	
	@PostMapping("/coupon/create")
	public ResponseEntity<?> createCoupon(@RequestParam(value = "count") int  count) {
		
		CouponLitDto couponListDto = new CouponLitDto();  
		ArrayList<Coupon> couponList  = new ArrayList<Coupon>();
//쿠폰 번호 형식
// "문자" +현재일자 +"브랜드코드 "+ 일련번호 17자리 
//  C 20210320 0001 4966 
		String code = "";
		int cNum ;
		double  cRandomNumber;

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
		return new ResponseEntity<>(couponListDto ,HttpStatus.OK );
	}

}

package com.KakaopayPreTest.insurance.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KakaopayPreTest.insurance.domain.Coupon;
import com.KakaopayPreTest.insurance.repository.CouponRepository;
import com.KakaopayPreTest.insurance.response.dto.CouponLitDto;

@Service
public class CouponService {
	
	private final CouponRepository couponRepository;


	/**
	 * @param couponRepository
	 */
	public CouponService(CouponRepository couponRepository) {
		super();
		this.couponRepository = couponRepository;
	}
	

	@Transactional()
	public void  couponSave(CouponLitDto couponLitDto ) {		
		ArrayList<Coupon> couponList = couponLitDto.getCouponList();		
		for(Coupon coupon : couponList) {
			couponRepository.save(coupon);
		}
	}
	
}

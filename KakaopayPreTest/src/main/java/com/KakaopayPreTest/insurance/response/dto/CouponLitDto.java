package com.KakaopayPreTest.insurance.response.dto;

import java.util.ArrayList;

import com.KakaopayPreTest.insurance.domain.Coupon;

public class CouponLitDto {


	private ArrayList<Coupon> CouponList = new ArrayList<>();


	/**
	 * @return
	 */
	public ArrayList<Coupon> getCouponList() {
		return CouponList;
	}

	public void setCouponList(ArrayList<Coupon> couponList) {
		CouponList = couponList;
	}

	
	
}

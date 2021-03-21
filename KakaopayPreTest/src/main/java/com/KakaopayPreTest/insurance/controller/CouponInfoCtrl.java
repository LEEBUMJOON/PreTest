package com.KakaopayPreTest.insurance.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KakaopayPreTest.insurance.domain.Coupon;
import com.KakaopayPreTest.insurance.domain.User;
import com.KakaopayPreTest.insurance.response.dto.CouponLitDto;
import com.KakaopayPreTest.insurance.response.dto.UserListDto;
import com.KakaopayPreTest.insurance.service.CouponService;
import com.KakaopayPreTest.insurance.util.ConstantsVariable;
import com.KakaopayPreTest.insurance.util.DateUtil;
import com.KakaopayPreTest.insurance.util.StringUtil;

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
	
	/**
	 *  API 1. 랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관하는 API
	 *  count 갯수 만큼 쿠폰 생성
	 *  쿠폰 번호 형식 
	 *  "문자(C)" +현재일자 +"브랜드코드 "+ 일련번호 17자리 =  C 20210320 0001 4966
	 *  쿠폰유효기간은 현재일자 ~ 현재일자 +3개월 
	 * @param count
	 * @return
	 */
	@PostMapping("/coupon/create")
	public ResponseEntity<?> createCoupon(@RequestParam(value = "count") int  count) {
	
		CouponLitDto couponListDto = couponService.createCoupon(count);
		
		return new ResponseEntity<>(couponListDto ,HttpStatus.OK );
	}
	
	
	/**
	 *  사용자에게 쿠폰 지급 
	 *  API 2. 생성된 쿠폰중 하나를 사용자에게 지급하는 API
	 * @param userId
	 * @return
	 */
	@PostMapping("/coupon/Issuance")
	public ResponseEntity<?> issuanceCoupon(@RequestParam(value = "userId") String userId) {
		
		User user = couponService.issanceCoupunForUser(userId);
		
		return new ResponseEntity<>(user ,HttpStatus.OK );
	}
	
	/**
	 * 날짜 기준 만료된 쿠폰 목록 조회  
	 * API 6. 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회하는 API
	 * @param applDate
	 * @return
	 */
	@GetMapping("/coupon/getExpir")
	public ResponseEntity<?> getCouponExpirByDate(@RequestParam(value  = "applDate") String applDate){
		
		CouponLitDto couponListDto = couponService.getCouponExpribyDate(applDate);
		ArrayList<Coupon> couponList = couponListDto.getCouponList();
		
		return new ResponseEntity<>(couponList, HttpStatus.OK);
		
	}
	
	/**
	 * 사용자에게 지급된 쿠폰 목록 조회 
	 * API 3. 사용자에게 지급된 쿠폰을 조회하는 API
	 * @param userId
	 * @return
	 */
	@GetMapping("/coupon/getusercoupon")
	public ResponseEntity<?> getUserCoupon(@RequestParam(value ="userid") String userId){
		UserListDto userListDto = couponService.getUserCoupon(userId);
		
		return new ResponseEntity<>(userListDto, HttpStatus.OK);
	}
	
	/**
	 * 지급된 쿠폰중 하나를 사용하는 API  (쿠폰 재사용은 불가)
	 * @param code
	 * @param useCls
	 * @return
	 */
	@PostMapping("/coupon/useCoupon")
	public ResponseEntity<?> useCoupon(@RequestParam(value ="code") String code ){

		UserListDto userListDto =  couponService.userCouponUseByCode(code, "U");
		

		return new ResponseEntity<>(userListDto, HttpStatus.OK);
	}
	
	/**
	 * 5. 지급된 쿠폰중 하나를 사용 취소하는 API (취소된 쿠폰 재사용 가능)
	 * @param code
	 * @param useCls
	 * @return
	 */
	@PostMapping("/coupon/cancleCoupon")
	public ResponseEntity<?> cancleCoupon(@RequestParam(value ="code") String code){
		
		UserListDto userListDto =  couponService.userCouponUseByCode(code, "C");
		
		return new ResponseEntity<>(userListDto, HttpStatus.OK);
	}
	
	/**
	 * 발급된 쿠폰중 만료 현재일자 + expDay 일전 사용자(userId)에게 메세지(“쿠폰이 3일 후 만료됩니다.”)를 발송
	 * @param userId
	 * @param expDay
	 * @return
	 */
	@GetMapping("/coupon/getexpcouponexpday")
	public ResponseEntity<?>getExpCouponExpDay(@RequestParam(value = "userId") String userId , @RequestParam(value = "expday") int expDay){
		String expMessage = "";
		expMessage = couponService.getExpCouponAnnou(userId, expDay);
		
		return new ResponseEntity<>(expMessage, HttpStatus.OK);
	}

}

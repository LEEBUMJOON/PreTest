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
	 *  API 1. ������ �ڵ��� ������ N�� �����Ͽ� �����ͺ��̽��� �����ϴ� API
	 *  count ���� ��ŭ ���� ����
	 *  ���� ��ȣ ���� 
	 *  "����(C)" +�������� +"�귣���ڵ� "+ �Ϸù�ȣ 17�ڸ� =  C 20210320 0001 4966
	 *  ������ȿ�Ⱓ�� �������� ~ �������� +3���� 
	 * @param count
	 * @return
	 */
	@PostMapping("/coupon/create")
	public ResponseEntity<?> createCoupon(@RequestParam(value = "count") int  count) {
	
		CouponLitDto couponListDto = couponService.createCoupon(count);
		
		return new ResponseEntity<>(couponListDto ,HttpStatus.OK );
	}
	
	
	/**
	 *  ����ڿ��� ���� ���� 
	 *  API 2. ������ ������ �ϳ��� ����ڿ��� �����ϴ� API
	 * @param userId
	 * @return
	 */
	@PostMapping("/coupon/Issuance")
	public ResponseEntity<?> issuanceCoupon(@RequestParam(value = "userId") String userId) {
		
		User user = couponService.issanceCoupunForUser(userId);
		
		return new ResponseEntity<>(user ,HttpStatus.OK );
	}
	
	/**
	 * ��¥ ���� ����� ���� ��� ��ȸ  
	 * API 6. �߱޵� ������ ���� ����� ��ü ���� ����� ��ȸ�ϴ� API
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
	 * ����ڿ��� ���޵� ���� ��� ��ȸ 
	 * API 3. ����ڿ��� ���޵� ������ ��ȸ�ϴ� API
	 * @param userId
	 * @return
	 */
	@GetMapping("/coupon/getusercoupon")
	public ResponseEntity<?> getUserCoupon(@RequestParam(value ="userid") String userId){
		UserListDto userListDto = couponService.getUserCoupon(userId);
		
		return new ResponseEntity<>(userListDto, HttpStatus.OK);
	}
	
	/**
	 * ���޵� ������ �ϳ��� ����ϴ� API  (���� ������ �Ұ�)
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
	 * 5. ���޵� ������ �ϳ��� ��� ����ϴ� API (��ҵ� ���� ���� ����)
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
	 * �߱޵� ������ ���� �������� + expDay ���� �����(userId)���� �޼���(�������� 3�� �� ����˴ϴ�.��)�� �߼�
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

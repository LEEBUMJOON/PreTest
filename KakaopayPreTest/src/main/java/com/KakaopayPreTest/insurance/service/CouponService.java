package com.KakaopayPreTest.insurance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KakaopayPreTest.insurance.domain.Coupon;
import com.KakaopayPreTest.insurance.domain.User;
import com.KakaopayPreTest.insurance.repository.CouponRepository;
import com.KakaopayPreTest.insurance.repository.UserRepository;
import com.KakaopayPreTest.insurance.response.dto.CouponLitDto;
import com.KakaopayPreTest.insurance.response.dto.UserListDto;
import com.KakaopayPreTest.insurance.util.DateUtil;
import com.KakaopayPreTest.insurance.util.StringUtil;

@Service
public class CouponService {
	
	private final CouponRepository couponRepository;
	
	private final UserRepository userRepository;


	/**
	 * @param couponRepository
	 */
	public CouponService(CouponRepository couponRepository , UserRepository userRepository ) {
		super();		
		this.couponRepository = couponRepository;
		this.userRepository = userRepository;
	}
	

	/**
	 * 쿠폰 발급 
	 * @param couponLitDto
	 */
	@Transactional()
	public void  couponSave(CouponLitDto couponLitDto ) {		
		ArrayList<Coupon> couponList = couponLitDto.getCouponList();		
		for(Coupon coupon : couponList) {
			couponRepository.save(coupon);
		}
	}
	
	
/**
 * APPL DATE 일자에 유효한 쿠폰 조회 발급일자가 가능 빠른 건 
 * @param currentDate
 * @return
 */
	@Transactional
	public Coupon getNonIssuanceCouponByApplDate(String applDate) {
		List<Coupon> couponList = new ArrayList<Coupon>();
		Coupon couponRlt = new Coupon();
		
		if (StringUtil.isEmpty(applDate)) { 
			applDate = DateUtil.getCurrentDate("");
		}
		couponList = couponRepository.getNoneIssuanceCoupuon(applDate);
		couponRlt = couponList.get(0);
		return couponRlt;
	}
	
	/**
	 * 고객 쿠폰 지급 서비스 
	 * 현재일자 기준 유효한 쿠폰 목록 중 발급 일자가 가장 최신 건을 고객에게 지급.
	 * @param userId
	 * @return
	 */
	@Transactional
	public User issanceCoupunForUser(String userId) {
		User userRlt = new User();
		Coupon coupon = this.getNonIssuanceCouponByApplDate(DateUtil.getCurrentDate(""));
		if (coupon !=null ) {
			coupon.setIssuance("Y");
			couponRepository.save(coupon);
			
			userRlt.setUserID(userId);
			userRlt.setCouponCode(coupon.getCode());
						
			userRepository.save(userRlt);
		}
	
		return userRlt;
	}
	
	/**
	 * 날짜 기준 만료 되는 쿠폰 리스트를 리턴 한다.
	 * @param applDate
	 * @return
	 */
	public CouponLitDto  getCouponExpribyDate(String applDate) {
		CouponLitDto couponLitDto = new CouponLitDto();
		ArrayList<Coupon> couponList = new ArrayList<Coupon>();
		if (StringUtil.isEmpty(applDate)) {
			applDate = DateUtil.getCurrentDate("");
		}
		
		couponList = couponRepository.getCouponExpiraedByDate(applDate);
		if (couponList.size() > 0) {
			couponLitDto.setCouponList(couponList);
		}

		return couponLitDto;
	}
	
	
	/**
	 * 사용자에게 지급된 쿠폰 목록 조회 
	 * @param userId
	 * @return
	 */
	public UserListDto getUserCoupon(String userId) {
		UserListDto userListDto = new UserListDto();
		ArrayList<User> userList = userRepository.getUserCoupon(userId);
		userListDto.setUserList(userList);
		return userListDto;
	}
	
	/**
	 * 1. U : 지급된 쿠폰 사용
	 * 2. C :  지급된 쿠폰 취소 
	 * @param couponCode
	 * @return
	 */
	public UserListDto userCouponUseByCode(String couponCode , String useCls) {		
		UserListDto userListDto = new UserListDto();
		ArrayList<User> userList = userRepository.getUserCouponByCode(couponCode);		
		for (User userCoupon : userList) {
			if (useCls.equals("U") &&  ( userCoupon.getUseYn().equals("") || userCoupon.getCancleYn().equals("Y") ) ) {				
				userCoupon.setUseYn("Y");
				userCoupon.setExcutionDate(DateUtil.getCurrentDate(""));
				
			} else if (useCls.equals("C") && userCoupon.getUseYn().equals("Y")) {
				
				userCoupon.setUseYn("");
				userCoupon.setCancleYn("Y");
				userCoupon.setExcutionDate(DateUtil.getCurrentDate(""));
				
			}
			userRepository.save(userCoupon);
		}
		 userListDto.setUserList(userList);
		return userListDto;
	}
	
}

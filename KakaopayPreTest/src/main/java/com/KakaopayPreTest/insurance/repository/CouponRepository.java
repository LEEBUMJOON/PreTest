package com.KakaopayPreTest.insurance.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.KakaopayPreTest.insurance.domain.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long >{ 
	

	/**
	 * applDate 기준 유효한 미지급 쿠폰 목록 조회 
	 * @param applDate
	 * @return
	 */
	@Query(value = "SELECT * FROM COUPON  WHERE ISSUANCE = 'N' AND APPL_START_DATE <= :applDate  AND APPL_END_DATE >= :applDate ORDER BY APPL_START_DATE ASC "
					  , nativeQuery = true)
	List<Coupon> getNoneIssuanceCoupuon(@Param("applDate") String applDate);

	/**
	 * applDate 기준 만료된 쿠폰 목록 조회 
	 * @param appldate
	 * @return
	 */
	@Query(value = "SELECT * FROM COUPON  WHERE APPL_END_DATE <= :applDate ORDER BY APPL_START_DATE ASC "
			  , nativeQuery = true)
	ArrayList<Coupon> getCouponExpiraedByDate(@Param("applDate" ) String appldate);
}

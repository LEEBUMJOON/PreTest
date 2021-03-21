package com.KakaopayPreTest.insurance.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.KakaopayPreTest.insurance.domain.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long > {

	@Query(value = "SELECT * FROM USER  WHERE userid = :userId  "
			  , nativeQuery = true)
	ArrayList<User> getUserCoupon(@Param("userId") String userId);
	
	@Query(value = "SELECT * FROM USER  WHERE  coupon_code = :code "
			  , nativeQuery = true)
	ArrayList<User> getUserCouponByCode(@Param("code") String code);
}

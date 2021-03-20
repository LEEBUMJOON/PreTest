package com.KakaopayPreTest.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KakaopayPreTest.insurance.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long >{ 

}

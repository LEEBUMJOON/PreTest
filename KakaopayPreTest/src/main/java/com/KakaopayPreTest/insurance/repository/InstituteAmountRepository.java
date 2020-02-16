package com.KakaopayPreTest.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KakaopayPreTest.insurance.domain.Amount;

public interface InstituteAmountRepository extends JpaRepository< Amount , Long> {
}

package com.KakaopayPreTest.insurance.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InstituteAmountRepositoryTest {
	
	@Autowired
	private InstituteAmountRepository instituteAmountRepository; 
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAmountSelect() {
//		fail("Not yet implemented");
		System.out.println("------------------");
		instituteAmountRepository.findAll();
		System.out.println("------------------");
		
	}

}

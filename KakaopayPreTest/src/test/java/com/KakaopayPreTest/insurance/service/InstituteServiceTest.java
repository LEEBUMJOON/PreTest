package com.KakaopayPreTest.insurance.service;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.KakaopayPreTest.insurance.repository.InstituteAmountRepository;
import com.KakaopayPreTest.insurance.repository.InstituteRepository;
import com.KakaopayPreTest.insurance.response.dto.InstituteListResponseDto;

@SpringBootTest
class InstituteServiceTest {
	@Autowired
	private  InstituteRepository instituteRepository;
	
	@Autowired
	private InstituteAmountRepository instituteAmountRepository; 
	
	private  InstituteService instituteService;
	 
	@BeforeEach
	void setUp() throws Exception {
		instituteService = new InstituteService( instituteRepository , instituteAmountRepository);
	}

	@Test
	void test() {
		//fail("Not yet implemented");
		List<InstituteListResponseDto> resultList = instituteService.getInstitueList();
		for(InstituteListResponseDto instituteListResponseDto : resultList) {
			System.out.println(instituteListResponseDto.getInstituteCode() + "_" + instituteListResponseDto.getInstituteName());
		}
		
	}

}

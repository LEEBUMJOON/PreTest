package com.KakaopayPreTest.insurance.repository;

import java.util.ArrayList;
import java.util.List;

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
		//instituteAmountRepository.getAmountTotalYear();
		instituteAmountRepository.getDetailAmount();
		System.out.println("------------------");		
	}
	
	@Test
	void testAmountSelectDetatil() {
//		fail("Not yet implemented");
		System.out.println("------------------");
		
		Object[] objArray= { "2004","P01", "한국은행","1234123"};
		
		List<Object[]> IntituteDetailTotalAmount = new ArrayList<>();
		IntituteDetailTotalAmount.add(objArray);

		for (Object[] obj : IntituteDetailTotalAmount ) {
			System.out.println(obj[3].toString());
		}
		
		System.out.println("------------------");		
	}
	
	@Test
	void testMinMax() {
		List<Object[]> instituteMinMaxList = instituteAmountRepository.getInstitueMinMaxAvg("B07"); // 평균값 기준  오름차순  정렬
		if (instituteMinMaxList != null &&  instituteMinMaxList.size() > 0) {
			Object[] obj = instituteMinMaxList.get(0);
		}
		System.out.println("------------------");		
	}

}

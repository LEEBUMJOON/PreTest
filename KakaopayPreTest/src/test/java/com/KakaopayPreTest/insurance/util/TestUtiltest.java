package com.KakaopayPreTest.insurance.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestUtiltest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
//		fail("Not yet implemented");
		
		TestUtil testUtil = new TestUtil();
		 String[] array = { "ONE" , "TWO", "THREE","FOUR"," "," "};
		 
		int indx =  testUtil.getArrayValStrartIdx(array);
		int indx2 = testUtil.getArrayValEndIdx(array);
		System.out.println("----------------------------");
		 System.out.println(indx);
		 System.out.println(indx2);
	}

}

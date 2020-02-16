package com.KakaopayPreTest.insurance.util;

import org.apache.commons.lang3.StringUtils;

public class TestUtil {

	public static int  getArrayValStrartIdx(String[] array) {	
		int strartIdx  = 0;
		for ( int i = 0 ; i < array.length ; i ++) {

			if  ( StringUtils.isNotEmpty(StringUtils.trim(array[i])) ) {
				break;
			}						
			strartIdx++;
		}		
		
		return		strartIdx ; 
	}
	
	public static int getArrayValEndIdx (String[] array) {	
		int endIdx  =array.length -1;
		for ( int i = array.length -1 ; i >= 0 ; i--) { 
			if  ( StringUtils.isNotEmpty(StringUtils.trim(array[i])) ) {
				break;
			}
			endIdx--;
		}
		
		return endIdx;
	}
	
}

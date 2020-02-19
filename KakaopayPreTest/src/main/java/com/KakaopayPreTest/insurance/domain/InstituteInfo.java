package com.KakaopayPreTest.insurance.domain;

public enum InstituteInfo {
	
	NHUF("주택도시기금", "P01"),
   KB("국민은행", "B01"), 
   WOORI("우리은행", "B02"), 
   SHINHAN("신한은행", "B03"),
	KOREA_CITY("한국시티은행", "B04"), 
	HANA("하나은행", "B05"), 
	NH_SH("농협은행/수협은행", "B06"), 
	KEB("외환은행", "B07"),
	ETC_BANK("기타은행", "bank99");

	private final String name;
	private final String code;

	InstituteInfo(final String name, final String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;	
	}
	
	/**
	 * @param name
	 * @return
	 * 명칭을 변환 
	 * ex)국민은행(억원) - > 국민은행 
	 */
	public  static String covertName(String name) {
		String covertName = null;
		for( InstituteInfo instituteEntry : InstituteInfo.values()) {
			if(name.contains(instituteEntry.getName())) {
				covertName = instituteEntry.getName();
				break;
			}			
		}
		return covertName;
	}
	
	
	/**
	 * @param name
	 * @return
	 * 명칭을 코드로 변환 
	 * ex)국민은행(억원) - > A01 
	 */
	public  static String covertNameToCode(String name) {
		String covertCode = null;
		for( InstituteInfo instituteEntry : InstituteInfo.values()) {
			if(name.contains(instituteEntry.getName())) {
				covertCode = instituteEntry.getCode();
				break;
			}			
		}
		return covertCode;
	}
}

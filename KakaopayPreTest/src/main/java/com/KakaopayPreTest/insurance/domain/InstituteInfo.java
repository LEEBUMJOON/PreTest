package com.KakaopayPreTest.insurance.domain;

public enum InstituteInfo {
	
	NHUF("���õ��ñ��", "P01"),
   KB("��������", "B01"), 
   WOORI("�츮����", "B02"), 
   SHINHAN("��������", "B03"),
	KOREA_CITY("�ѱ���Ƽ����", "B04"), 
	HANA("�ϳ�����", "B05"), 
	NH_SH("��������/��������", "B06"), 
	KEB("��ȯ����", "B07"),
	ETC_BANK("��Ÿ����", "bank99");

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
	 * ��Ī�� ��ȯ 
	 * ex)��������(���) - > �������� 
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
	 * ��Ī�� �ڵ�� ��ȯ 
	 * ex)��������(���) - > A01 
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

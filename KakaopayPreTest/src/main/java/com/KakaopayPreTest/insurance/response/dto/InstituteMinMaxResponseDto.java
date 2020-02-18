package com.KakaopayPreTest.insurance.response.dto;

import java.util.ArrayList;
import java.util.List;

public class InstituteMinMaxResponseDto {
	
	private String bank;
	
	private List<SupportAmountDto> supportAmountDto = new  ArrayList<>();

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public List<SupportAmountDto> getSupportAmountDto() {
		return supportAmountDto;
	}

	public void setSupportAmountDto(List<SupportAmountDto> supportAmountDto) {
		this.supportAmountDto = supportAmountDto;
	}

}

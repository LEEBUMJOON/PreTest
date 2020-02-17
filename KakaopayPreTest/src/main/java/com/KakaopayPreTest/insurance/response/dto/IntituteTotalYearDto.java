package com.KakaopayPreTest.insurance.response.dto;

import java.util.ArrayList;
import java.util.List;

public class IntituteTotalYearDto {
	
	private String year;
	
	private int amount;
	
	private List<IntituteDetailTotalAmountDto> IntituteDetailTotalAmountDtoList = new ArrayList<>();

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public List<IntituteDetailTotalAmountDto> getIntituteDetailTotalAmountDtoList() {
		return IntituteDetailTotalAmountDtoList;
	}

	public void setIntituteDetailTotalAmountDtoList(List<IntituteDetailTotalAmountDto> intituteDetailTotalAmountDtoList) {
		IntituteDetailTotalAmountDtoList = intituteDetailTotalAmountDtoList;
	}
	
	
 
}

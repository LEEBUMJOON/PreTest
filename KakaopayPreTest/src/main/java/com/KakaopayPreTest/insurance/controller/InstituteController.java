package com.KakaopayPreTest.insurance.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KakaopayPreTest.insurance.response.dto.InstituteAmountMaxResponseDto;
import com.KakaopayPreTest.insurance.response.dto.InstituteListResponseDto;
import com.KakaopayPreTest.insurance.response.dto.InstituteMinMaxResponseDto;
import com.KakaopayPreTest.insurance.response.dto.IntituteTotalYearDto;
import com.KakaopayPreTest.insurance.service.InstituteService;

@RestController
public class InstituteController {
	
	private InstituteService instituteService;

	/**
	 * @param institute
	 */
	public InstituteController(InstituteService instituteService) {		
		this.instituteService = instituteService;
	}
	
	@GetMapping("/InstiuteList")
	public ResponseEntity<?> getInstiuteList( ){
		List<InstituteListResponseDto> InstituteListResponseDtoList = instituteService.getInstitueList();
		
		return new ResponseEntity<>(InstituteListResponseDtoList , HttpStatus.OK);
	}
	
	@GetMapping("/Instiute/Year")
	public ResponseEntity<?> getAmoutTotalYear(){
		List<IntituteTotalYearDto> intituteTotalYearDtoList =  instituteService.getAmoutTotal();
		
		return new ResponseEntity<>(intituteTotalYearDtoList , HttpStatus.OK);
	}
	
	@GetMapping("/Instiute/Year/Max")
	public ResponseEntity<?> getInstituteAmountMax(){
		InstituteAmountMaxResponseDto instituteAmountMaxResponseDto = instituteService.getInstituteAmountMax();
		
		return new ResponseEntity<>(instituteAmountMaxResponseDto , HttpStatus.OK);
	}
	
	@GetMapping("/Instiute/Year/MinMax")
	public ResponseEntity<?> getInstituteAmountMinMax(@RequestParam(value = "bankname") String bankName) { 
		InstituteMinMaxResponseDto instituteMinMaxResponseDto =  instituteService.getInstituteMinMaxAvg(bankName);
		return new ResponseEntity<>(instituteMinMaxResponseDto ,HttpStatus.OK );
	}
	
}

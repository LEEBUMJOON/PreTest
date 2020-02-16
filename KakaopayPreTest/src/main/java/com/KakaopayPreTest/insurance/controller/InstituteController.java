package com.KakaopayPreTest.insurance.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KakaopayPreTest.insurance.response.dto.InstituteListResponseDto;
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
	
}

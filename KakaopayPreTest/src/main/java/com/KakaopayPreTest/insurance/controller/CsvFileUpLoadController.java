package com.KakaopayPreTest.insurance.controller;

import org.springframework.web.bind.annotation.RestController;

import com.KakaopayPreTest.insurance.service.CsvFileUpLoadService;

@RestController
public class CsvFileUpLoadController {
	
	private final CsvFileUpLoadService csvFileUploadService;

	public CsvFileUpLoadController(CsvFileUpLoadService csvFileUploadService) {
	        this.csvFileUploadService = csvFileUploadService;
	    }
	
	


}

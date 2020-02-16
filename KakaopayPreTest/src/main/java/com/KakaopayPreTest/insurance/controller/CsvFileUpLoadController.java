package com.KakaopayPreTest.insurance.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.KakaopayPreTest.insurance.response.dto.FileUpLoadResponseDto;
import com.KakaopayPreTest.insurance.service.CsvFileUpLoadService;

@RestController
public class CsvFileUpLoadController {
	
	private final CsvFileUpLoadService csvFileUploadService;

	public CsvFileUpLoadController(CsvFileUpLoadService csvFileUploadService) {
	        this.csvFileUploadService = csvFileUploadService;
	    }
	
	
    @PostMapping("/file/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
    	FileUpLoadResponseDto fileUpLoadResponseDto = csvFileUploadService.upLoad(multipartFile);
        return new ResponseEntity<>(fileUpLoadResponseDto, HttpStatus.OK);
    }


}

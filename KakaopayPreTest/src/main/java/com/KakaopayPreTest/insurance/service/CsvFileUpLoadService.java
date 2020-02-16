package com.KakaopayPreTest.insurance.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.KakaopayPreTest.insurance.util.CsvReader;

@Service
public class CsvFileUpLoadService {
	
	@Transactional
	public List<String[]> upLoad(MultipartFile file ) throws IOException{
		List<String[]> csvData = readCsvFile(file); 
		
		
		return csvData;
	}
	
	
	private List<String[]> readCsvFile(MultipartFile file) throws IOException{
		CsvReader csvReader = new CsvReader();
		List<String[]> result = new ArrayList<>();
		result = csvReader.fileRead(file.getInputStream());		
		return result;
	}

}

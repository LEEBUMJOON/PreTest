package com.KakaopayPreTest.insurance.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.KakaopayPreTest.insurance.domain.Institute;
import com.KakaopayPreTest.insurance.domain.InstituteInfo;
import com.KakaopayPreTest.insurance.repository.InstituteRepository;
import com.KakaopayPreTest.insurance.util.ConstantsVariable;
import com.KakaopayPreTest.insurance.util.CsvReader;

import antlr.StringUtils;

@Service
public class CsvFileUpLoadService {
	
	
	private InstituteService instituteService;

	
	public CsvFileUpLoadService(InstituteService instituteService) {		
		this.instituteService = instituteService;
	}


	@Transactional
	public List<String[]> upLoad(MultipartFile file ) throws IOException{
		List<String[]> csvData = readCsvFile(file); 
		List<String> headLine = getHeadLine(csvData);
		
		saveInstitute(headLine);
		
		return csvData;
	}
	
	
	private List<String[]> readCsvFile(MultipartFile file) throws IOException{
		CsvReader csvReader = new CsvReader();
		List<String[]> result = new ArrayList<>();
		result = csvReader.fileRead(file.getInputStream());		
		return result;
	}
	
	private List<String> getHeadLine(List<String[]> csvData) { 
		List<String> header = new ArrayList<>();
		
		String[] headerArray = csvData.get(ConstantsVariable.HEAD_LINE_IDX);
		
		for(int i = ConstantsVariable.HEAD_START_IDX ; i < headerArray.length ; i++) {
			if (headerArray[i]!= null  ||  headerArray[i] != " ") {
			header.add(headerArray[i]);
			}
		}
		
		return header;
	}
	
	private void saveInstitute(List<String> instituteList) {		
		for (String institute : instituteList) {
			String instituteName = InstituteInfo.covertName(institute);
			String instituteCode = InstituteInfo.covertNameToCode(institute);
			instituteService.save(instituteName, instituteCode);
		}
		
	}

}

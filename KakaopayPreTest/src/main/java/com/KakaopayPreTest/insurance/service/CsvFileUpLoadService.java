package com.KakaopayPreTest.insurance.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.KakaopayPreTest.insurance.domain.Institute;
import com.KakaopayPreTest.insurance.domain.InstituteInfo;
import com.KakaopayPreTest.insurance.util.ConstantsVariable;
import com.KakaopayPreTest.insurance.util.CsvReader;
import com.KakaopayPreTest.insurance.util.TestUtil;

@Service
public class CsvFileUpLoadService {
	
	
	private InstituteService instituteService;

	
	public CsvFileUpLoadService(InstituteService instituteService) {		
		this.instituteService = instituteService;
	}


	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@Transactional
	public List<String[]> upLoad(MultipartFile file ) throws IOException{
		List<String[]> csvData = readCsvFile(file); 
		List<String> headLine = getHeadLine(csvData);		
		List<List<String>> dataLine = getAmountData(csvData);
		
		saveInstitute(headLine);
		saveInstituteAmount(dataLine);
		
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

		for (int i = ConstantsVariable.HEAD_START_IDX; i < headerArray.length; i++) {

			if (StringUtils.isNotEmpty(StringUtils.trim(headerArray[i]))) {
				header.add(headerArray[i]);
			}
		}

		return header;
	}
	
	/**
	 * @param instituteList
	 */
	private void saveInstitute(List<String> instituteList) {		
		for (String institute : instituteList) {
			String instituteName = InstituteInfo.covertName(institute);
			String instituteCode = InstituteInfo.covertNameToCode(institute);
			instituteService.save(instituteName, instituteCode);
		}
		
	}
	
	
	private List<List<String>> getAmountData(List<String[]> csvData) {
		List<List<String>> amountData = new ArrayList<>(new ArrayList<>());
		List<String> dataRowList = null;
		for (String[] dataRow : csvData.subList(ConstantsVariable.DATA_LINE_IDX, csvData.size())) {
			if (dataRow.length == 0) {
				continue;
			}
			int startIdx = TestUtil.getArrayValStrartIdx(dataRow);
			int endIdx = TestUtil.getArrayValEndIdx(dataRow);
			
			dataRowList = new ArrayList<>(Arrays.asList(dataRow).subList(startIdx, endIdx+1));
			if (dataRowList.size() != 0) {
				amountData.add(dataRowList);
			}
		}
		return amountData;
	}
	
	private void saveInstituteAmount(List<List <String> > amountData) {
		for(List<String> rowData : amountData) {			
			String year = rowData.get(ConstantsVariable.YEAR_IDX);
			String  month = rowData.get(ConstantsVariable.MONTH_IDX);
			
			for(int i = ConstantsVariable.HEAD_START_IDX ; i < rowData.size() ; i++) {		
				
				int amount =Integer.parseInt(rowData.get(i).replaceAll(",", ""));
				Institute institute = instituteService.getInstitute((long)i-1);
				instituteService.amountSave(year, month, institute, amount);				
			}
										
		}		
	}

}

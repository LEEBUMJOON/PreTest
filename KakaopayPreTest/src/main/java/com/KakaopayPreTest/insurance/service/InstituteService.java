package com.KakaopayPreTest.insurance.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KakaopayPreTest.insurance.domain.Amount;
import com.KakaopayPreTest.insurance.domain.Institute;
import com.KakaopayPreTest.insurance.repository.InstituteAmountRepository;
import com.KakaopayPreTest.insurance.repository.InstituteRepository;
import com.KakaopayPreTest.insurance.response.dto.InstituteAmountMaxResponseDto;
import com.KakaopayPreTest.insurance.response.dto.InstituteListResponseDto;
import com.KakaopayPreTest.insurance.response.dto.IntituteDetailTotalAmountDto;
import com.KakaopayPreTest.insurance.response.dto.IntituteTotalYearDto;

@Service
public class InstituteService {
	
	
	private final InstituteRepository instituteRepository;
	
	private final InstituteAmountRepository instituteAmountRepository;
	
	
	public InstituteService( InstituteRepository instituteRepository, InstituteAmountRepository instituteAmountRepository) {	
		this.instituteRepository = instituteRepository;
		this.instituteAmountRepository = instituteAmountRepository;
	}

	@Transactional()
	public Institute save(String instituteName, String instituteCode  ) { 
		Institute institute =  new Institute(instituteName, instituteCode);	
		return  instituteRepository.save(institute);
	}
	
	@Transactional(readOnly = true)
	public Institute getInstitute(Long id) { 
		Institute institute = new Institute();
		institute =  instituteRepository.findById(id).orElse(null);
		return institute;
	}
	
	@Transactional()
	public Amount amountSave(String year, String month, Institute institute, int amount) {
		Amount amountData = new Amount(institute,  year, month,  amount);		 
		return instituteAmountRepository.save(amountData);
	}
	
	@Transactional(readOnly =  true)
	public List<InstituteListResponseDto> getInstitueList() {
		List<InstituteListResponseDto> result = new ArrayList<InstituteListResponseDto>();
		InstituteListResponseDto instituteListResponseDto = new InstituteListResponseDto();
		List<Institute> insituteList = instituteRepository.findAll();
		for (Institute   institute : insituteList ) {
			instituteListResponseDto.setInstituteCode(institute.getCode());
			instituteListResponseDto.setInstituteName(institute.getName());
			result.add(instituteListResponseDto);
		}
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public List<IntituteTotalYearDto> getAmoutTotal() {		
		List<Object[]> instituteTotalYear = instituteAmountRepository.getAmountTotalYear();		
		
		List<Object[]>instituteDetailTotalList = instituteAmountRepository.getDetailAmount();
		
		HashMap<String,IntituteDetailTotalAmountDto> retultMap= buildMapIntituteDetailTotalAmount(instituteDetailTotalList);
		
		List<IntituteTotalYearDto> InstituteTotalYearDtoList = bulidIntituteTotalYearDto(instituteTotalYear, retultMap);

		
		return InstituteTotalYearDtoList;
	}
	
	private List<IntituteTotalYearDto> bulidIntituteTotalYearDto (List<Object[]> instituteTotalYear , HashMap<String,IntituteDetailTotalAmountDto> parmMap){
		List<IntituteTotalYearDto> InstituteTotalYearDtoList = new ArrayList<>();
		for(Object[] obj : instituteTotalYear) {
			
			IntituteTotalYearDto intituteTotalYearDto = new IntituteTotalYearDto();
			intituteTotalYearDto.setYear(obj[0].toString());
			
			intituteTotalYearDto.setAmount(Integer.parseInt(obj[1].toString()));
			intituteTotalYearDto.getIntituteDetailTotalAmountDtoList().add( parmMap.get(intituteTotalYearDto.getYear()) );
			
			InstituteTotalYearDtoList.add(intituteTotalYearDto);			
			
		}				
		return  InstituteTotalYearDtoList;
	}
 /**
		DTO LIST - > MAP으로  변환		 
		*/ 
	private HashMap<String, IntituteDetailTotalAmountDto> buildMapIntituteDetailTotalAmount( List<Object[]> instituteDetailTotalList ){				
		HashMap<String,  IntituteDetailTotalAmountDto>  resultMap = new HashMap<>();
		for(Object[] obj : instituteDetailTotalList) {
			String strKey = String.format("%s" ,  obj[0].toString());  //년도 key 
			
			IntituteDetailTotalAmountDto intituteDetailTotalAmountDto = new IntituteDetailTotalAmountDto();
			
			intituteDetailTotalAmountDto.setName(obj[1].toString()); //기관명
			intituteDetailTotalAmountDto.setAmount(Integer.parseInt(obj[2].toString())); // 금액 
			
			 resultMap.put(strKey , intituteDetailTotalAmountDto );			
		}
		
		return resultMap;
		
	}
	
	@Transactional(readOnly = true)
	public InstituteAmountMaxResponseDto getInstituteAmountMax() {
		InstituteAmountMaxResponseDto instituteAmountMaxResponseDto = new InstituteAmountMaxResponseDto();
		
		List<Object[]>instituteDetailTotalList = instituteAmountRepository.getDetailAmount();
		
		Object[] obj = instituteDetailTotalList.get(0);
		
		instituteAmountMaxResponseDto.setYear(obj[0].toString());
		instituteAmountMaxResponseDto.setBank(obj[1].toString());
			
		
		return instituteAmountMaxResponseDto;
		
	}

}

package com.KakaopayPreTest.insurance.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KakaopayPreTest.insurance.domain.Amount;
import com.KakaopayPreTest.insurance.domain.Institute;
import com.KakaopayPreTest.insurance.repository.InstituteAmountRepository;
import com.KakaopayPreTest.insurance.repository.InstituteRepository;
import com.KakaopayPreTest.insurance.response.dto.InstituteListResponseDto;

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

}

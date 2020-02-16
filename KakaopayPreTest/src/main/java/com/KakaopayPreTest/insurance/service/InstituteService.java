package com.KakaopayPreTest.insurance.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.KakaopayPreTest.insurance.domain.Amount;
import com.KakaopayPreTest.insurance.domain.Institute;
import com.KakaopayPreTest.insurance.repository.InstituteAmountRepository;
import com.KakaopayPreTest.insurance.repository.InstituteRepository;

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

}

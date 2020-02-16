package com.KakaopayPreTest.insurance.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.KakaopayPreTest.insurance.domain.Institute;
import com.KakaopayPreTest.insurance.repository.InstituteRepository;

@Service
public class InstituteService {
	
	
	private final InstituteRepository instituteRepository;
	
	
	public InstituteService(final InstituteRepository instituteRepository) {	
		this.instituteRepository = instituteRepository;
	}

	@Transactional()
	public Institute save(String instituteName, String instituteCode  ) { 
		Institute institute =  new Institute();
		institute.setName(instituteName);
		institute.setCode(instituteCode);
		return  instituteRepository.save(institute);
	}

}

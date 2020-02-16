package com.KakaopayPreTest.insurance.CsvFileLoadTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.KakaopayPreTest.insurance.domain.Amount;
import com.KakaopayPreTest.insurance.domain.Institute;
import com.KakaopayPreTest.insurance.repository.InstituteAmountRepository;
import com.KakaopayPreTest.insurance.repository.InstituteRepository;
import com.KakaopayPreTest.insurance.service.CsvFileUpLoadService;
import com.KakaopayPreTest.insurance.service.InstituteService;

@SpringBootTest
class CsvFileUpLoadServiceTest {

	@Autowired
	 InstituteRepository instituteRepository;
	
	@Autowired
	InstituteAmountRepository instituteAmountRepository; 
	
	 CsvFileUpLoadService csvService;
	
	  InstituteService instituteService;
	
	
	@BeforeEach
	void setUp() throws Exception {
		instituteService = new InstituteService( instituteRepository , instituteAmountRepository);
		csvService =  new CsvFileUpLoadService(instituteService);		
	}

	@Test
	void upLoadtest() throws IOException {
//		fail("Not yet implemented");		
		Path path = Paths.get("C:\\DevWork\\git\\repository\\KakaopayPreTest\\src\\main\\resources\\사전과제3.csv");

		String name = "사전과제3";
		String originalFileName = "사전과제3.csv";
		String contentType = "text/csv";
		byte[] content = null;
		try {
		    content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		MultipartFile result = new MockMultipartFile(name,
		                     originalFileName, contentType, content);
			
		
		List<String[]> csvData = csvService.upLoad(result);
		Institute test  = new Institute();
//		System.out.println("---------------------------------");
//		System.out.println( instituteRepository.findById(1L));
//		System.out.println("---------------------------------");
//		Amount amount = instituteAmountRepository.findById(140L).orElse(null);
//		System.out.println( instituteAmountRepository.findAll().size());
//		System.out.println(amount.getAmount());
		
//		test = instituteRepository.findById(1L).orElse(test);
//		System.out.println(test.getCode());
//		System.out.println(test.getName());

	}
	
	@Test
   void UitlsTest() { 
		System.out.println(StringUtils.isNotEmpty(StringUtils.trim(" ")));
	}

}

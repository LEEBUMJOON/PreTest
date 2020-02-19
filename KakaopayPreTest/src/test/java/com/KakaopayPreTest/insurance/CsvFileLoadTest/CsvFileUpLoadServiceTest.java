package com.KakaopayPreTest.insurance.CsvFileLoadTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.KakaopayPreTest.insurance.repository.InstituteAmountRepository;
import com.KakaopayPreTest.insurance.repository.InstituteRepository;
import com.KakaopayPreTest.insurance.response.dto.FileUpLoadResponseDto;
import com.KakaopayPreTest.insurance.service.CsvFileUpLoadService;
import com.KakaopayPreTest.insurance.service.InstituteService;

@SpringBootTest
class CsvFileUpLoadServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(CsvFileUpLoadServiceTest.class);

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
			
		
		FileUpLoadResponseDto fileUpLoadResponseDto =csvService.upLoad(result);		
		log.debug("----------------Strart response-------------------------");
		log.debug( fileUpLoadResponseDto.getFileName());
		log.debug(fileUpLoadResponseDto.getFileContentType() );
		log.debug(String.valueOf(fileUpLoadResponseDto.getFileSize() ));
		

	}
	

}

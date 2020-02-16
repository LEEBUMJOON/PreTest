package com.KakaopayPreTest.insurance.CsvFileLoadTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class CsvFileUpLoadService {
//	CsvFileUpLoadService csvService =  new CsvFileUpLoadService();
	
	@Test
	void CsvFileReadTest() {
		//fail("Not yet implemented");
//		C:\DevWork\git\repository\KakaopayPreTest\src\main\resources\사전과제3.csv
		
		
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
		
		
		
		System.out.println(result.getSize());
		
	}

}

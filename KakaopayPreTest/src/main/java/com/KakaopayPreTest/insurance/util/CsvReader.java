package com.KakaopayPreTest.insurance.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvReader {
	
	private String charset = ConstantsVariable.DEFAULT_CHAR_SET; //UTF-8
	
	public  List<String[]> fileRead(InputStream stream) throws IOException, IOException {
        List<String[]> results = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(stream , charset))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                results.add(nextLine);
            }
        }

        return results;
		
	}
	
	public  List<String[]> fileRead(InputStream stream , String charSet) throws IOException, IOException {
        List<String[]> results = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(stream , charSet))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                results.add(nextLine);
            }
        }

        return results;
		
	}

}

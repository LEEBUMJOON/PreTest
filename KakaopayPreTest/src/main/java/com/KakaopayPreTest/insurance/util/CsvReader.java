package com.KakaopayPreTest.insurance.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvReader {
	
	public  List<String[]> fileRead(InputStream stream) throws IOException, IOException {
        List<String[]> results = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(stream , "UTF-8"))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                results.add(nextLine);
            }
        }

        return results;
		
	}

}

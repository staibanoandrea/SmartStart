package it.andrea.smartstart;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvReader {
	private static final String POSIZIONE_IN = "Ingresso in una posizione";
	private static final String POSIZIONE_OUT = "Uscita da una posizione";

	public static List<CsvRequest> translateFile(File csvFile) {
		List<CsvRequest> requestList = new ArrayList<CsvRequest>();
		
		int i = 1;
		try {
			for (String[] request : getFileData(csvFile)) {
				requestList.add(new CsvRequest(i, request[1], request[3]));
			}
			i++;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return requestList;
	}

	private static List<String[]> getFileData(File csvFile) {
		final int typeIndex = 2;
		List<String[]> untranslatedList = new ArrayList<String[]>();
		try {
			CSVReader reader = new CSVReader(new FileReader(csvFile));

			while (reader.peek() != null) {
				while (!reader.peek()[typeIndex].equals(POSIZIONE_IN)
						&& !reader.peek()[typeIndex].equals(POSIZIONE_OUT)) {
					reader.skip(1);
				}
				untranslatedList.add(reader.readNext());
			}
			reader.close();
		} catch (CsvValidationException e) {
			System.out.println("An error occurred: CsvValidationException.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An error occurred: IOException.");
			e.printStackTrace();
		}
		return untranslatedList;
	}
}

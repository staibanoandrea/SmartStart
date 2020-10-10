package it.andrea.smartstart;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvReader {
	private static final String POSIZIONE_IN = "Ingresso in una posizione";
	private static final String POSIZIONE_OUT = "Uscita da una posizione";

	public static List<Request> translateFile(File csvFile) {
		List<Request> requestList = new ArrayList<Request>();
		int i = 1;

		for (String[] request : getFileData(csvFile)) {
			requestList.add(new Request(i, requestTime, xPos, yPos));

			i++;
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
			System.out.println("An error occurred: file not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An error occurred: file not found.");
			e.printStackTrace();
		}
		return untranslatedList;
	}
}

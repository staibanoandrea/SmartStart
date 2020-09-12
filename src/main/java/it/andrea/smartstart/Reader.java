package it.andrea.smartstart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
	public static List<Request> translateFile(File file) {
		List<Request> requestList = new ArrayList<Request>();
		try {
			Scanner myReader = new Scanner(file);
			// skip the first line;
			myReader.nextLine();
			while (myReader.hasNextLine()) {
				// the desired line is (without backspaces): index \t xPos \t yPos \t requestTime \n
				String data = myReader.nextLine();
				String[] tokenizedData = data.split("\t");
				Request r = new Request(Integer.parseInt(tokenizedData[0]), Integer.parseInt(tokenizedData[1]),
						Integer.parseInt(tokenizedData[2]), Integer.parseInt(tokenizedData[3]));
				requestList.add(r);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return requestList;
	}
}

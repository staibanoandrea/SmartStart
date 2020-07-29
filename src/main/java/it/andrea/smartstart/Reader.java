package it.andrea.smartstart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {
	private Reader() {
		// can't be instantiated
	}

	public static List<Request> getRequests() {
		List<Request> requestList = new ArrayList<Request>();
		try {
			File myObj = new File("istanze.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				// linea prevista (senza spazi): index \t xPos \t yPos \t requestTime \n
				String data = myReader.nextLine();
				// TODO crea una lista di richieste?
				String[] tokenizedData = data.split("\t");
				Request r = new Request(Integer.parseInt(tokenizedData[0]), Integer.parseInt(tokenizedData[1]),
						Integer.parseInt(tokenizedData[2]), Double.parseDouble(tokenizedData[3]));
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

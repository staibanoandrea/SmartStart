package it.andrea.smartstart;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextReader {
	public static List<Request> translateFile(File timeFile, File coordFile) {
		List<Request> requestList = new ArrayList<Request>();
		try {
			Scanner timeReader = new Scanner(timeFile);
			Scanner coordReader = new Scanner(coordFile);

			// the first line of coordFile contains the origin coordinates:
			String originData = coordReader.nextLine();
			String[] tokenizedOriginData = originData.split(" ");
			Request origin = new Request(0, null, Double.parseDouble(tokenizedOriginData[0]),
					Double.parseDouble(tokenizedOriginData[1]));
			requestList.add(origin);

			while (timeReader.hasNextLine()) { // now both timeReader and coordReader move at the same speed
				// timeReader line: index \t time
				// coordReader line: xPos yPos
				String timeData = timeReader.nextLine();
				String[] tokenizedTimeData = timeData.split("\t");
				String coordData = coordReader.nextLine();
				String[] tokenizedCoordData = coordData.split(" ");
				Request r = new Request(Integer.parseInt(tokenizedTimeData[0]),
						Double.parseDouble(tokenizedTimeData[1]), Double.parseDouble(tokenizedCoordData[0]),
						Double.parseDouble(tokenizedCoordData[1]));
				requestList.add(r);
				
			}
			timeReader.close();
			coordReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred: file not found.");
			e.printStackTrace();
		}
		return requestList;
	}
}

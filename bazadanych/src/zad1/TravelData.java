package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
 * List<String> travelData.getOffersDescriptionsList(String loc, String dateFormat)
 * która zwraca listę napisów, każdy z których jest opisem jednej oferty z plików 
 * katalogu data, przedstawionym zgodnie z regułami i w języku lokalizacji loc i 
 * przy podanym formacie daty (możliwe formaty określa klasa SimpleDateFormat).
 */

public class TravelData {

	private List<TravelDataEntry> travelDataList = new ArrayList<>();

	public TravelData(File dataDir) {
		for (final File fileEntry : dataDir.listFiles()) {
			if (!fileEntry.isDirectory()) {
				Path filePath = fileEntry.toPath();
				try {
					for (String row : Files.readAllLines(filePath)) {
						System.out.println("Parsing row: " + row);
						String[] rowAsTab = row.split("\t");
						TravelDataEntry tDE = new TravelDataEntry(rowAsTab);
						travelDataList.add(tDE);
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			}
		}

	}

	// List<String> travelData.getOffersDescriptionsList(String loc, String
	// dateFormat);

	public List<String> getOffersDescriptionsList(String loc, String dateFormat) {
		List<String> offersDescriptionsList = new ArrayList<>();
		for (TravelDataEntry tDE : travelDataList) {
			offersDescriptionsList.add(tDE.toString(loc, dateFormat));
		}
		return offersDescriptionsList;
	}

}

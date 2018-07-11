package edu.handong.csee.isel.patch;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.supercsv.cellprocessor.FmtBool;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;
import java.util.ArrayList;

public class MakingCSVExample {

	public static void main(String[] args) throws IOException {
		// File exampleFile = new File("/Users/imseongbin/Desktop/ExampleOfCsv.csv");

		final String[] header = new String[] { "ShortMessage", "CommitHash", "Patch", "Date", "Author"};

		int i;
		ArrayList<Map<String, Object>> commits = new ArrayList<Map<String, Object>>();
		for (i = 0; i < 2; i++) {
			Map<String, Object> temp = new HashMap<String, Object>();
			
			
			//ArrayList, String 5 끝!.
			if (i == 0) {
				temp.put(header[0], "GGGGGcommit 7323");
				temp.put(header[1], "temp");
				temp.put(header[2], "Dunbar");
				temp.put(header[3], "");
				temp.put(header[4], "1600 Amphitheatre Parkway\nMountain View, CA 94043\nUnited States");
			}
			
			if(i == 1) {
				temp.put(header[0], "2");
				temp.put(header[1], "temp");
				temp.put(header[2], "Down");
				temp.put(header[3], "");
				temp.put(header[4], "1601 Willow Rd.\nMenlo Park, CA 94025\nUnited States");
			}
			commits.add(temp);
		}
		

		// create the customer Maps (using the header elements for the column keys)
//		final Map<String, Object> john = new HashMap<String, Object>();
//		john.put(header[0], "GGGGGcommit 7323");
//		john.put(header[1], "John");
//		john.put(header[2], "Dunbar");
//		john.put(header[3], new GregorianCalendar(1945, Calendar.JUNE, 13).getTime());
//		john.put(header[4], "1600 Amphitheatre Parkway\nMountain View, CA 94043\nUnited States");
//
//		final Map<String, Object> bob = new HashMap<String, Object>();
//		bob.put(header[0], "2");
//		bob.put(header[1], "Bob");
//		bob.put(header[2], "Down");
//		bob.put(header[3], new GregorianCalendar(1919, Calendar.FEBRUARY, 25).getTime());
//		bob.put(header[4], "1601 Willow Rd.\nMenlo Park, CA 94025\nUnited States");

		ICsvMapWriter mapWriter = null;
		try {
			mapWriter = new CsvMapWriter(new FileWriter("/Users/imseongbin/Desktop/ExampleOfCsv.csv"),
					CsvPreference.STANDARD_PREFERENCE);

			final CellProcessor[] processors = MakingCSVExample.getProcessors();

			// write the header
			mapWriter.writeHeader(header);

			// write the customer maps
			for(Map<String, Object> person : commits) {
				mapWriter.write(person, header, processors);
			}
			
//			mapWriter.write(john, header, processors);
//			mapWriter.write(bob, header, processors);

		} finally {
			if (mapWriter != null) {
				mapWriter.close();
			}
		}
		System.out.println("Success!");

	}

	private static CellProcessor[] getProcessors() {

		CellProcessor[] processors = new CellProcessor[] { new UniqueHashCode(), // customerNo (must be unique)
				new NotNull(), // firstName
				new NotNull(), // lastName
				new NotNull(), // mailingAddress
				new NotNull(), // favouriteQuote
		};

		return processors;
	}
}
package commandline;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to create and append data to a log file.
 * Class is final to prevent any other classes from extending the functionality,
 * and the constructor is set to private to stop any classes creating any instance of this class.
 */
final public class OutputLogger {
	
	/**
	 * Private constructor to prevent object being instantiated
	 */
	private OutputLogger() { }
	
	/**
	 * Method to create a new log file if one does not already exist. 
	 * Otherwise it will clear the existing file.
	 * 
	 * To be called when starting a new game, before starting any logging of data.
	 */
	public static void createLogFile() {
		// Define log file name and location (in main project folder)
		File logfile = new File("logfile.txt");

		// If a file already exists, delete it so it can be replaced.
		if (logfile.exists()) {
			logfile.delete();
		}

		// Create a new file empty file by the name of "logfile.txt"
		try {
			logfile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Add a heading line to the file with date and time information for when game was started.
		addToLog(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME)+"\n");
	}
	
	/**
	 * Method to add a line of text to the log file.
	 * @param logEntry String of text to be appended to the end of the log file.
	 */
	public static void addToLog(String logEntry) {
		try {
			// Create new FileWriter object, passing a second argument to append text rather than overwrite.
			FileWriter fw = new FileWriter("logfile.txt", true);
			
			// Add string to the log file, end with a newline character then close the writer.
			fw.write(logEntry);
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a section split in the log file.
	 * To be called during the game when a round is complete or relevant information is logged.
	 */
	public static void addLogSectionSplit() {
		addToLog("\n-----------------------------------------------------------------------------");
	}

}
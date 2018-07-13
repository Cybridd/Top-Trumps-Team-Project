package commandline;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *  Class to handle interaction with the database which will store statistics on the game.  
 *	Methods to connect and close the connection, to enter and view data, and to return relevant variables. 
 *	Includes all SQL statements used in the program. 
 *
 */

public class DatabaseInteraction {
	// Variables
	private Connection connection = null; 
	private Statement statement = null; 
	private String dbname;	
	private String username;
	private String password;
	private String totalGameNumber, computerWins, humanWins, avgDraws, maxRounds; // Variables to store output of statistics
	private double avgDrawsNumber; // This double is used to get a more accurate average 
	
	/**
	 *  Constructor for our database class.
	 *  Assigns the details for the database we are using to the relevant variable.  
	 * 
	 */
	public DatabaseInteraction(){
		dbname = "m_17_2354562e";
		username = "m_17_2354562e";
		password = "2354562e";		
	}
	
	/**
	 *  Method to establish connection with the database
	 *  
	 */
	public void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + dbname, username, password);
			}
		catch (SQLException e) {
			System.err.println("Connection Failed!"); // Catches SQL exception and prints it to the console. 
			e.printStackTrace();
			return;
			}
		if (connection != null) {
			//System.out.println("Connection successful"); // Prints to the console if the connection is successful. 
		}
		else {
			System.err.println("Failed to make connection!"); // Tells us the connection has failed if it has not been caught be the catch statement. 
		}
	}
	
	/**
	 *  Method to close the connection to the database once an action has been performed. 
	 * 
	 */
	public void closeConnection() {
		try {
			connection.close();
			//System.out.println("Connection closed");
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Connection could not be closed - SQLexception"); // Error printed to the console if the connection could not be closed.
			}
	}
	
	/**
	 *  Method for inputting data into the database. 
	 *  The inputed data is taken from the following parameters:  
	 * @param gameNumber
	 * @param draws
	 * @param winner
	 * @param totalRounds
	 * @param roundHuman
	 * @param roundAi1
	 * @param roundAi2
	 * @param roundAi3
	 * @param roundAi4
	 * 
	 */
	public void enterData(int gameNumber, int draws, String winner, int totalRounds, int roundHuman, int roundAi1, int roundAi2, int roundAi3, int roundAi4) {
		connect(); // Connects to the database.
		// SQL query stored in a string.
		String query = String.format("INSERT INTO team.toptrumpgame VALUES ('%d', '%d', '%s', '%d', '%d', '%d', '%d', '%d', '%d');", gameNumber, draws, winner, totalRounds, roundHuman, roundAi1, roundAi2, roundAi3, roundAi4);
		
		try { // Try/catch to query database.
			statement = connection.createStatement();
			statement.executeUpdate(query);
		}
		catch  (SQLException e) {
			e.printStackTrace();
			System.err.println("Error executing update : " + query); // Prints to the console the SQL statement which failed.
		}
		closeConnection(); // Closes the connection.
	}

	/**
	 *  Method for getting the output from the database.
	 *  Calling this method assigns the required output to individual variables. 
	 * 
	 */

	
	public void viewData() {
		// SQL queries we will use for viewing data.
		String totalGameNumberSQL = "SELECT COUNT(number) FROM team.toptrumpgame";
		String computerWinsSQL = "SELECT COUNT (*) FROM team.toptrumpgame WHERE winner IN ('AI1', 'AI2', 'AI3', 'AI4')";
		String humanWinsSQL = "SELECT COUNT (*) FROM team.toptrumpgame WHERE winner NOT IN ('AI1', 'AI2', 'AI3', 'AI4')";
		String avgDrawsSQL = "SELECT AVG(drawsnumber) FROM team.toptrumpgame";
		String maxRoundsSQL = "SELECT MAX(totalrounds) FROM team.toptrumpgame";
		
		
		connect(); // Connects to the database.
		
		// Gets the total number of games.
		try {
			statement = connection.createStatement();
			ResultSet rs1 = statement.executeQuery(totalGameNumberSQL);
			while (rs1.next()) {
				totalGameNumber = rs1.getString("count");
				//System.out.println(totalGameNumber);
			}
		}
		catch(SQLException e) {
				e.printStackTrace();
				System.err.println("Query Failed!");
		}
		
		
		// Gets the total number of AI wins.
		try {
			statement = connection.createStatement();
			ResultSet rs2 = statement.executeQuery(computerWinsSQL);
			while (rs2.next()) {
				computerWins = rs2.getString("count");
				//System.out.println(computerWins);
			}
		}
		catch(SQLException e) {
				e.printStackTrace();
				System.err.println("Query Failed!");
		}
		
		// Gets the total number of Human player wins.
		try {
			statement = connection.createStatement();
			ResultSet rs3 = statement.executeQuery(humanWinsSQL);
			while (rs3.next()) {
				humanWins = rs3.getString("count");
				//System.out.println(humanWins);
			}
		}
		catch(SQLException e) {
				e.printStackTrace();
				System.err.println("Query Failed!");
		}
		
		// Gets the average number of draws
		try {
			statement = connection.createStatement();
			ResultSet rs4 = statement.executeQuery(avgDrawsSQL);
			while (rs4.next()) {
				avgDrawsNumber = rs4.getDouble("avg");
				avgDraws = String.format("%6.2f", avgDrawsNumber);
				//System.out.println(avgDraws);
			}
		}
		catch(SQLException e) {
				e.printStackTrace();
				System.err.println("Query Failed!");
		}
		
		// Gets the maximum number of rounds
		try {
			statement = connection.createStatement();
			ResultSet rs5 = statement.executeQuery(maxRoundsSQL);
			while (rs5.next()) {
				maxRounds = rs5.getString("max");
				//System.out.println(maxRounds);
			}
		 
		}
		catch(SQLException e) {
				e.printStackTrace();
		}
		
		//printString();
		closeConnection(); // Closes the connection to the database
	}
	
	
	/**
	 *  Accessor methods to return the values from the database. 
	 * 
	 * @return
	 */
	
	public String getTotalGameNumber(){
		return totalGameNumber;
	}
	
	public String getComputerWins() {
		return computerWins;
	}
	
	public String getHumanWins() {
		return humanWins;
	}
	
	public String getAvgDraws() {
		return avgDraws;
	}
	
	public String getMaxRounds() {
		return maxRounds;
	}
	
	/**
	 *  Method which prints all the values asked for in the specification.
	 * 
	 */
	public void printString() {
		System.out.print(String.format("Total Game Numbers: %-5s\nComputer Wins: %-5s\nHuman Wins: %-5s\nAverage Draws: %-5s\nMax Number of Rounds: %-5s\n", getTotalGameNumber(), getComputerWins(), getHumanWins(), getAvgDraws(), getMaxRounds()));
	}
	
	/**
	 *  Method to return the string as formatted in the printString method
	 *  Used to return it to the Rest API
	 * 
	 */
	public String returnStats() {
		String stats = (String.format("%5s%5s%5s%5s%5s", getTotalGameNumber(), getComputerWins(), getHumanWins(), getAvgDraws(), getMaxRounds()));
		return stats;
	}
}


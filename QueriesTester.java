import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class QueriesTester {
	public static void main(String[] args) throws SQLException {
		String inputFile = "queriesInput.txt";
		String actualOutputFile = "queriesActualOutput.txt";
		String expectedOutputFile = "queriesExpectedOutput.txt";

		Queries.executeQueriesFromFile(inputFile, actualOutputFile);
		try {
			BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
			BufferedReader actualOutputReader = new BufferedReader(new FileReader(actualOutputFile));
			BufferedReader expectedOutputReader = new BufferedReader(new FileReader(expectedOutputFile));

			String query, actualOutput, expectedOutput;
			int queryNumber = 1;

			while ((query = inputReader.readLine()) != null && (actualOutput = actualOutputReader.readLine()) != null
					&& (expectedOutput = expectedOutputReader.readLine()) != null) {
				System.out.print("Query:" + queryNumber + " :- " + query);
				if (actualOutput.equals(expectedOutput)) {
					System.out.println(" \t- Vedict: PASS");
				} else {
					System.out.println(" \t- Vedict: FAIL");
				}
				queryNumber++;
			}
			inputReader.close();
			actualOutputReader.close();
			expectedOutputReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

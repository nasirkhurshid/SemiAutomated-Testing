import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SortingTester {
	public static void main(String[] args) {
		String input = "sequenceInput.txt";
		String output = "expectedOutput.txt";
		String log = "log.txt";

		try (BufferedReader i = new BufferedReader(new FileReader(input));
				BufferedReader e = new BufferedReader(new FileReader(output));
				FileWriter logWriter = new FileWriter(log)) {

			String inputLine, outputLine;
			int lineNumber = 0;

			while ((inputLine = i.readLine()) != null && (outputLine = e.readLine()) != null) {
				String expected = outputLine.trim();
				String observed = Sorting.sortSequence(inputLine.trim());
				String verdict = "FAIL";
				if (observed.equals(expected))
					verdict = "PASS";

				String logLine = "Test Case No : " + ++lineNumber + "\nExpected Output : " + expected
						+ "\nObserved Output : " + observed + "\nVerdict : " + verdict + "\n----------\n";

				logWriter.write(logLine);
				System.out.print(logLine);
			}
			System.out.println("Test log is written to file: \"log.txt\"");
		} catch (IOException e) {
			System.out.println("Error reading or writing file: " + e.getMessage());
			System.exit(1);
		}
	}
}

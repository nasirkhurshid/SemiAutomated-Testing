import java.util.ArrayList;
import java.util.List;

public class Sorting {
	public static String sortSequence(String line) {
		if (line.length() < 1) {
			return "Input is empty!";
		}
		String[] parts = line.trim().split("\\s+");
		char reqChar = Character.toUpperCase(parts[0].charAt(0));
		List<Double> sequence = new ArrayList<>();

		for (int i = 1; i < parts.length; i++) {
			try {
				double number = Double.parseDouble(parts[i]);
				sequence.add(number);
			} catch (NumberFormatException e) {
				return "Invalid number format: " + parts[i];
			}
		}

		if (sequence.isEmpty()) {
			return "Empty sequence";
		} else {
			int n = sequence.size();
			// Sorting in ascending order
			if (reqChar == 'A') {
				for (int i = 1; i < n; i++) {
					Double key = sequence.get(i);
					int j = i - 1;
					while (j >= 0 && sequence.get(j) > key) {
						sequence.set(j + 1, sequence.get(j));
						j = j - 1;
					}
					sequence.set(j + 1, key);
				}
				return reqChar + ": " + sequence.toString();
			}
			// Sorting in descending order
			else if (reqChar == 'D') {
				for (int i = 1; i < n; i++) {
					Double key = sequence.get(i);
					int j = i - 1;
					while (j >= 0 && sequence.get(j) < key) {
						sequence.set(j + 1, sequence.get(j));
						j = j - 1;
					}
					sequence.set(j + 1, key);
				}
				return reqChar + ": " + sequence.toString();
			}
			// Invalid reqChar
			else {
				return "Invalid request character, must be 'A' or 'D'";
			}
		}
	}
}

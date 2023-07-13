import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.awt.datatransfer.Clipboard;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;

public class ExponentiationTester {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter base: ");
		double base = scanner.nextDouble();
		System.out.print("Enter exponent: ");
		double exponent = scanner.nextDouble();
		scanner.close();
		
		String baseString = Double.toString(base);
		String exponentString = Double.toString(exponent);

		// launching calculator
		ProcessBuilder pb = new ProcessBuilder("calc.exe");
		pb.redirectErrorStream(true);
		Process p = pb.start();

		// waiting for calculator to load
		Thread.sleep(2000);

		// creating a new Robot object for using calculator
		Robot robot = new Robot();

		// Pressing all the digits in base
		for (int i = 0; i < baseString.length(); i++) {
			char c = baseString.charAt(i);
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
			robot.keyPress(keyCode);
			robot.keyRelease(keyCode);
		}

		// Pressing '^' key
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_6);
		robot.keyRelease(KeyEvent.VK_6);
		robot.keyRelease(KeyEvent.VK_SHIFT);

		// Pressing all the digits in exponent
		for (int i = 0; i < exponentString.length(); i++) {
			char c = exponentString.charAt(i);
			int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
			robot.keyPress(keyCode);
			robot.keyRelease(keyCode);
		}

		// Pressing '=' key
		robot.keyPress(KeyEvent.VK_EQUALS);
		robot.keyRelease(KeyEvent.VK_EQUALS);

		// waiting for the result to appear
		Thread.sleep(1000);

		// Pressing 'Ctrl+C' for copying result
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		// retrieving result from the clipboard
		String result = getClipboardContents();
		System.out.println("Result: " + result);

		double expectedOutput = Double.parseDouble(result);
		// Close the calculator using 'Alt+F4'
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_F4);
		robot.keyRelease(KeyEvent.VK_F4);
		robot.keyRelease(KeyEvent.VK_ALT);
		p.destroy();

		// Getting output from the program Exponentiation.java
		double observedOutput = Exponentiation.calculateExponent(base, exponent);
		
		// Printing results and verdict
		String expression = base + "^" + exponent;
		String verdict = (Math.abs(observedOutput - expectedOutput) <= Math.pow(10, -15)) ? "PASS" : "FAIL";
		System.out.println("\nTest Expression: " + expression + "\nExpected Output: " + expectedOutput + "\nObserved Output: "
				+ observedOutput + "\nVerdict: " + verdict);
	}

	// Function to get clipboard contents
	public static String getClipboardContents() {
		String result = "";
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		if (clipboard != null && clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
			try {
				result = (String) clipboard.getData(DataFlavor.stringFlavor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}

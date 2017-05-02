package gojek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AutomatedParking {
	
	public static void main(String[] args) {
		InputParser inputParser = new InputParser();
		switch (args.length) {
		case 0:
			System.out.println("Please enter 'exit' to quit the application");
			for (;;) {
                try {
                    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                    String inputString = bufferRead.readLine();
                    if (inputString.equalsIgnoreCase("exit")) {
                        break;
                    } else {
                        // Do nothing
                    }
                } catch(IOException e) {
                    System.out.println("Encountered an error");
                    e.printStackTrace();
                }
            }
			break;

		case 1:
			inputParser.parseFileInput(args[1]);
			break;
		
		default:
			System.out.println("Invalid input provided");
			System.out.println("Please provide input in the following format");
			System.out.println("java -jar <jar_file_path> <input_file_path>");
		}
	}
}

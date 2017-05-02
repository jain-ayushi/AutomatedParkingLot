package gojek;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputParser {
	
	private static ParkingLot parkingLot = new ParkingLot();
	
	public void parseFileInput(String filePath){
		File inputFile = new File(filePath);
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;
            try {
                while ((line = br.readLine()) != null) {
                    this.parseLineInput(line.trim());
                }
            } catch (IOException e) {
                System.out.println("Error in reading the input file.");
                e.printStackTrace();
            }
		} catch (FileNotFoundException ex){
			System.out.println("File not found on the specified path");
			System.out.println(ex.getMessage());
		}
	}

	private void parseLineInput(String line){
		String[] values = line.split("\\s+");
		if (!values[0].isEmpty()) {
			switch (values[0]) {
			case "create_parking_lot": 
				if (values.length == 2) 
				{
					try 
					{
						int slots = Integer.parseInt(values[1]);
						parkingLot.createParkingLot(slots);
					} catch (NumberFormatException ex) {
						System.out.println("Invalid input.Should be a number\n");
						System.out.println(ex.getMessage());
					}
				} else {
					System.out.println("Invalid input passed.\n");
				}
				
				break;
			
				
			case "park": 
				if (values.length == 3) {
					parkingLot.park(values[1], values[2]);
				} else {
					System.out.println("Invalid input");
				}
				break;
			

			case "leave": 
				if (values.length == 2) {
					try {
						parkingLot.leave(Integer.parseInt(values[1]));
					} catch (NumberFormatException ex) {
						System.out.println("Invalid input. Should be a number");
						System.out.println(ex.getMessage());
					}
				} else {
					System.out.println("Invalid input");
				}
				break;
			

			case "status": 
				if (values.length == 1) {
					parkingLot.getParkingStatus();
				} else {
					System.out.println("Invalid input");
				}
				break;
			

			case "registration_numbers_for_cars_with_colour": 
				if (values.length == 2){
					parkingLot.getRegNosFromColor(values[1]);
				} else {
					System.out.println("Invalid input");
				}
				break;
			

			case "slot_numbers_for_cars_with_colour": 
				if (values.length == 2) {
					parkingLot.getSlotNosFromColor(values[1]);
				} else {
					System.out.println("Invalid input");
				}
				break;

			case "slot_number_for_registration_number": {
				if (values.length == 2) {
					parkingLot.getSlotNoFromRegNo(values[1]);
				} else
					System.out.println("Invalid input");
				}
				break;

			default: 
				System.out.println("Invalid command Entered.\n");
				break;
			}
		}

		
	}
}

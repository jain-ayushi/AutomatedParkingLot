package gojek;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Main {
	
	private static ParkingLot parkingLot = new ParkingLot();
	public static void main(String[] args) {
		String ipstr;
		BufferedReader br = null;
		String[] values;
		boolean exitFromApp = false;
		try {
			if (args.length > 0) {
				br = new BufferedReader(new FileReader(args[0]));
			}
			while (!exitFromApp) {
				if (args.length == 0) {
					System.out.println("Enter Command: ");
					br = new BufferedReader(new InputStreamReader(System.in));
				}
				ipstr = br.readLine().trim();
				values = ipstr.split("\\s+");
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
								System.out.println("Inavlid input.Should be a number\n");
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

					case "quit": 
						exitFromApp = true;
						break;
					
						
					default: 
						System.out.println("Invalid command Entered.\n");
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Application Terminated");
		}
	}
}

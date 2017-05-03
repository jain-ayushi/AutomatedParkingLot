package gojek;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

	ParkingLot parkingLot = new ParkingLot();
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    
	@Test
	 public void createParkingLot() throws Exception {
        parkingLot.createParkingLot(6);
        assertEquals("Created a parking lot with 6 slots\n", output.toString());
    }
	
	@Test
    public void park() throws Exception {
        parkingLot.park("KA-01-HH-1234", "White");
        assertEquals("Sorry, parking lot is not created\n", output.toString());
        parkingLot.createParkingLot(6);
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        assertEquals(4, parkingLot.availableSlots.size());
        assertEquals(2, parkingLot.parkedCars.size());
    }
	
	@Test
	public void leave() throws Exception {
		parkingLot.leave(2);
		parkingLot.createParkingLot(6);
        parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.leave(2);
        parkingLot.leave(2);
        assertEquals("Sorry, parking lot is not created\n" +
        		"Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Slot number 2 is free\n" +
                "Parking slot is already empty\n", output.toString());
	}
	
	@Test
	public void getParkingStatus() throws Exception {
		parkingLot.getParkingStatus();
		parkingLot.createParkingLot(6);
		parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.getParkingStatus();
        assertEquals("Sorry, parking lot is not created\n" +
        "Created a parking lot with 6 slots\n" +
        "Allocated slot number: 1\n" +
        "Allocated slot number: 2\n" +
        "Slot No.\tRegistration No.\tColor\n" +
        "1\tKA-01-HH-1234\tWhite\n" + "2\tKA-01-HH-9999\tWhite\n\n", output.toString());
	}
	
	@Test
	public void getRegNosFromColor() throws Exception {
		parkingLot.getRegNosFromColor("White");
		parkingLot.createParkingLot(6);
		parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.getRegNosFromColor("White");
        parkingLot.getRegNosFromColor("Red");
        assertEquals("Sorry, parking lot is not created\n" +
        		"Created a parking lot with 6 slots\n" +
        		"Allocated slot number: 1\n" +
        		"Allocated slot number: 2\n" +
        		"KA-01-HH-1234, KA-01-HH-9999\n" +
        		"No cars found for this color\n\n", output.toString());
	} 
	
	@Test
	public void getSlotNoFromRegNo() throws Exception {
		parkingLot.getSlotNoFromRegNo("KA-01-HH-1234");
		parkingLot.createParkingLot(6);
		parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.getSlotNoFromRegNo("KA-01-HH-9999");
        parkingLot.getSlotNoFromRegNo("KA-01-1111");
        assertEquals("Sorry, parking lot is not created\n" +
        		"Created a parking lot with 6 slots\n" +
        		"Allocated slot number: 1\n" +
        		"Allocated slot number: 2\n" +
        		"2\n" +
        		"Not found\n\n", output.toString());
	}
	
	@Test
	public void getSlotNosFromColor() throws Exception {
		parkingLot.getSlotNosFromColor("White");
		parkingLot.createParkingLot(6);
		parkingLot.park("KA-01-HH-1234", "White");
        parkingLot.park("KA-01-HH-9999", "White");
        parkingLot.getSlotNosFromColor("White");
        parkingLot.getSlotNosFromColor("4");
        assertEquals("Sorry, parking lot is not created\n" +
        		"Created a parking lot with 6 slots\n" +
        		"Allocated slot number: 1\n" +
        		"Allocated slot number: 2\n" +
        		"1, 2\n" +
        		"Not found\n\n", output.toString());
	}
}

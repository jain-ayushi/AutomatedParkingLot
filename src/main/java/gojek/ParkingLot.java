package gojek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ParkingLot {

	private PriorityQueue<Integer> availableSlots = null;
	private boolean isParkingLotCreated = false;
	private List<String> parkedCars;
	//Map for slot and Car object
	private Map<Integer, Car> map1;
	//Map for color and corresponding list of regNos
	private Map<String, ArrayList<String>> map2;
	//Map for regNo and corresponding slot
	private Map<String, Integer> map3;
	
	public void createParkingLot(int slots) {
		if(slots >0 && !this.isParkingLotCreated){
			this.availableSlots = new PriorityQueue<Integer>(slots);
			for (int i=0;i<slots;i++){
				this.availableSlots.add(i);
			}
			this.isParkingLotCreated = true;
			this.parkedCars = new ArrayList<String>();
			this.map1 = new HashMap<Integer, Car>();
			this.map2 = new HashMap<String, ArrayList<String>>();
			this.map3 = new HashMap<String, Integer>();
			System.out.println("Created a parking lot with "+slots+" slots");
		} else {
			if(this.isParkingLotCreated) {
				System.out.println("Parking lot already exists");
			} 
			if(slots <= 0){
				System.out.println("Please enter a valid value for slots");
			}
		}
	}
	
	public void park(String regNo, String color) {
		if (!parkedCars.contains(regNo)) {
			if(this.availableSlots.peek()!=null) {
				int emptySlot = this.availableSlots.poll() + 1;
				Car newCar = new Car(regNo, color);
				this.parkedCars.add(regNo);
				this.map1.put(emptySlot, newCar);
				this.map3.put(regNo, emptySlot);
				if(this.map2.containsKey(color)) {
					ArrayList<String> oldRegNosList = this.map2.get(color);
					this.map2.remove(color);
					oldRegNosList.add(regNo);
					this.map2.put(color, oldRegNosList);
				} else {
					ArrayList<String> regNosList = new ArrayList<String>();
					regNosList.add(regNo);
					this.map2.put(color, regNosList);
				}
				System.out.println("Allocated slot number: " + emptySlot);
			} else {
				System.out.println("Sorry, parking lot is full");
			}
		} else {
			System.out.println("Car with registration number :"+regNo+" is already parked");
		}
	}
	
	public void leave(int slotNo){
		if(this.map1.containsKey(slotNo)){
			Car car = this.map1.get(slotNo);
			this.map1.remove(slotNo);
			String regNo = car.getRegNo();
			this.map3.remove(regNo);
			
			String color = car.getColor();
			ArrayList<String> regNoList = this.map2.get(color);
			if (regNoList.contains(regNo)){
				regNoList.remove(regNo);
			}
			this.map2.remove(color);
			if (!regNoList.isEmpty()){
				this.map2.put(color, regNoList);
			}
			this.parkedCars.remove(regNo);
			this.availableSlots.add(slotNo-1);
			System.out.println("Slot number " + slotNo + " is free");
		} else {
			System.out.println("Parking slot is already empty");
		}
	}
	
	public void getParkingStatus() {
		if (!this.isParkingLotCreated) {
			System.out.println("Sorry, parking lot is not created");
		} else {
			if (this.map1.size()>0) {
				 System.out.println("Slot No.\tRegistration No.\tColor");
				 Iterator<Integer> iterator = this.map1.keySet().iterator();
				 
				 while(iterator.hasNext()) {
					 Integer slot = iterator.next();
					 Car parkedCar = this.map1.get(slot);
					 System.out.println(slot.toString() + "\t" + parkedCar.getRegNo() + "\t" + parkedCar.getColor());
				 }
				 System.out.println();
			} else {
				System.out.println("Parking lot is empty");
				System.out.println();
			}
		}
	}
	
	public void getRegNosFromColor(String color) {
		if (!this.isParkingLotCreated){
			System.out.println("Sorry, parking lot is not created");
		} else if (this.map2.containsKey(color)){
				ArrayList<String> regNoList = map2.get(color);
				StringBuilder sb = new StringBuilder();
				for (int i=0;i<regNoList.size();i++){
					sb.append(regNoList.get(i)).append(", ");
				}
				sb.delete(sb.length()-2, sb.length());
				System.out.println(sb.toString());
		} else {
			System.out.println("No cars found for this color");
			System.out.println();
		}
	}
	
	public void getSlotNoFromRegNo(String regNo) {
		if (!this.isParkingLotCreated){
			System.out.println("Sorry, parking lot is not created");
		} else if (this.map3.containsKey(regNo)){
				System.out.println(map3.get(regNo));
		} else {
			System.out.println("Not found");
			System.out.println();
		}
	}
	
	public void getSlotNosFromColor(String color) {
		if (!this.isParkingLotCreated){
			System.out.println("Sorry, parking lot is not created");
		} else if (this.map2.containsKey(color)){
				ArrayList<String> regNoList = map2.get(color);
				StringBuilder sb = new StringBuilder();
				for(int i=0;i<regNoList.size();i++){
					sb.append(this.map3.get(regNoList.get(i))).append(", ");
				}
				sb.delete(sb.length()-2, sb.length());
				System.out.println(sb.toString());
		} else {
			System.out.println("Not found");
			System.out.println();
		}
	}
	 
}

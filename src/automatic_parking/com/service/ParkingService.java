package automatic_parking.com.service;

import java.util.Scanner;

import automatic_parking.com.core.Car;
import automatic_parking.com.core.Slot;

public class ParkingService {
	
	static Scanner scanner = new Scanner(System.in);
	Slot slot=new Slot(5,20);
	
	public void entry() {
		Car newcar = new Car();
		
		System.out.println("Enter Car Registration Number : ");
		String registrationNumber = scanner.next();
		newcar.setRegistrationNumber(registrationNumber);
		
		System.out.println("Enter Car Color : ");
		String color = scanner.next();
		newcar.setColorOfCar(color);
	
		
		int slot0 =0;
		
		for (int i = 0; i <slot.getParkingSlot().length; i++) 
		{
			if(slot.getParkingSlot()[i]==null)
			{
				slot0=i;
				break;
			}
		}
		slot.getParkingSlot()[slot0]=newcar;
		
		System.out.println(newcar+"\nPark in Slot "+(slot0+1));
		System.out.println("------------------");
	}
	
	
	public void dePark() {
		Car carExit = new Car();
		boolean vehicleAvialble=false;
		System.out.println("Enter Car Registration Number :");
		String registrationNumber = scanner.next();
		carExit.setRegistrationNumber(registrationNumber);
		
		for (int i = 0; i < slot.getParkingSlot().length; i++)
		{
			if(slot.getParkingSlot()[i]!=null && slot.getParkingSlot()[i].getRegistrationNumber().equals(registrationNumber))
			{
				slot.getParkingSlot()[i]=null;
				System.out.println("Vehicle is Removed.\n");
				vehicleAvialble=true;
				break;
			}
		}
		if(!vehicleAvialble) {
			System.out.println("Vehicle is not Available. \n ");
		}
	}
	
	public void getRegistrationNumberByColor()
	{
		System.out.println("Enter Car Color : ");
		String color = scanner.next();
		
		for (int i = 0; i < slot.getParkingSlot().length; i++) {
			
			if(slot.getParkingSlot()[i]!=null)
			{
				if(slot.getParkingSlot()[i].getColorOfCar().equalsIgnoreCase(color)) 
				{
					System.out.println(slot.getParkingSlot()[i].getRegistrationNumber());
		
				}
				
			}
			
		}
		
	}
	
	
	public void getParkingSlotByRegistrationNumber()
	{
		System.out.println("Enter Car Registration Number : ");
		String registrationNumber = scanner.next();
		int slot1=0;
		for (int i = 0; i < slot.getParkingSlot().length; i++) {
			if(slot.getParkingSlot()[i].getRegistrationNumber().equalsIgnoreCase(registrationNumber))
			{
				slot1 = i;
				System.out.println("Your car is Parked in Slot-"+(slot1+1));
				break;
			}
			
		}
	}
	
	
	public void getSlotNumberByColor()
	{
		System.out.println("Enter Car Color : ");
		String color = scanner.next();
		
		int slot1=0;
		for (int i = 0; i < slot.getParkingSlot().length; i++) {
			if(slot.getParkingSlot()[i].getColorOfCar().equalsIgnoreCase(color))
			{
				slot1=i;
				System.out.println("Slot Number :"+(slot1+1));
				break;
				
			}
		}
	}
	
	
	public void getListOfSlot() {
		for (int i = 0; i < slot.getParkingSlot().length; i++) {
			if(slot.getParkingSlot()[i]!=null)
			{
			  System.out.println(slot.getParkingSlot()[i]);
			  System.out.println("Vehicle Slot :"+(i+1));
			  System.out.println("-------------------");
			  
			}
		}
	}

}

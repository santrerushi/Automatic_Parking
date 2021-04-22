package automatic_parking.com.service;

import java.util.Scanner;

import automatic_parking.com.car.Car;

public class Service {
	
	Scanner scanner = new Scanner(System.in);
	
	String registrationNumber;
	String color;
	
	public void entry(Car[] parkingArray) {
		Car newcar = new Car();
		
		System.out.println("Enter Car Registration Number : ");
		registrationNumber = scanner.next();
		newcar.setregistration_Number(registrationNumber);
		
		System.out.println("Enter Car Color : ");
		color = scanner.next();
		newcar.setcolor_of_car(color);
		
		int slot0 =0;
		
		for (int i = 0; i < parkingArray.length; i++) 
		{
			if(parkingArray[i]==null)
			{
				slot0=i;
				break;
			}
		}
		parkingArray[slot0]=newcar;
		
		System.out.println(newcar+"\nPark in Slot "+(slot0+1));
		System.out.println("------------------");
	}
	
	
	public void exit(Car[] parkingArray) {
		Car carExit = new Car();
		boolean vehicleAvialble=false;
		System.out.println("Enter Car Registration Number :");
		registrationNumber = scanner.next();
		carExit.setregistration_Number(registrationNumber);
		
		for (int i = 0; i < parkingArray.length; i++)
		{
			if(parkingArray[i]!=null && parkingArray[i].getregistration_Number().equals(registrationNumber))
			{
				parkingArray[i]=null;
				System.out.println("Vehicle is Removed.\n");
				vehicleAvialble=true;
				break;
			}
		}
		if(!vehicleAvialble) {
			System.out.println("Vehicle is not Available. \n ");
		}
	}
	
	public void getRegistrationNumberByColor(String color,Car[] array)
	{
		
		for (int i = 0; i < array.length; i++) {
			
			if(array[i]!=null)
			{
				if(array[i].getcolor_of_car().equalsIgnoreCase(color)) 
				{
					System.out.println(array[i].getregistration_Number());
		
				}
				
			}
			
		}
		
	}
	
	
	public void getParkingSlotByRegistrationNumber(String RegistrationNumber,Car[] array)
	{
		int slot=0;
		for (int i = 0; i < array.length; i++) {
			if(array[i].getregistration_Number().equalsIgnoreCase(RegistrationNumber))
			{
				slot = i;
				System.out.println("Your car is Parked in Slot-"+(slot+1));
				break;
			}
			
		}
	}
	
	
	public void getSlotNumberByColor(String color,Car[] array)
	{
	
		int slot1=0;
		for (int i = 0; i < array.length; i++) {
			if(array[i].getcolor_of_car().equalsIgnoreCase(color))
			{
				slot1=i;
				System.out.println("Slot Number :"+(slot1+1));
				break;
				
			}
		}
	}
	
	
	public void getListOfSlot(Car[] parkingArray) {
		for (int i = 0; i < parkingArray.length; i++) {
			if(parkingArray[i]!=null)
			{
			  System.out.println(parkingArray[i]);
			  System.out.println("Vehicle Slot :"+(i+1));
			  System.out.println("-------------------");
			  
			}
		}
	}

}

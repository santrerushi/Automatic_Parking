package com.service;

import java.util.Scanner;

import com.model.Car;

public class ParkingService {

	public static void main(String[] args) {
		
		Car[] parkingArray=new Car[100];
		Scanner scanner = new Scanner(System.in);
		
		String registrationNumber;
		String color;
		Searching searching = new Searching();
		
		
		while (true) {
			System.out.println("\n 1.Entry \n 2.Exit \n 3.getRegistrationNumberByColor \n 4.getSlotNumberByRegistrationNumber \n 5.getSlotNumberByColor \n 6.getListOfSlot \n \n Enter Your Choice :");
			int choice=0;
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				Car newcar = new Car();
				System.out.println("Enter Car Registration Number : ");
				registrationNumber = scanner.next();
				newcar.setRegistration_Number(registrationNumber);
				System.out.println("Enter Car Color : ");
				color = scanner.next();
				newcar.setColor(color);
				int Slot =0;
				
				for (int i = 0; i < parkingArray.length; i++) {
					if(parkingArray[i]==null)
					{
						Slot=i;
						break;
					}
				}
				parkingArray[Slot] =newcar;	
				System.out.println(newcar+"\nPark in Slot "+(Slot+1));
				System.out.println("------------------");
				break;

			case 2:
				Car carExit = new Car();
				boolean vehicleAvialble=false;
				System.out.println("Enter Car Registration Number");
				registrationNumber = scanner.next();
				carExit.setRegistration_Number(registrationNumber);
				
				for (int i = 0; i < parkingArray.length; i++) {
					if(parkingArray[i]!=null && parkingArray[i].getRegistration_Number().equals(registrationNumber))
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
				break;
				
			case 3:
				System.out.println("Enter Car Color : ");
				color = scanner.next();
				
				searching.getRegistrationNumberByColor(color, parkingArray);
				break;
				
			case 4:
				System.out.println("Enter Car Registration Number : ");
				registrationNumber = scanner.next();
				
				searching.getParkingSlotByRegistrationNumber(registrationNumber, parkingArray);
				break;
			
			case 5:
				System.out.println("Enter Car Color : ");
				color = scanner.next();
				
				searching.getSlotNumberByColor(color, parkingArray);
				break;
				
			case 6:
				for (int i = 0; i < parkingArray.length; i++) {
					if(parkingArray[i]!=null)
					{
					  System.out.println(parkingArray[i]);
					  System.out.println("Vehicle Slot :"+(i+1));
					  System.out.println("-------------------");
					  
					}
				}
				
			default:
				break;
			}
			
		}
		
	}

}

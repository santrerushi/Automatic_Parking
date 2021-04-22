package automatic_parking;

import java.util.Scanner;


import automatic_parking.com.car.Car;
import automatic_parking.com.service.Service;


public class Main {
public static void main(String[] args) {
		
		Car[] parkingArray=new Car[100];
     	Scanner scanner = new Scanner(System.in);
		
	    String registrationNumber;
		String color ;
		Service service = new Service();
		
		
		while (true) {
			System.out.println("\n 1.Entry \n 2.Exit \n 3.getRegistrationNumberByColor \n 4.getSlotNumberByRegistrationNumber \n 5.getSlotNumberByColor \n 6.getListOfSlot \n \n Enter Your Choice :");
			int choice=0;
			choice = scanner.nextInt();
			switch (choice) 
			{
			
			case 1:
				service.entry(parkingArray);
				break;

				
			case 2:
				service.exit(parkingArray);
				break;
				
				
			case 3:
			    System.out.println("Enter Car Color : ");
      			color = scanner.next();
				
				service.getRegistrationNumberByColor(color, parkingArray);
				break;
				
				
			case 4:
				System.out.println("Enter Car Registration Number : ");
				registrationNumber = scanner.next();
				
				service.getParkingSlotByRegistrationNumber(registrationNumber, parkingArray);
				break;
			
				
			case 5:
				System.out.println("Enter Car Color : ");
				color = scanner.next();
				
				service.getSlotNumberByColor(color, parkingArray);
				break;
				
				
			case 6:

				service.getListOfSlot(parkingArray);
				
				
			default:
				break;
			}
			
		}
		
	}


}

package automatic_parking;

import java.util.Scanner;
import automatic_parking.com.service.ParkingService;


public class Main {
public static void main(String[] args) {
		
     	Scanner scanner = new Scanner(System.in);
		ParkingService parkingservice=new ParkingService();
			
		while (true) {
			System.out.println("\n 1.Entry \n 2.Exit \n 3.getRegistrationNumberByColor \n 4.getSlotNumberByRegistrationNumber \n 5.getSlotNumberByColor \n 6.getListOfSlot \n \n Enter Your Choice :");
			int choice=0;
			choice = scanner.nextInt();
			switch (choice) 
			{
			
			case 1:
				parkingservice.entry();
				break;

				
			case 2:
				parkingservice.dePark();
				break;
				
				
			case 3:
				
				parkingservice.getRegistrationNumberByColor();
				break;
				
				
			case 4:
				
				parkingservice.getParkingSlotByRegistrationNumber();
				break;
			
				
			case 5:
				
				parkingservice.getSlotNumberByColor();
				break;
				
				
			case 6:

				parkingservice.getListOfSlot();
				
				
			default:
				break;
			}
			
		}
		
	}


}

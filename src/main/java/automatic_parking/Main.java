package automatic_parking;

import automatic_parking.com.client.Mysqlclient;
import automatic_parking.com.service.ParkingService;
import automatic_parking.com.utility.AppConfig;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

     	Scanner scanner = new Scanner(System.in);
		ParkingService parkingService=new ParkingService();
	    Mysqlclient sql=new Mysqlclient();
	    AppConfig applicationConfig=new AppConfig();
	    applicationConfig.fileConfig();

	    String clientName= applicationConfig.getClient();
	    if(clientName.equalsIgnoreCase("mysql"))
	    {
	       System.out.println("Client Name - "+clientName);
		   applicationConfig.mySqlConnection();
	    }

	    else if(clientName.equalsIgnoreCase("InMemory"))
		{
	    	ParkingService client=parkingService;
		}

		while (true) {
			System.out.println("\n 1.Entry \n 2.Exit \n 3.getRegistrationNumberByColor \n 4.getSlotNumberByRegistrationNumber \n 5.getSlotNumberByColor \n 6.getListOfSlot \n \n Enter Your Choice :");
			int choice=0;
			choice = scanner.nextInt();
			switch (choice)
			{

			case 1:
				//parkingService.entry();
				sql.entry();
				break;


			case 2:
				//parkingService.dePark();
				sql.dePark();
				break;


			case 3:
				//parkingService.getRegistrationNumberByColor();
				sql.getRegistrationNumberByColor();
				break;


			case 4:
				//parkingService.getParkingSlotByRegistrationNumber();
				sql.getSlotNumberByRegistartionNumber();
				break;


			case 5:
				//parkingService.getSlotNumberByColor();
				sql.getSlotNumberByColor();
				break;


			case 6:
				//parkingService.getListOfSlot();
				sql.getListOfSlot();
				break;

			default:
				System.out.println("Invalid Input ");
				break;
			}
		}
	}
}

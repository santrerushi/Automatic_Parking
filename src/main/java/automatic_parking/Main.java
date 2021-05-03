package automatic_parking;

import automatic_parking.com.client.BaseClient;
import automatic_parking.com.client.InMemory;
import automatic_parking.com.client.MongoClient;
import automatic_parking.com.client.Mysqlclient;
import automatic_parking.com.utility.AppConfig;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		AppConfig applicationConfig=new AppConfig();
	    applicationConfig.fileConfig();
	    String clientName= applicationConfig.getClient();
		BaseClient baseClient=new InMemory();

	    if(clientName.equalsIgnoreCase("mysql"))
	    {
	       System.out.println("Client Name - "+clientName);
	       baseClient=new Mysqlclient();
		   applicationConfig.mySqlConnection();
	    }
	    else if(clientName.equalsIgnoreCase("mongodb")){
			System.out.println("Client Name - "+clientName);
			baseClient=new MongoClient();
			applicationConfig.mongoConnection();
			MongoClient mongoClient=(MongoClient) baseClient;
			mongoClient.createCollection();
		}

		while (true) {
			System.out.println("\n 1.Entry \n 2.Exit \n 3.getRegistrationNumberByColor \n 4.getSlotNumberByRegistrationNumber \n 5.getSlotNumberByColor \n 6.getListOfSlot \n \n Enter Your Choice :");
			int choice=0;
			Scanner scanner = new Scanner(System.in);
			choice = scanner.nextInt();
			switch (choice)
			{
			case 1:
				baseClient.entry();
				break;

			case 2:
				baseClient.dePark();
				break;

			case 3:
				baseClient.getRegistrationNumberByColor();
				break;

			case 4:
				baseClient.getSlotNumberByRegistrationNumber();
				break;

			case 5:
				baseClient.getSlotNumberByColor();
				break;

			case 6:
				baseClient.getListOfSlot();
				break;

			default:
				System.out.println("Invalid Input ");
				break;
			}
		}
	}
}

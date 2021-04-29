package automatic_parking.com.client;

import java.util.Scanner;

public abstract class BaseClient {
    Scanner sc= new Scanner(System.in);

    public String registrationNumber(){
        System.out.println("Enter Car Registration Number : ");
        String registrationNumber = sc.nextLine();
        return registrationNumber;
    }

    public String color(){
        System.out.println("Enter Car Color : ");
        String color = sc.nextLine();
        return color;
    }

    public abstract void entry();
    public abstract void dePark();
    public abstract void getRegistrationNumberByColor();
    public abstract void getSlotNumberByRegistrationNumber();
    public abstract void getSlotNumberByColor();
    public abstract void getListOfSlot();




}

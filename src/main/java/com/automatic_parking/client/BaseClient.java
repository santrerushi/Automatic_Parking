package com.automatic_parking.client;

import java.util.Scanner;

public abstract class BaseClient {
    Scanner sc= new Scanner(System.in);

    public String registrationNumber(){
        System.out.println("Enter Car Registration Number : ");
        return sc.nextLine();
    }

    public String color(){
        System.out.println("Enter Car Color : ");
        return sc.nextLine();
    }
    public abstract void entry();
    public abstract void dePark();
    public abstract void getRegistrationNumberByColor();
    public abstract void getSlotNumberByRegistrationNumber();
    public abstract void getSlotNumberByColor();
    public abstract void getListOfSlot();
}

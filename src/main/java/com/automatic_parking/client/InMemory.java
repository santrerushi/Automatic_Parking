package com.automatic_parking.client;

import com.automatic_parking.core.Car;
import com.automatic_parking.core.Slot;

public class InMemory extends BaseClient{
    Slot slot=new Slot(5,20);
    @Override
    public void entry() {
        Car newCar = new Car();
        newCar.setRegistrationNumber(registrationNumber());
        newCar.setColorOfCar(color());
        int slot0 =0;
        for (int i = 0; i <slot.getParkingSlot().length; i++)
        {
            if(slot.getParkingSlot()[i]==null)
            {
                slot0=i;
                break;
            }
        }
        slot.getParkingSlot()[slot0]=newCar;
        System.out.println(newCar+"\nPark in Slot "+(slot0+1));
        System.out.println("------------------");
    }

    @Override
    public void dePark() {
        Car carExit = new Car();
        boolean vehicleAvialble=false;;
        String regNo=registrationNumber();
        carExit.setRegistrationNumber(regNo);

        for (int i = 0; i < slot.getParkingSlot().length; i++)
        {
            if(slot.getParkingSlot()[i]!=null && slot.getParkingSlot()[i].getRegistrationNumber().equals(regNo))
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
    @Override
    public void getRegistrationNumberByColor()
    {
        String color=color();
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

    @Override
    public void getSlotNumberByRegistrationNumber()
    {
        String regNo =registrationNumber();
        int slot1=0;
        for (int i = 0; i < slot.getParkingSlot().length; i++) {
            if(slot.getParkingSlot()[i].getRegistrationNumber().equalsIgnoreCase(regNo))
            {
                slot1 = i;
                System.out.println("Your Car is Parked in Slot-"+(slot1+1));
                break;
            }
        }
    }

    @Override
    public void getSlotNumberByColor()
    {
        String color=color();
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

    @Override
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

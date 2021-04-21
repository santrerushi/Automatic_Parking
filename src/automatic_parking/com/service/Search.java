package automatic_parking.com.service;

import automatic_parking.com.car.Car;

public class Search {
	public void getRegistrationNumberByColor(String color,Car[] array)
	{
		
		for (int i = 0; i < array.length; i++) {
			Car newcar = new Car();
			newcar=array[i];
			
			if(newcar!=null)
			{
				if(newcar.getColor().equalsIgnoreCase(color)) 
				{
					System.out.println(newcar.getRegistrationNumber());
		
				}
				
			}
			
		}
		
	}
	
	
	public void getParkingSlotByRegistrationNumber(String RegistrationNumber,Car[] array)
	{
		int slot=0;
		for (int i = 0; i < array.length; i++) {
			if(array[i].getRegistrationNumber().equalsIgnoreCase(RegistrationNumber))
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
			if(array[i].getColor().equalsIgnoreCase(color))
			{
				slot1=i;
				System.out.println("Slot Number :"+(slot1+1));
				break;
				
			}
		}
	}
	

}
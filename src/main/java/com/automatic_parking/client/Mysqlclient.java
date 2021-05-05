package com.automatic_parking.client;

import com.automatic_parking.utility.AppConfig;
import java.sql.*;
import java.util.Scanner;

public class Mysqlclient extends BaseClient {
    AppConfig appConfig=new AppConfig();
    @Override
    public void entry(){
        boolean flag=false;
        try {
            String qry1="SELECT * from parking_sys";
            ResultSet rs=appConfig.getStatement().executeQuery(qry1);
            while(rs.next()) {
                if (rs.getString(1)==null) {
                    int slot=rs.getInt(3);
                    PreparedStatement pst = appConfig.getConnection().prepareStatement("UPDATE parking_sys SET RegistrationNumber = ?,Color = ? where SlotNumber=? ");
                    pst.setString(1, registrationNumber());
                    pst.setString(2, color());
                    pst.setInt(3,slot);
                    pst.executeUpdate();
                    System.out.println("Car is Entered in MySQL Database !!");
                    flag=true;
                    break;
                }
            }if(!flag){
                PreparedStatement pst = appConfig.getConnection().prepareStatement("insert into parking_sys(RegistrationNumber,Color) values(?,?) ");
                pst.setString(1, registrationNumber());
                pst.setString(2, color());
                pst.executeUpdate();
                System.out.println("Car is Entered in MySQL Database !!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void dePark(){
        try{
            PreparedStatement pst=appConfig.getConnection().prepareStatement("UPDATE parking_sys " + " SET RegistrationNumber= ?,Color=? WHERE RegistrationNumber = ?");
            pst.setString(1,null);
            pst.setString(2,null);
            pst.setString(3,registrationNumber());
            pst.executeUpdate();
            System.out.println("Car is Removed !! ");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getRegistrationNumberByColor(){
        try{
            String query1="select RegistrationNumber from parking_sys where Color='"+color()+"'";
            ResultSet rs=appConfig.getStatement().executeQuery(query1);
            while (rs.next()) {
                String registrationNumber = rs.getObject(1).toString();
                System.out.println(registrationNumber);}
        }
        catch( Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void getSlotNumberByRegistrationNumber(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Registration Number to be searched : ");
            String registrationNumber = sc.next();
            String query1 = "select SlotNumber from parking_sys where RegistrationNumber='" + registrationNumber + "' ";
            ResultSet rs=appConfig.getStatement().executeQuery(query1);
            while (rs.next()) {
                String slotNumber = rs.getObject(1).toString();
                System.out.println(slotNumber);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void getSlotNumberByColor(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Vehicle Color to be searched  : ");
            String color = sc.next();
            String query1 = "select SlotNumber from parking_sys where Color='" + color + "' ";
            ResultSet rs=appConfig.getStatement().executeQuery(query1);
            while (rs.next()) {
                String slotNumber = rs.getObject(1).toString();
                System.out.println(slotNumber);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void getListOfSlot(){
        try {
            String query = "select * from parking_sys where RegistrationNumber IS NOT NULL ";
            ResultSet rs=appConfig.getStatement().executeQuery(query);
            while (rs.next()) {
                String registrationNumber = rs.getObject(1).toString();
                String color = rs.getObject(2).toString();
                int slot = rs.getObject(3).hashCode();
                System.out.println(registrationNumber + "  " + color + "       " + slot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            for (Throwable ex : e) {
                System.err.println("Error occurred " + ex);
            }
            System.out.println("Error in fetching data");
        }
    }
    }


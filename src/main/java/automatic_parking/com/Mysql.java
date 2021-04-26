package automatic_parking.com;


import java.sql.*;
import java.util.Scanner;

public class Mysql {
    static Connection con = null;

    public void entryDB(){
        boolean flag=false;
        try {
            Statement stmt= con.createStatement();
            String qry1="SELECT * from parking_sys";
            ResultSet rs= stmt.executeQuery(qry1);
            while(rs.next()) {
                if (rs.getString(1)==null) {
                    int slot=rs.getInt(3);
                    //PreparedStatement pst = con.prepareStatement("insert into parking_sys(RegistrationNumber,Color) values(?,?) ");
                    PreparedStatement pst = con.prepareStatement("UPDATE parking_sys SET RegistrationNumber = ?,Color = ? where SlotNumber=? ");
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Enter the Registration Number : ");
                    String registration = sc.next();
                    System.out.print("Enter the Color : ");
                    String color = sc.next();
                    pst.setString(1, registration);
                    pst.setString(2, color);
                    pst.setInt(3,slot);
                    pst.executeUpdate();
                    System.out.println("Car is Entered successfully via update !!");
                    flag=true;
                    break;

                  }


            }if(!flag){
                PreparedStatement pst = con.prepareStatement("insert into parking_sys(RegistrationNumber,Color) values(?,?) ");
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter the Registration Number : ");
                String registration = sc.next();
                System.out.print("Enter the Color : ");
                String color = sc.next();
                pst.setString(1, registration);
                pst.setString(2, color);
                pst.executeUpdate();
                System.out.println("Car is Entered !!");

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void removeDB(){
        try{
            PreparedStatement pst=con.prepareStatement("UPDATE parking_sys " + " SET RegistrationNumber= ?,Color=? WHERE RegistrationNumber = ?");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Registration Number to be removed : ");
            String regNo=sc.next();

            pst.setString(1,null);
            pst.setString(2,null);
            pst.setString(3,regNo);
            pst.executeUpdate();
            System.out.println("Car is Removed !! ");
        }catch(Exception e){
            e.printStackTrace();
        }

        }

        public void getRegNumberByColor(){
            try{
                Statement stmt=con.createStatement();
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter Color to be searched  : ");
                String color=sc.next();
                String query1="select RegistrationNumber from parking_sys where Color='"+color+"'";
                ResultSet rs = stmt.executeQuery(query1);
                while (rs.next()) {
                    String registrationNumber = rs.getObject(1).toString();
                    System.out.println(registrationNumber);}
            }
            catch( Exception e)
            {
                e.printStackTrace();
            }
        }

        public void getSlotNumberByRegNumber(){
            try {
                Statement stmt = con.createStatement();
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Registration Number to be searched : ");
                String registrationNumber = sc.next();
                String query1 = "select SlotNumber from parking_sys where RegistrationNumber='" + registrationNumber + "' ";
                ResultSet rs = stmt.executeQuery(query1);
                while (rs.next()) {
                    String slotNumber = rs.getObject(1).toString();
                    System.out.println(slotNumber);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        public void getSlotNumberByColor(){
            try {
                Statement stmt = con.createStatement();
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Vehicle Color to be searched  : ");
                String color = sc.next();
                String query1 = "select SlotNumber from parking_sys where Color='" + color + "' ";
                ResultSet rs = stmt.executeQuery(query1);
                while (rs.next()) {
                    String slotNumber = rs.getObject(1).toString();
                    System.out.println(slotNumber);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        public void allSlot(){
            try {
                Statement stmt = con.createStatement();
                String query = "select * from parking_sys where RegistrationNumber IS NOT NULL ";
                ResultSet rs = stmt.executeQuery(query);
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

        public void sqlConnection(){
            String URL = "jdbc:mysql://localhost:3306/parking_system?useSSL=false";
            String UserName = "root";
            String Password = "Near@123";

            try {
                Mysql.con = DriverManager.getConnection(URL, UserName, Password);

                if (con != null) {
                    System.out.println("Database connection is successfully done !!!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}

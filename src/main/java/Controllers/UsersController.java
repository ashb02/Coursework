package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;


public class UsersController {

    public static void listUser(){
        try{
            PreparedStatement ps = db.prepareStatement("SELECT UserID, EmailAddress, FirstName, Password FROM Users");

            ResultSet results = ps.executeQuery();
            while(results.next()){
                int UserID = results.getInt(1);
                String EmailAddress =  results.getString(2);
                String FirstName = results.getString(3);
                String Password = results.getString(4);
                System.out.println(UserID + " " + EmailAddress + " " + FirstName + " " + Password);
            }
        } catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void insertUser(int UserID, String EmailAddress, String FirstName, String Password){
        try{
            PreparedStatement ps = db.prepareStatement("INSERT INTO Users (UserID, EmailAddress, FirstName, Password) VALUES (?, ?, ?, ?");
            ps.setInt(1, UserID);
            ps.setString(2, EmailAddress);
            ps.setString(3, FirstName);
            ps.setString(4, Password);
            ps.executeUpdate();
            System.out.println("Record added to Users table");

        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void UpdateUser(int UserID, String EmailAddress, String FirstName, String Password){
        try{
            PreparedStatement ps = db.prepareStatement("UPDATE Users SET UserID = ?, EmailAddress = ?, FirstName = ?, Password = ?");
            ps.setInt(1, UserID);
            ps.setString(2, EmailAddress);
            ps.setString(3, FirstName);
            ps.setString(4, Password);

        } catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void deleteUser(int UserID){
        try{
            PreparedStatement ps = db.prepareStatement("DELETE FROM Users WHERE UserID = ?");
            ps.setInt(1, UserID);
            ps.executeUpdate();

        } catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }


}

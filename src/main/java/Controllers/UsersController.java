package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;


public class UsersController
{

    //Outputs the items in the Users Table
    public static void listUser()
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT UserID, EmailAddress, FirstName, Password FROM Users");

            ResultSet results = ps.executeQuery();

            while(results.next()) //returns the next record until there are no more values in the column
            {
                int UserID = results.getInt(1);
                String EmailAddress =  results.getString(2);
                String FirstName = results.getString(3);
                String Password = results.getString(4);

                System.out.println(UserID + " " + EmailAddress + " " + FirstName + " " + Password);
            }
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Adds a record to the User Table
    public static void insertUser(int UserID, String EmailAddress, String FirstName, String Password)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Users (UserID, EmailAddress, FirstName, Password) VALUES (?, ?, ?, ?");

            //contain the values to be added through the SQL statement in place of the ?s
            ps.setInt(1, UserID);
            ps.setString(2, EmailAddress);
            ps.setString(3, FirstName);
            ps.setString(4, Password);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

            System.out.println("Record added to Users table");
        }
        catch(Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Changes a record wihtin the Users Table
    public static void UpdateUser(int UserID, String EmailAddress, String FirstName, String Password)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("UPDATE Users SET UserID = ?, EmailAddress = ?, FirstName = ?, Password = ?");

            //contain the values to be changed through the SQL statement in place of the ?s
            ps.setInt(1, UserID);
            ps.setString(2, EmailAddress);
            ps.setString(3, FirstName);
            ps.setString(4, Password);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Deletes a record within the User Table
    public static void deleteUser(int UserID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("DELETE FROM Users WHERE UserID = ?");

            ps.setInt(1, UserID);

            ps.executeUpdate();
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}
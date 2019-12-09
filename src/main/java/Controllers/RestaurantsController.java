package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class RestaurantsController
{

    //Outputs the items in the Restaurants table
    public static void listRestaurants()
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT RestaurantID, LocationID, RestaurantName, Description, Cost, Address, Website FROM Restaurants");

            ResultSet results = ps.executeQuery();

            while (results.next()) //returns the next record, returns false when no more in the table
            {
                int TripID = results.getInt(1);
                int DayNumber = results.getInt(2);
                int ActivityID = results.getInt(3);
                String Time = results.getString(4);
                String Notes = results.getString(5);

                System.out.println(TripID + " " + DayNumber + " " + ActivityID + " " + Time + " " + Notes);
            }
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    //Adds a record to the Restaurants table
    public static void insertRestaurant(int RestaurantID, int LocationID, String RestaurantName, String Description, String Cost, String Address, String Website)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Restaurants (RestaurantID, LocationID, RestaurantName, Description, Cost, Address, Website) VALUES (?, ?, ?, ?, ?, ?, ?); ");

            //contain the values to be added through the SQL statement in place of the ?s
            ps.setInt(1, RestaurantID);
            ps.setInt(2, LocationID);
            ps.setString(3, RestaurantName);
            ps.setString(4, Description);
            ps.setString(5, Cost);
            ps.setString(6, Address);
            ps.setString(7, Website);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

            System.out.println("Record added to Restaurants table");
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    //Changes a record within the Restaurant table
    public static void updateRestaurant(int RestaurantID, int LocationID, String RestaurantName, String Description, String Cost, String Address, String Website)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("UPDATE Restaurant SET RestaurantID = ?, LocationID = ?, RestaurantName = ?, Description = ?, Cost = ?, Address = ?, Website = ?");

            //contain the values to be changed through the SQL statement in place of the ?s
            ps.setInt(1, RestaurantID);
            ps.setInt(2, LocationID);
            ps.setString(3, RestaurantName);
            ps.setString(4, Description);
            ps.setString(5, Cost);
            ps.setString(6, Address);
            ps.setString(7, Website);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

        }
        catch(Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    //Deletes a record within the Restaurants table
    public static void deleteRestaurant(int RestaurantID, int LocationID, String RestaurantName, String Description, String Cost, String Address, String Website)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("DELETE FROM Restaurants WHERE RestaurantID = ?");

            ps.setInt(1, RestaurantID);

            ps.executeUpdate();
        }
        catch(Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}

package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class TripRestaurantsController
{

    //Outputs the items in the Accommodation Table
    public static void listTripRestaurants()
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT TripID, DayNumber, Time, RestaurantID FROM TripRestaurant");

            ResultSet results = ps.executeQuery();

            while (results.next()) //returns the next record until there are no more values in the column
            {
                int TripID = results.getInt(1);
                int DayNumber = results.getInt(2);
                String Time = results.getString(3);
                String RestaurantID = results.getString(4);

                System.out.println(TripID + " " + DayNumber + " " + Time + " " + RestaurantID + " ");
            }
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Adds a record to the TripRestaurants Table
    public static void insertTripRestaurants(int TripID, int DayNumber, String Time, int RestaurantID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO TripRestaurants (TripID, DayNumber, Time, RestaurantID) VALUES (?, ?, ?, ?)");

            //contain the values to be added through the SQL statement in place of the ?s
            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setString(3, Time);
            ps.setInt(4, RestaurantID);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

            System.out.println("Record added to TripRestaurants table");
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Changes a record within the TripRestarants Table
    public static void UpdateTripRestaurants(int TripID, int DayNumber, String Time, int RestaurantID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("UPDATE TripRestaurants SET TripID = ?, DayNumber = ?, Time = ?, RestaurantID = ?");

            //contain the values to be changed through the SQL statement in place of the ?s
            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setString(3, Time);
            ps.setInt(4, RestaurantID);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

        }
        catch(Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Deletes a record within the TripRestaurants Table
    public static void deleteTripRestaurants(int TripID, int DayNumber, String Time, int RestaurantID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("DELETE FROM TripRestaurants WHERE TripID = ?, DayNumber = ?, Time = ?, RestaurantID = ?");

            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setString(3, Time);
            ps.setInt(4, RestaurantID);

            ps.executeUpdate();
        }
        catch(Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}

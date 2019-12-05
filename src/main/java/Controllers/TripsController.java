package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class TripsController
{

    //Outputs the items in the Trips Table
    public static void listTrips()
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT TripID, TripStart, TripEnd, UserID FROM Trips");

            ResultSet results = ps.executeQuery();

            while(results.next())
            {
                int TripID = results.getInt(1);
                String TripStart = results.getString(2);
                String TripEnd = results.getString(3);
                int UserID = results.getInt(4);

                System.out.println(TripID + " " + TripStart + " " + TripEnd + " " + UserID);
            }
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Adds a record to the Trip Table
    public static void insertTrip(int TripID, String TripStart, String TripEnd, int UserID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Trips (TripId, TripStart, TripEnd, UserID) VALUES (?, ?, ?, ?)");

            ps.setInt(1, TripID);
            ps.setString(2, TripStart);
            ps.setString(3, TripEnd);
            ps.setInt(4, UserID);
            ps.executeUpdate();

            System.out.println("Record added to the Trips table");
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Changes a record within the Trip Table
    public static void updateTrip(int TripID, String TripStart, String TripEnd, int UserID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("UPDATE Trips SET TripID = ?, TripStart = ?, TripEnd = ?, UserID = ?");

            ps.setInt(1, TripID);
            ps.setString(2, TripStart);
            ps.setString(3, TripEnd);
            ps.setInt(4, UserID);

            ps.executeUpdate();
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Deletes a record within the Trip Table
    public static void deleteTrip(int TripID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("DELETE FROM Trips WHERE TripID = ?");

            ps.setInt(1, TripID);
            ps.executeUpdate();
        }
        catch(Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}

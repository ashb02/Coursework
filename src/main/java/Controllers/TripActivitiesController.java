package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class TripActivitiesController
{

    //Outputs the items in the TripActivites Table
    public static void listTripActivities()
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT TripID, DayNumber, ActivityID, Time, Notes FROM AccommodationDetails");

            ResultSet results = ps.executeQuery();

            while (results.next())
            {
                int TripID = results.getInt(1);
                int DayNumber = results.getInt(2);
                int ActivityID = results.getInt(3);
                String Time = results.getString(4);
                String Notes = results.getString(5);

                System.out.println(TripID + " " + DayNumber + " " + ActivityID + " " + Time + " " + Notes);
            }
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Adds a record to the TripActivities Table
    public static void insertTripActivities(int TripID, int DayNumber, int ActivityID, String Time, String Notes)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO ActivityDetails (TripID, DayNumber, ActivityID, Time, Notes) VALUES (?, ?, ?, ?, ?)");

            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, ActivityID);
            ps.setString(4, Time);
            ps.setString(5, Notes);
            ps.executeUpdate();

            System.out.println("Record added to ActivityDetails table");
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Changes a record within the TripActivities Table
    public static void updateTripActivites(int TripID, int DayNumber, int ActivityID, String Time, String  Notes)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("UPDATE ActivityDetails SET TripID = ?, DayNumber = ?, ActivityDetails = ?, Time = ?, Notes = ?");

            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, ActivityID);
            ps.setString(4, Time);
            ps.setString(5, Notes);

        }
        catch(Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Deletes a record within the TripActivitiesTable
    public static void deleteTripActivites(int TripID, int DayNumber, int ActivityID, String Time, String  Notes)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("DELETE FROM ActivityDetails WHERE TripID = ?, DayNumber = ?, ActivityID = ?, Time = ?");
            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, ActivityID);
            ps.setString(4, Time);
            ps.executeUpdate();

        }
        catch(Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}
package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class TripAccommodationController
{

    //Outputs the items in the TripAccommodation Table
    public static void listTripAccoommodation()
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT TripID, DayNumber, AccommodationID FROM AccommodationDetails");

            ResultSet results = ps.executeQuery();

            while (results.next()) //returns the next record until there are no more values in the column
            {
                int TripID = results.getInt(1);
                int DayNumber = results.getInt(2);
                int AccommodationID = results.getInt(3);

                System.out.println(TripID + " " + DayNumber + " " + AccommodationID);
            }
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }

    }


    //Adds a record to the TripAccommodation Table
    public static void insertTripAccommodation(int TripID, int DayNumber, int AccommodationID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO AccommodationDetails (TripID, DayNumber, AccommodationID) VALUES (?, ?, ?");

            //contain the values to be added through the SQL statement in place of the ?s
            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, AccommodationID);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

            System.out.println("Record added to AccommodationDetails table");
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }

    }


    //Changes a record within the TripAccommodationTable
    public static void updateTripAccommodation(int TripID, int DayNumber, int AccommodationID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("UPDATE AccommodationDetails SET TripID = ?, DayNumber = ?, AccommodationID = ?");

            //contain the values to be changed through the SQL statement in place of the ?s
            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, AccommodationID);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

        }
        catch(Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Deletes a record within the TripAccommodation Table
    public static void deleteTripAccommodation(int TripID, int DayNumber, int AccommodationID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("DELETE FROM AccommodationDetials WHERE TripID = ?, DayNumber = ?, AccommodationID = ?");

            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, AccommodationID);

            ps.executeUpdate();
        }
        catch(Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}

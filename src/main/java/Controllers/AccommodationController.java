package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class AccommodationController
{

    //Outputs the items in the Accommodation table
    public void listAccommodation()
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT AccommodationID, LocationID, AccommodationName, Description, Cost, Address, Website FROM Accommodation");

            ResultSet results = ps.executeQuery();

            while (results.next()) {
                int accommodationID = results.getInt(1);
                int locationID = results.getInt(2);
                String accommodationName = results.getString(3);
                String description = results.getString(4);
                String cost = results.getString(5);
                String address = results.getString(6);
                String website = results.getString(7);

                System.out.println(accommodationID + " " + locationID + " " + accommodationName + " " + description + " " + cost + " " + address + " " + website);
            }
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    //Adds a record to the Accommodation table
    public void insertAccommodation(int AccommodationID, int LocationID, String AccommodationName, String Description, String Cost, String Address, String Website)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Accommodation (AccommodationID, LocationID, AccommodationName, Description, Cost, Address, Website) VALUES (?, ?, ?, ?, ?, ?, ?");

            ps.setInt(1, AccommodationID);
            ps.setInt(2, LocationID);
            ps.setString(3, AccommodationName);
            ps.setString(4, Description);
            ps.setString(5, Cost);
            ps.setString(6, Address);
            ps.setString(7, Website);

            ps.executeUpdate();

            System.out.println("Record added to Accommodation");

        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    //Changes a record within the Accommodation table
        public static void updateAccommodation (int AccommodationID, int LocationID, String AccommodationName, String Description, String Cost, String Address, String Website)
        {
            try
            {
                PreparedStatement ps = db.prepareStatement("UPDATE Accommodation SET AccommodationID = ?, LocationID = ?, AccommodationName = ?, Description = ?, Cost = ?, Address = ?, Website = ?");

                ps.setInt(1, AccommodationID);
                ps.setInt(2, LocationID);
                ps.setString(3, AccommodationName);
                ps.setString(4, Description);
                ps.setString(5, Cost);
                ps.setString(6, Address);
                ps.setString(7, Website);

            }
            catch (Exception exception)
            {
                System.out.println("Database error: " + exception.getMessage());
            }
    }

    //Deletes a record within the Accommodation table
    public static void deleteAccommodation (int AccommodationID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("DELETE FROM Accommodation SET AccommodationID = ?");

            ps.setInt(1, AccommodationID);

            ps.executeUpdate();
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}
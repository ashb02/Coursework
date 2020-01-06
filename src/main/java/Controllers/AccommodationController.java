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

            while (results.next()) //returns the next record until there are no more values in the column
            {
                int AccommodationID = results.getInt(1);
                int LocationID = results.getInt(2);
                String AccommodationName = results.getString(3);
                String Description = results.getString(4);
                String Cost = results.getString(5);
                String Address = results.getString(6);
                String Website = results.getString(7);

                System.out.println(AccommodationID + " " + LocationID + " " + AccommodationName + " " + Description + " " + Cost + " " + Address + " " + Website);
            }
        }
        catch (Exception exception) //if an error occurs returns an error message
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

            //contain the values to be added through the SQL statement in place of the ?s
            ps.setInt(1, AccommodationID);
            ps.setInt(2, LocationID);
            ps.setString(3, AccommodationName);
            ps.setString(4, Description);
            ps.setString(5, Cost);
            ps.setString(6, Address);
            ps.setString(7, Website);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

            System.out.println("Record added to Accommodation");

        }
        catch (Exception exception) //if an error occurs returns an error message
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

                //contain the values to be changed through the SQL statement in place of the ?s
                ps.setInt(1, AccommodationID);
                ps.setInt(2, LocationID);
                ps.setString(3, AccommodationName);
                ps.setString(4, Description);
                ps.setString(5, Cost);
                ps.setString(6, Address);
                ps.setString(7, Website);

                ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

            }
            catch (Exception exception)//if an error occurs returns an error message
            {
                System.out.println("Database error: " + exception.getMessage());
            }
    }

    //Deletes a record within the Accommodation table
    public static void deleteAccommodation (int AccommodationID)
    {        try
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
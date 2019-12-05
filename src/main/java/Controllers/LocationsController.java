package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class LocationsController
{

    //Outputs the items in the Locations table
    public static void listLocations()
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT LocationID, LocationName, Cost, AverageTemperature");

            ResultSet results = ps.executeQuery();

            while (results.next())
            {
                int LocationID = results.getInt(1);
                String LocationName = results.getString(2);
                String Cost = results.getString(3);
                String AverageTemperature = results.getString(4);
                System.out.println(LocationID + " " + LocationName + " " + Cost + " " + AverageTemperature);
            }
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    //Adds a record to the Locations table
    public static void insertLocation(int LocationID, String LocationName, String Cost, String AverageTemperature)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Activities (LocationID, LocationName, Cost, AverageTemperature) VALUES (?, ?, ?, ?");
            ps.setInt(1, LocationID);
            ps.setString(2, LocationName);
            ps.setString(3, Cost);
            ps.setString(4, AverageTemperature);
            ps.executeUpdate();
            System.out.println("Record added to Locations");
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    //Updates a record in the Locations table
    public static void updateLocations(int LocationID, String LocationName, String Cost, String AverageTemperature)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("UPDATE LocationID = ?, LocationName = ?, Cost = ?, AverageTemperature = ?");
            ps.setInt(1, LocationID);
            ps.setString(2, LocationName);
            ps.setString(3, Cost);
            ps.setString(4, AverageTemperature);
        }
        catch(Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    //Deletes a record within the Location table
    public static void deleteLocation(int LocationID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("DELETE FROM Locations WHERE LocationID = ?");

            ps.setInt(1, LocationID);

            ps.executeUpdate();
        }
        catch(Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}

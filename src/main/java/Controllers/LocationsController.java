package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class LocationsController {

    public static void listLocations() {
        try {
            PreparedStatement ps = db.prepareStatement("SELECT LocationID, LocationName, Cost, AverageTemperature");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int LocationID = results.getInt(1);
                String LocationName = results.getString(2);
                String Cost = results.getString(3);
                String AverageTemperature = results.getString(4);
                System.out.println(LocationID + " " + LocationName + " " + Cost + " " + AverageTemperature);
            }
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void insertLocation(int LocationID, String LocationName, String Cost, String AverageTemperature) {
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Activities (LocationID, LocationName, Cost, AverageTemperature) VALUES (?, ?, ?, ?");
            ps.setInt(1, LocationID);
            ps.setString(2, LocationName);
            ps.setString(3, Cost);
            ps.setString(4, AverageTemperature);
            ps.executeUpdate();
            System.out.println("Record added to Locations");

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void updateLocations(int LocationID, String LocationName, String Cost, String AverageTemperature){
        try{
            PreparedStatement ps = db.prepareStatement("UPDATE LocationID = ?, LocationName = ?, Cost = ?, AverageTemperature = ?");
            ps.setInt(1, LocationID);
            ps.setString(2, LocationName);
            ps.setString(3, Cost);
            ps.setString(4, AverageTemperature);

        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void deleteActivtiy(int LocationID, String LocationName, String Cost, String AverageTemperature){
        try{
            PreparedStatement ps = db.prepareStatement("DELETE FROM Activities WHERE ActivityID = ?, LocationID = ?, ActivityName = ?");
            ps.setInt(1, LocationID);
            ps.setString(2, LocationName);
            ps.setString(3, Cost);
            ps.setString(4, AverageTemperature);
            ps.executeUpdate();

        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}

package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class AccommodationController {

    public void listAccommodation() {
        try {
            PreparedStatement ps = db.prepareStatement("SELECT AccommodationID, LocationID, AccommodationName, Description, Cost, Address, Website FROM Accommodation");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int AccommodationID = results.getInt(1);
                int LocationID = results.getInt(2);
                String AccommodationName = results.getString(3);
                String Description = results.getString(4);
                String Cost = results.getString(5);
                String Address = results.getString(6);
                String Website = results.getString(7);
                System.out.println(AccommodationID + " " + LocationID + " " + AccommodationName + " " + Description + " " + Cost + " " + Address + " " + Website);
            }
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public void insertAccommodation(int AccommodationID, int LocationID, String AccommodationName, String Description, String Cost, String Address, String Website) {
        try {
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

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

        public static void updateAccommodation (int AccommodationID, int LocationID, String AccommodationName, String Description, String Cost, String Address, String Website){
            try {
                PreparedStatement ps = db.prepareStatement("UPDATE Accommodation SET AccommodationID = ?, LocationID = ?, AccommodationName = ?, Description = ?, Cost = ?, Address = ?, Website = ?");
                ps.setInt(1, AccommodationID);
                ps.setInt(2, LocationID);
                ps.setString(3, AccommodationName);
                ps.setString(4, Description);
                ps.setString(5, Cost);
                ps.setString(6, Address);
                ps.setString(7, Website);

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }
    }

    public static void deleteAccommodation (int AccommodationID, int LocationID, String AccommodationName, String Description, String Cost, String Address, String Website){
        try {
            PreparedStatement ps = db.prepareStatement("DELETE FROM Accommodation SET AccommodationID = ?, LocationID = ?, AccommodationName = ?, Description = ?, Cost = ?, Address = ?, Website = ?");
            ps.setInt(1, AccommodationID);
            ps.setInt(2, LocationID);
            ps.setString(3, AccommodationName);
            ps.setString(4, Description);
            ps.setString(5, Cost);
            ps.setString(6, Address);
            ps.setString(7, Website);
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}
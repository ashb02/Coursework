package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class TripAccommodationController {

    public static void listTripAccoommodation() {
        try {
            PreparedStatement ps = db.prepareStatement("SELECT TripID, DayNumber, AccommodationID FROM AccommodationDetails");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int TripID = results.getInt(1);
                int DayNumber = results.getInt(2);
                int AccommodationID = results.getInt(3);
                System.out.println(TripID + " " + DayNumber + " " + AccommodationID);
            }
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }

    public static void insertTripAccommodation(int TripID, int DayNumber, int AccommodationID){
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO AccommodationDetails (TripID, DayNumber, AccommodationID) VALUES (?, ?, ?");
            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, AccommodationID);
            ps.executeUpdate();
            System.out.println("Record added to AccommodationDetails table");

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }

    public static void updateTripAccommodation(int TripID, int DayNumber, int AccommodationID){
        try{
            PreparedStatement ps = db.prepareStatement("UPDATE AccommodationDetails SET TripID = ?, DayNumber = ?, AccommodationID = ?");
            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, AccommodationID);

        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void deleteTripAccommodation(int TripID, int DayNumber, int AccommodationID){
        try{
            PreparedStatement ps = db.prepareStatement("DELETE FROM AccommodationDetials WHERE TripID = ?, DayNumber = ?, AccommodationID = ?");
            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, AccommodationID);
            ps.executeUpdate();

        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }


}

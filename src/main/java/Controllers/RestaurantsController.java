package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class RestaurantsController {

    public static void listRestaurants() {
        try {
            PreparedStatement ps = db.prepareStatement("SELECT RestaurantID, LocationID, RestaurantName, Description, Cost, Address, Website FROM Restaurants");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int TripID = results.getInt(1);
                int DayNumber = results.getInt(2);
                int ActivityID = results.getInt(3);
                String Time = results.getString(4);
                String Notes = results.getString(5);
                System.out.println(TripID + " " + DayNumber + " " + ActivityID + " " + Time + " " + Notes);
            }
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void insertRestaurant(int RestaurantID, int LocationID, String RestaurantName, String Description, String Cost, String Address, String Website) {
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Restaurants (RestaurantID, LocationID, RestaurantName, Description, Cost, Address, Website) VALUES (?, ?, ?, ?, ?, ?, ?); ");
            ps.setInt(1, RestaurantID);
            ps.setInt(2, LocationID);
            ps.setString(3, RestaurantName);
            ps.setString(4, Description);
            ps.setString(5, Cost);
            ps.setString(6, Address);
            ps.setString(7, Website);
            ps.executeUpdate();
            System.out.println("Record added to Restaurants table");

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void updateRestaurant(int RestaurantID, int LocationID, String RestaurantName, String Description, String Cost, String Address, String Website){
        try{
            PreparedStatement ps = db.prepareStatement("UPDATE Restaurant SET RestaurantID = ?, LocationID = ?, RestaurantName = ?, Description = ?, Cost = ?, Address = ?, Website = ?");
            ps.setInt(1, RestaurantID);
            ps.setInt(2, LocationID);
            ps.setString(3, RestaurantName);
            ps.setString(4, Description);
            ps.setString(5, Cost);
            ps.setString(6, Address);
            ps.setString(7, Website);

        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }

    public static void deleteRestaurant(int RestaurantID, int LocationID, String RestaurantName, String Description, String Cost, String Address, String Website){
        try{
            PreparedStatement ps = db.prepareStatement("DELETE FROM Restaurants WHERE RestaurantID = ?, LocationID = ?, RestaurantName = ?, Description = ?, Cost = ?, Address = ?, Website = ?");
            ps.setInt(1, RestaurantID);
            ps.setInt(2, LocationID);
            ps.setString(3, RestaurantName);
            ps.setString(4, Description);
            ps.setString(5, Cost);
            ps.setString(6, Address);
            ps.setString(7, Website);
            ps.executeUpdate();

        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}

package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class ActivitiesController {

        public static void listActivities() {
            try {
                PreparedStatement ps = db.prepareStatement("SELECT ActivityID, LocationID, ActivityName, Description, Cost, Address, Website");

                ResultSet results = ps.executeQuery();
                while (results.next()) {
                    int ActivityID = results.getInt(1);
                    int LocationID = results.getInt(2);
                    String ActivityName = results.getString(3);
                    String Description = results.getString(4);
                    String Cost = results.getString(5);
                    String Address = results.getString(6);
                    String Website = results.getString(7);
                    System.out.println(ActivityID + " " + LocationID + " " + ActivityID + " " + Description + " " + Cost + " " + Address + " " + Website);
                }
            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }
        }

        public static void insertActivities(int ActivityID, int LocationID, String ActivityName, String Description, String Cost, String Address, String Website) {
            try {
                PreparedStatement ps = db.prepareStatement("INSERT INTO Activities (ActivityID, LocationID, ActivityName, Description, Cost, Address, Website) VALUES (?, ?, ?, ?, ?, ?, ?");
                ps.setInt(1, ActivityID);
                ps.setInt(2, LocationID);
                ps.setString(3, ActivityName);
                ps.setString(4, Description);
                ps.setString(5, Cost);
                ps.setString(6, Address);
                ps.setString(7, Website);
                ps.executeUpdate();
                System.out.println("Record added to Activities");

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }
        }

        public static void updateActivities(int ActivityID, int LocationID, String ActivityName, String Description, String Cost, String Address, String Website){
            try{
                PreparedStatement ps = db.prepareStatement("UPDATE Activities SET ActivityID = ?, LocationID = ?, ActivityName = ?, Description = ?, Cost = ?, Address = ?, Website = ?");
                ps.setInt(1, ActivityID);
                ps.setInt(2, LocationID);
                ps.setString(3, ActivityName);
                ps.setString(4, Description);
                ps.setString(5, Cost);
                ps.setString(6, Address);
                ps.setString(7, Website);

            } catch(Exception exception){
                System.out.println("Database error: " + exception.getMessage());
            }
        }

        public static void deleteActivtiy(int ActivityID, int LocationID, String ActivityName, String Description, String Cost, String Address, String Website){
            try{
                PreparedStatement ps = db.prepareStatement("DELETE FROM Activities SET ActivityID = ?, LocationID = ?, ActivityName = ?, Description = ?, Cost = ?, Address = ?, Website = ?");
                ps.setInt(1, ActivityID);
                ps.setInt(2, LocationID);
                ps.setString(3, ActivityName);
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
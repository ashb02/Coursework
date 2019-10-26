package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ActivityController {
    public static void listActivity(){
        try{
            PreparedStatement ps = db.prepareStatement("SELECT ActivityID, LocationID, ActivityName, Description, Cost, Address, Website FROM Activity");

            ResultSet results = ps.executeQuery();
            while(results.next()){
                int ActivityID = results.getInt(1);
                int LocationID = results.getInt(2);
                String ActivityName = results.getString(3);
                String Description = results.getString(4);
                String Cost = results.getString(5);
                String Address = results.getString(6);
                String Website = results.getString(7);
                System.out.println(ActivityID + " " + LocationID + " " + ActivityName + " " + Description + " " + Cost + " " + Address + " " + Website);
            }
        } catch(Exception exception){
            System.out.println("Database error: " + exception.getMessage());
        }


        public static void insertActivity(int ActivityID, int LocationID, String ActivityName, String Description, String Cost, String Address, String Website){
            try{
                PreparedStatement ps = db.prepareStatement("INSERT INTO Activities (ActivtiyID, LocationID, ActivityName, Description, Cost, Address, Website) VALUES (?, ?, ?, ?, ?, ?, ?");
                ps.setInt(1, ActivityID);
                ps.setInt(2, LocationID);
                ps.setString(3, ActivtiyName);
                ps.setString(4, Description);
                ps.setString(5, Cost);
                ps.setString(6, Address);
                ps.setString(7, Website);
                ps.executeUpdate();
                System.out.println("Record added to Activities");

            } catch(Exception exception){
                System.out.println("Database error: " + exception.getMessage());
            }


            public static void updateActivity(int ActivityID, int LocationID, String ActivityName, String Description, String Cost, String Address, String Website){
                try{
                    PreparedStatement ps = db.prepareStatement("UPDATE Activties SET ActivityID = ?, LocationID = ?, ActivityName = ?, Description = ?, Cost = ?, Address = ?, Website = ?");
                    ps.setInt(1, ActivityID);
                    ps.setInt(2, LocationID);
                    ps.setString(3, ActivityName);
                    ps.setString(4, Description);
                    ps.setString(5, Cost);
                    ps.setString(6, Address);
                    ps.setString(7, website);

                } catch(Exception exception){
                    System.out.println("Database error: " + exception.getMessage());
                }
            }


            public static void deleteActivity(int ActivityID, int LocationID, String ActivityName,){
                try{
                    PreparedStatement ps = db.prepareStatement("DELETE FROM Accommodation WHERE AccommodationID = ?");
                    ps.setInt(1, AccommodationID);
                    ps.executeUpdate();

                } catch(Exception exception){
                    System.out.println("Database error: " + exception.getMessage());
                }
            }
}

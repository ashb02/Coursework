package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

public class ActivitiesController
{

    //Outputs the items in the Actvities table
        public static void listActivities()
        {
            try
            {
                PreparedStatement ps = db.prepareStatement("SELECT ActivityID, LocationID, ActivityName, Description, Cost, Address, Website");

                ResultSet results = ps.executeQuery();

                while (results.next()) //returns the next record until there are no more values in the column
                {
                    int ActivityID = results.getInt(1);
                    int LocationID = results.getInt(2);
                    String ActivityName = results.getString(3);
                    String Description = results.getString(4);
                    String Cost = results.getString(5);
                    String Address = results.getString(6);
                    String Website = results.getString(7);

                    System.out.println(ActivityID + " " + LocationID + " " + ActivityName + " " + Description + " " + Cost + " " + Address + " " + Website);
                }
            }
            catch (Exception exception) //if an error occurs returns an error message
            {
                System.out.println("Database error: " + exception.getMessage());
            }
        }

    //Adds a record to the Activities table
        public static void insertActivities(int ActivityID, int LocationID, String ActivityName, String Description, String Cost, String Address, String Website)
        {
            try
            {
                PreparedStatement ps = db.prepareStatement("INSERT INTO Activities (ActivityID, LocationID, ActivityName, Description, Cost, Address, Website) VALUES (?, ?, ?, ?, ?, ?, ?");

                //contain the values to be added through the SQL statement in place of the ?s
                ps.setInt(1, ActivityID);
                ps.setInt(2, LocationID);
                ps.setString(3, ActivityName);
                ps.setString(4, Description);
                ps.setString(5, Cost);
                ps.setString(6, Address);
                ps.setString(7, Website);

                ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

                System.out.println("Record added to Activities");
            }
            catch (Exception exception) //if an error occurs returns an error message
            {
                System.out.println("Database error: " + exception.getMessage());
            }
        }

    //Changes a record within the Activities table
        public static void updateActivities(int ActivityID, int LocationID, String ActivityName, String Description, String Cost, String Address, String Website)
        {
            try
            {
                PreparedStatement ps = db.prepareStatement("UPDATE Activities SET ActivityID = ?, LocationID = ?, ActivityName = ?, Description = ?, Cost = ?, Address = ?, Website = ?");

                //contain the values to be changed through the SQL statement in place of the ?s
                ps.setInt(1, ActivityID);
                ps.setInt(2, LocationID);
                ps.setString(3, ActivityName);
                ps.setString(4, Description);
                ps.setString(5, Cost);
                ps.setString(6, Address);
                ps.setString(7, Website);

                ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

            }
            catch(Exception exception) //if an error occurs returns an error message
            {
                System.out.println("Database error: " + exception.getMessage());
            }
        }

    //Deletes a record from the Activities table
        public static void deleteActivity(int ActivityID)
        {
            try
            {
                PreparedStatement ps = db.prepareStatement("DELETE FROM Activities SET ActivityID = ?");

                ps.setInt(1, ActivityID);

                ps.executeUpdate();
            }
            catch(Exception exception)
            {
                System.out.println("Database error: " + exception.getMessage());
            }
        }

    }
package Controllers;



import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;

import static Server.Main.db;

@Path("TripAccommodation/")
public class TripAccommodationController
{

    //Outputs the items in the TripAccommodation Table
    // curl -s localhost:8081/TripAccommodation/listTripAccommodation
    @GET
    @Path("listTripAccommodation")
    @Produces(MediaType.APPLICATION_JSON)
    public static String listTripAccoommodation()
    {
        System.out.println("TripAccommodation/listTripAccommodation");
        JSONArray list = new JSONArray();
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT TripID, DayNumber, AccommodationID FROM TripAccommodation");

            ResultSet results = ps.executeQuery();

            while (results.next()) //returns the next record until there are no more values in the column
            {
                JSONObject item = new JSONObject();
                item.put("TripId", results.getInt(1));
                item.put("DayNumber", results.getInt(2));
                item.put("AccommodationID", results.getInt(3));
                list.add(item);
                //int TripID = results.getInt(1);
                //int DayNumber = results.getInt(2);
                //int AccommodationID = results.getInt(3);

                //System.out.println(TripID + " " + DayNumber + " " + AccommodationID);
            }
            return list.toString();
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }

    }


    //Adds a record to the TripAccommodation Table
    @POST
    @Path("insertTripAccommodation")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    //curl -s localhost:8081/TripAccommodation/insertTripAccommodation -F TripID=2 -F DayNumber=2 -F AccommodationID=2
    //{"status": "OK"}
    public static String insertTripAccommodation(@FormDataParam("TripID") Integer TripID, @FormDataParam("DayNumber") Integer DayNumber, @FormDataParam("AccommodationID") Integer AccommodationID)
    {
        System.out.println("TripAccommodation/insertTripAccommodation");
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO TripAccommodation (TripID, DayNumber, AccommodationID) VALUES (?, ?, ?)");

            //contain the values to be added through the SQL statement in place of the ?s
            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, AccommodationID);

            ps.execute(); //executes the SQL statement in the PreparedStatement

            System.out.println("Record added to AccommodationDetails table");
            return "{\"status\": \"OK\"}";
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }

    }


    //Changes a record within the TripAccommodationTable
    public static void updateTripAccommodation(int TripID, int DayNumber, int AccommodationID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("UPDATE AccommodationDetails SET TripID = ?, DayNumber = ?, AccommodationID = ?");

            //contain the values to be changed through the SQL statement in place of the ?s
            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, AccommodationID);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

        }
        catch(Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Deletes a record within the TripAccommodation Table
    public static void deleteTripAccommodation(int TripID, int DayNumber, int AccommodationID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("DELETE FROM AccommodationDetials WHERE TripID = ?, DayNumber = ?, AccommodationID = ?");

            ps.setInt(1, TripID);
            ps.setInt(2, DayNumber);
            ps.setInt(3, AccommodationID);

            ps.executeUpdate();
        }
        catch(Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}

package Controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

@Path("Locations/")
public class LocationsController
{

    //Outputs the items in the Locations table
    @GET
    @Path("viewAll")
    @Produces(MediaType.APPLICATION_JSON)
    //Locations/viewAll
    //curl -s localhost:8081/Locations/viewAll
    public String listLocations()
    {
        System.out.println("Locations/viewAll");
        JSONArray list = new JSONArray();
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT LocationID, LocationName, Country, Cost, AverageTemperature");
            ResultSet results = ps.executeQuery();

            while (results.next()) //returns the next record until there are no more values in the column
            {
                JSONObject item = newJSONObject();
                item.put("LocationID", results.getInt(1));
                item.put("LocationName", results.getString(2));
                item.put("Country", results.getString(3));
                item.put("Cost", results.getString(4));
                item.put("AverageTemperature", results.getString(5));
                list.add(item);
            }
            return list.toString();
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }

    @GET
    @Path("searchCountry")
    @Produces(MediaType.APPLICATION_JSON)
    //Locations/searchCountry
    //curl -s localhost:8081/Locations/searchCountry -F Country=?
    public String listCountry(@FormDataParam("Country") String Country) throws Exception {
        if (Country == null){
            throw new Exception("Country is missing in the HTTP request");
        }
        System.out.println("Locations/searchCountry" + Country);
        JSONArray list = new JSONArray();
        try
        {
            PreparedStatement ps = db.prepareStatement("SELECT LocationID, LocationName, Country, Cost, AverageTemperature WHERE Country =?");
            ResultSet results = ps.executeQuery();

            while (results.next()) //returns the next record until there are no more values in the column
            {
                JSONObject item = newJSONObject();
                item.put("LocationID", results.getInt(1));
                item.put("LocationName", results.getString(2));
                item.put("Country", results.getString(3));
                item.put("Cost", results.getString(4));
                item.put("AverageTemperature", results.getString(5));
                list.add(item);
            }
            return list.toString();
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
        }
    }

    //Adds a record to the Locations table
    public static void insertLocation(int LocationID, String LocationName, String Cost, String AverageTemperature)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Activities (LocationID, LocationName, Cost, AverageTemperature) VALUES (?, ?, ?, ?");

            //contain the values to be added through the SQL statement in place of the ?s
            ps.setInt(1, LocationID);
            ps.setString(2, LocationName);
            ps.setString(3, Cost);
            ps.setString(4, AverageTemperature);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

            System.out.println("Record added to Locations");
        }
        catch (Exception exception) //if an error occurs returns an error message
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

            //contain the values to be changed through the SQL statement in place of the ?s
            ps.setInt(1, LocationID);
            ps.setString(2, LocationName);
            ps.setString(3, Cost);
            ps.setString(4, AverageTemperature);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

        }
        catch(Exception exception) //if an error occurs returns an error message
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

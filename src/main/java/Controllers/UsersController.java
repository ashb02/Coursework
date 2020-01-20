package Controllers;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Server.Main.db;

@Path("Users/")
public class UsersController
{

    //Outputs items from the Users Table
    @GET
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static void listUser(@FormDataParam("EmailAddress") String EmailAddress, @FormDataParam("Password") String Password) throws Exception {
        if (EmailAddress == null || Password == null){
            throw new Exception("User's EmailAddress or Password is missing in the HTTP request's URL.");
        }

        System.out.println("Users/login/" + EmailAddress + " " + Password);
        JSONObject item = new JSONObject();

        try {
            PreparedStatement ps = db.prepareStatement("SELECT UserID, EmailAddress, FirstName, Password FROM Users WHERE EmailAddress = ? AND Password = ?");

            ResultSet results = ps.executeQuery();

            while(results.next()) //returns the next record until there are no more values in the column
            {
                int UserID = results.getInt(1);
                String EmailAddress =  results.getString(2);
                String FirstName = results.getString(3);
                String Password = results.getString(4);

                System.out.println(UserID + " " + EmailAddress + " " + FirstName + " " + Password);
            }
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Adds a record to the User Table
    public static void insertUser(int UserID, String EmailAddress, String FirstName, String Password)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("INSERT INTO Users (UserID, EmailAddress, FirstName, Password) VALUES (?, ?, ?, ?");

            //contain the values to be added through the SQL statement in place of the ?s
            ps.setInt(1, UserID);
            ps.setString(2, EmailAddress);
            ps.setString(3, FirstName);
            ps.setString(4, Password);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

            System.out.println("Record added to Users table");
        }
        catch(Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Changes a record wihtin the Users Table
    public static void UpdateUser(int UserID, String EmailAddress, String FirstName, String Password)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("UPDATE Users SET UserID = ?, EmailAddress = ?, FirstName = ?, Password = ?");

            //contain the values to be changed through the SQL statement in place of the ?s
            ps.setInt(1, UserID);
            ps.setString(2, EmailAddress);
            ps.setString(3, FirstName);
            ps.setString(4, Password);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement
        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }


    //Deletes a record within the User Table
    public static void deleteUser(int UserID)
    {
        try
        {
            PreparedStatement ps = db.prepareStatement("DELETE FROM Users WHERE UserID = ?");

            ps.setInt(1, UserID);

            ps.executeUpdate();
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

}
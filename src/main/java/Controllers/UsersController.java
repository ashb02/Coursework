package Controllers;

import Server.Main;
import org.eclipse.jetty.server.Authentication;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import static Server.Main.db;

@Path("Users/")
public class UsersController
{

    //Outputs items from the Users Table
    @GET
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // curl -s localhost8081/Users/login -F EmailAddress=? -F Password=?
    public String loginUser(@FormDataParam("EmailAddress") String EmailAddress, @FormDataParam("Password") String Password) throws Exception {

        try {

            PreparedStatement ps = db.prepareStatement("SELECT Password FROM Users WHERE EmailAddress = ? AND Password = ?");
            ps.setString(1, EmailAddress);
            ResultSet loginResults = ps.executeQuery();

            if (loginResults.next()){

                String correctPassword = loginResults.getString(1);

                if (Password.equals(correctPassword)) {

                    String token = UUID.randomUUID().toString();

                    PreparedStatement ps1 = Main.db.prepareStatement("UPDATE Users SET Token = ? WHERE EmailAdress = ?");
                    ps1.setString(1, token);
                    ps1.setString(2, EmailAddress);
                    ps1.executeUpdate();

                    return "{\"token\": \""+ token + "\"}";

                } else {
                    return "{\"error\": \"Incorrect password.\"}";
                }
            } else {

                return "{\"error\": \"Unknown user\"}";

            }

        } catch (Exception exception) { //if an error occurs returns an error message
            System.out.println("Database error during /User/login: " + exception.getMessage());
            return "{\"error\": \"Server side error.\"}";
        }
    }


    //Adds a record to the User Table
    @POST
    @Path("new")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertUser(@FormDataParam("UserID") Integer UserID, @FormDataParam("EmailAddress") String EmailAddress, @FormDataParam("FirstName") String FirstName, @FormDataParam("Password") String Password)
    {
        try
        {
            if (UserID == null || EmailAddress == null) {
                throw new Exception("One or more form data paraeters are missing from the HTTP request.");
            }

            System.out.println("User/createAccount=" + UserID);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (UserID, EmailAddress, FirstName, Password) VALUES (?, ?, ?, ?)");

            //contain the values to be added through the SQL statement in place of the ?s
            ps.setInt(1, UserID);
            ps.setString(2, EmailAddress);
            ps.setString(3, FirstName);
            ps.setString(4, Password);

            ps.executeUpdate(); //executes the SQL statement in the PreparedStatement

            return "{\"status"\": \"OK"}"}";
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
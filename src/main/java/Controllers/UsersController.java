package Controllers;

import Server.Main;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import static Server.Main.db;

@Path("Users/")
public class UsersController
{

    //Allows a user to login to their account
    @Get
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //Users/login
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
    @Path("createAccount")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    //Users/createAccount
    //curl -s localhost8081/Users/createAccount -F EmailAddress=? -F FirstName=? -F Password=?
    public String insertUser(@FormDataParam("UserID") Integer UserID, @FormDataParam("EmailAddress") String EmailAddress, @FormDataParam("FirstName") String FirstName, @FormDataParam("Password") String Password) throws Exception {
        try {
            if (UserID == null || EmailAddress == null) {
                throw new Exception("One or more form data parameters are missing from the HTTP request.");
            }

            System.out.println("User/createAccount=" + UserID);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (UserID, EmailAddress, FirstName, Password) VALUES (?, ?, ?, ?)");

            //contain the values to be added through the SQL statement in place of the ?s
            ps.setInt(1, UserID);
            ps.setString(2, EmailAddress);
            ps.setString(3, FirstName);
            ps.setString(4, Password);
            ps.execute(); //executes the SQL statement in the PreparedStatement

            return "{\"status\": \"OK\"}";
        } catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new account, please see server console for more info.\"}";
        }
    }


    //Changes a record within the Users Table
    @Post
    @Path("updateAccount")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    //Users/updateAccount
    //curl -s localhost8081/Users/updateAccount  -F EmailAddress=? -F FirstName=? -F Password=?
    public String UpdateUser(@FormDataParam("UserID") Integer UserID, @FormDataParam("EmailAddress") String EmailAddress, @FormDataParam("FirstName") String FirstName, @FormDataParam("Password") String Password)
    {
        try
        {
            if (EmailAddress == null || FirstName == null || Password == null){
                throw new Exception("One or more form data parameters are missing in the HTTP request");
            }
            System.out.println("users/updateAccount" + EmailAddress);

            PreparedStatement ps = db.prepareStatement("UPDATE Users SET EmailAddress = ?, FirstName = ?, Password = ? WHERE UserID =?");

            //contain the values to be changed through the SQL statement in place of the ?s
            ps.setString(2, EmailAddress);
            ps.setString(3, FirstName);
            ps.setString(4, Password);
            ps.execute(); //executes the SQL statement in the PreparedStatement
            return "{\"status\": \"OK\"}";

        }
        catch (Exception exception) //if an error occurs returns an error message
        {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        }
    }


    //Deletes a record within the User Table
    @Post
    @Path("deleteAccount")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    //Users/deleteAccount
    //curl -s localhost8081/Users/deleteAccount -F EmailAddress=? -F Password=?
    public String deleteUser(@FormDataParam("EmailAddress") String EmailAddress, @FormDataParam("Password") String Password)
    {
        try
        {
            if (EmailAddress == null || Password == null){
                throw new Exception("One or more form data parameters is missing in the HTTP request");
            }
            System.out.println("Users/deleteAccount" + EmailAddress);
            PreparedStatement ps = db.prepareStatement("DELETE FROM Users WHERE EmailAddress = ? AND Password = ?");

            ps.setString(1, EmailAddress);
            ps.setString(2, Password);
            ps.execute();

            return "{\"status\": \"OK\"}";
        }
        catch (Exception exception)
        {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

}
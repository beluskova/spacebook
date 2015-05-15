package controllers;

import play.*;

import java.util.*;

import models.*;
import play.db.jpa.Blob;
import play.mvc.*;

public class Accounts extends Controller 
{
	  public static void signup()
	  {
	    render();
	  }

	  public static void login()
	  {
	    
	    render();
	  }

	  public static void logout()
	  {
		session.clear();
	    index();
	  }

	  public static void index()
	  {
	    render();
	  }

	  public static void edit()
	  {
		  String userId = session.get("logged_in_userid");
	      User me = User.findById(Long.parseLong(userId));
	      if (me != null)
	      {
	       render(me);
	      Logger.info("Editing following" + userId);
		   }
	  }
	  
	  public static void save(String firstName, String lastName, String email,  int age, String nationality)
	  {
		 String userId = session.get("logged_in_userid");
	     User me = User.findById(Long.parseLong(userId));
	      if (me != null)
	      {
	    	     Logger.info("Editing following" +me.firstName + " " + me.lastName + " " + me.email + " " + me.age + " " +me.nationality );
		         me.firstName = firstName;
	    	     me.lastName = lastName;
		         me.email = email;
		         me.age = age;
		         me.nationality = nationality;
		         me.save();
	             Home.index();
	      }
	   }
	  
  
	
	  public static void register(String firstName, String lastName, String email, String password, int age, String nationality)
	  {
		    Logger.info(firstName + " " + lastName + " " + email + " " + password + " " + age + " " +nationality);
		    
		    User user = new User (firstName, lastName, email, password, age, nationality);
		    user.save();

		    index();
	  }

	  public static void authenticate(String email, String password)
	  {
		Logger.info("Attempting to authenticate with " +email+ " " + password);  

	    User user = User.findByEmail(email);
	    if ((user != null) && (user.checkPassword(password) == true))
	    {
	      Logger.info("Authentication successful");
	      session.put("logged_in_userid", user.id);
	      Home.index();
	    }
	    else
	    {
	      Logger.info("Authentication failed");
	      login();  
	    }
		
	  }

  
}
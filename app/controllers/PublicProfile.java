package controllers;

import play.*;

import java.util.*;

import models.User;
import play.mvc.*;

public class PublicProfile extends Controller {

    public static void index() 
    {
    	        String userId = session.get("logged_in_userid");
    	        if (userId != null)
    	        {
    	        User user = User.findById(Long.parseLong(userId));
    	        render(user);
    	        }
    	        else
    	        {
    	        	Accounts.login();
    	        }
    }

    
 
 public static void visit(Long id)
    {
	    User user = User.findById(id);
	    Logger.info("Just visiting the page for " + user.firstName + ' ' + user.lastName );
	    Logger.info("Just visiting the page for " + id);
        render(user);
    }  
    
 public static void sendMessage(Long id, String subject,  String messageText)
 {
   String userId = session.get("logged_in_userid");
   User fromUser = User.findById(Long.parseLong(userId));
   User toUser = User.findById(id);
   
   Logger.info("Message from user ID " +
   fromUser.firstName + " " + fromUser.lastName +" to: "+
   toUser.firstName + ' ' + toUser.lastName +": " + 
   "subject: " + subject +
   messageText);
   
   fromUser.sendMessage(toUser, subject, messageText);
   visit (id);
 }
  }
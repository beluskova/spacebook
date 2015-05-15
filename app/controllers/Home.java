package controllers;

import play.*;

import java.util.*;

import models.*;
import play.mvc.*;
import utils.MessageFromComparator;
//import views.Home;

public class Home extends Controller {

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
        	Accounts.index();       	
        }
    }
    
    public static void drop(Long id)
    {
      String userId = session.get("logged_in_userid");
      User user = User.findById(Long.parseLong(userId));

      User friend = User.findById(id);

      user.unfriend(friend);
      Logger.info("Dropping " + friend.email);
      index();
    }  
    
    public static void follow(Long id)
    {
      User friend = User.findById(id);

      String userId = session.get("logged_in_userid");
      User me = User.findById(Long.parseLong(userId));
      Logger.info("Following " + friend.firstName);
      me.befriend(friend);

      Home.index();
    }
    
    public static void byUser()
    {
    	String userId = session.get("logged_in_userid");
        User user = User.findById(Long.parseLong(userId));
        Collections.sort (user.inbox , new MessageFromComparator() );
        user.save();
        Home.index();
    }
    
    
}
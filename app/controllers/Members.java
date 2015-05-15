package controllers;

import play.*;

import java.util.*;

import models.User;
import play.mvc.*;

public class Members extends Controller {

    public static void index() 
    {
        String userId = session.get("logged_in_userid");
        if (userId != null)
        {
        User me = User.findById(Long.parseLong(userId));
        List<User> users = User.findAll();
        for (int i = 0; i < users.size(); i++) {
			users.remove(me);   
			render(users);
        }
        }
        else
        {
        	Accounts.index();
        }
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

}
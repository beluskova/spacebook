package controllers;
/**
* @file    LeaderBoard.java
* @brief   Class publishes users graded most socially and talkatively active
* @version 2015-4-22
* @author  
*/
import play.*;

import java.util.*;

import play.db.jpa.Blob;
import models.*;
import play.mvc.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.UserLeastTalkativeComparator;
import utils.UserSocialComparator;
import utils.UserTalkativeComparator;



public class Leaderboard extends Controller
{
  public static void index() 
	    { 
	        List<User> users = User.findAll();
	        for (int i = 0; i < users.size(); i++)
	        {
				render(users);
	        }
      }
  
  public static void talkative(ArrayList<User> users)
  {
   // Collections.sort(users, new UserTalkativeComparator());
    Leaderboard.index();
  }
  
  public static void leastTalkative(ArrayList<User> users)
  { 
 //  Collections.sort(users, new UserLeastTalkativeComparator());
   Leaderboard.index();
  }

  public static void mostSocial (ArrayList<User> users)
  {
	    Collections.sort(users, new UserSocialComparator());
	    Leaderboard.index();
 }
}

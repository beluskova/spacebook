package controllers;

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

  /**
   * Method index displays the views/Leaderboard/index page whenever the
   * Leaderboard button is hit in the navigation panel on top of (nearly) each
   * page. This is only done when the users is logged in so in the beginning of
   * the method this is checked first (by looking for the session created when
   * the users logs in). Then a new arrayList is created and users found are
   * added into this list. The list of users is then displayed on the page, also
   * the amount of messages in their inbox and outbox (size of the inbox/outbox)
   * and numbers of friends (size of their friendships). The list of users is
   * displayed using a numeric order (this is achieved by using a semantic style
   * ui ordered list).
   */

  public static void index()
  {
    String userId = session.get("logged_in_userid");
    if (userId != null)
    {
      List<User> users = User.findAll();
      for (int i = 0; i < users.size(); i++)
      {
        render(users);
      }
    }
    else
    {
      Accounts.index();
    }
  }

  /**
   * Method talkative is initialized by hitting the "MOST TALKATIVE" button on
   * the Leaderboard page. It creates the arraylist of all users first (when
   * they are found by findAll method) and then it loops through this list
   * (using a for loop) and while looping through it, users are sorted by size
   * of their outbox, the biggest outbox (by the size/amount of messages) first.
   * This is how the most talkative person is defined. In order to sort out the
   * users in the required way the Collections class (sort method) with a help
   * of a UserTalkativeComparator() class is initialized (within the for loop).
   * The Leaderboard page is then refreshed (using render method), displaying
   * the users by this required order (by Most Talkative user first).
   * 
   */
  public static void talkative()
  {
    List<User> users = User.findAll();
    // to avoid the Null Pointer Exception Errors:
    if (users != null)
    {
      for (int i = 0; i < users.size(); i++)
      {
        Collections.sort(users, new UserTalkativeComparator());
      }
    }
    render("Leaderboard/index.html", users);
  }

  /**
   * Method mostSocial is initialized by hitting the "MOST SOCIAL" button on the
   * Leaderboard page. It creates the arraylist of all users first (when they
   * are found by findAll method) and then it loops through this list (using a
   * for loop) and while looping through it, users are sorted by size of their
   * friendships, the biggest friendship(by the size/amount of friends) first.
   * This is how the most social person is defined. In order to sort out the
   * users in the required way the Collections class (sort method) with a help
   * of a UserSocialComparator() class is initialized (within the for loop). The
   * Leaderboard page is then refreshed (using render method), displaying the
   * users by this required order (by Most Talkative user first).
   * 
   */
  public static void mostSocial()
  {
    List<User> users = User.findAll();
    // to avoid the Null Pointer Exception Errors:
    if (users != null)
    {
      for (int i = 0; i < users.size(); i++)
      {
        Collections.sort(users, new UserSocialComparator());
      }
    }
    render("Leaderboard/index.html", users);
  }

}

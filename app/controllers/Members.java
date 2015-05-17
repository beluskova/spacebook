package controllers;

import play.*;

import java.util.*;

import models.Friendship;
import models.User;
import play.mvc.*;

public class Members extends Controller
{

/**  Method index displays the views/Members/index page whenever the
  *  Members button is hit in the navigation panel on top of (nearly) each
  *  page. This is only done when the users is logged in so in the beginning of
  *  the method this is checked first (by looking for the session created when
  *  the users logs in). Then a new arrayList is created and users found are
  *  added into this list. The list of users is then displayed on the page, however
  *  method remove will take the logged in user off the list as this is the page with
  *  the main purpose of displaying all other users apart from the one that is logged in,
  *  so these users then can be added into the user's friendships list.
  */
  public static void index()
  {
    String userId = session.get("logged_in_userid");
    if (userId != null)
    {
      User me = User.findById(Long.parseLong(userId));
      List<User> users = User.findAll();
      users.remove(me);
      render(users);
    }
    else
    {
      Accounts.index();
    }
  }

/** Method follow is initialized when the user hits the "Follow" button, which
 *  is placed beside every user's name on the Members page. With the help of 
 *  a befriend method (which is introduced in the User model) the user is then added
 *  into the user's friends' list (into user's array list friendships). In order to avoid
 *  adding a user that already is a friend, the method goes through the list (using a for loop) of user's friend
 *  first (through the arraylist called friendships assigned to the user by adding me. before the name)
 *  and checking if the chosen user is not in the list of his friends. If so, then the user is redirected to his
 *  home page. If not, then the user identified by his id (which is assigned to him in the database when he signs up)
 *  is added to the logged-in user's friends' list. 
 * 
 * @param id unique id number of type long by which the user's friend is identified
 */
  public static void follow(Long id)
  {
    User friend = User.findById(id);
    String userId = session.get("logged_in_userid");
    User me = User.findById(Long.parseLong(userId));
    for (int i = 0; i < me.friendships.size(); i++)
    {
      if (me.friendships.get(i).targetUser == friend)
      {
        Logger.info("Trying to befriend " + friend.firstName + " who is already " + me.firstName + "'s friend");
        Home.index();
        break;
      }
    }
    me.befriend(friend);
    Logger.info("Following " + friend.firstName);
    me.save();
    Home.index();
  }
}
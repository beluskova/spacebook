package controllers;

import play.*;

import java.util.*;

import models.*;
import play.mvc.*;
import play.db.jpa.Blob;

public class Profile extends Controller
{

  /**
   * Method index displays the views/Profile/index page whenever the Profile
   * button is hit in the navigation panel on top of (nearly) each page. This is
   * only done when the users is logged in so in the beginning of the method
   * this is checked first (by looking for the session created when the users
   * logs in). Otherwise the non-logged-in viewer is redirected to the page
   * views/Accounts/login. This page displays few of user's details that the
   * user can also change here (these are user's profile picture and status) and
   * which are also available for all user's friends to see.
   */

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

  /**
   * Method not used
   * 
   * @param profiletext
   */
  public static void changeText(String profiletext)
  {
    Logger.info("Status changed to " + profiletext);
    Profile.index();
  }

  /**
   * Method changeText is initialize when the user hits "Change" button on their
   * Profile page. The status that is typed into the box provided is recognized
   * by the app as a String, irrespectively of numbers of words or symbols used.
   * Status is then changed to the one typed in and this new status also
   * displays on the Profile and Members page once the button "Change" is hit.
   * The information about this action is printed out in the Console.
   *
   * @param profiletext
   *          anything can be typed in the field provided on the page, it's
   *          recognized as string by the app
   */

  public static void changeStatus(String profiletext)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    user.statusText = profiletext;
    user.save();
    Logger.info("Status changed to " + profiletext);
    index();
  }

  /**
   * Method getPicture is used to display the user's uploaded profile picture if
   * such picture exists (if it's stored for the user in the database). The
   * picture is recognised by the app as Blob. The method is initialized on the 
   * user's friend's publicprofile page where it displays user's friend's picture.
   * 
   * @param id
   *          unique id number of type long by which the user is identfied
   */
  public static void getPicture(Long id)
  {
    User user = User.findById(id);
    Blob picture = user.profilePicture;
    if (picture.exists())
    {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }

  /**
   * Method upload picture is initialized by hitting the "upload" button on the
   * user's Profile page. The logged-in user is identified by his unique id
   * number (stored in the database) and the chosen picture (which is placed in
   * the field after browsing action on the page is finished) is assigned to the
   * user. The details are saved for the user (using the save buton) and the
   * Profile is refreshed (using render method) displaying user's new profile
   * picture.
   * 
   * @param id
   *          unique id number of type long by which the user is identfied
   * @param picture
   *          picture that is to be uploaded recognized as type Blob by the app
   */
  public static void uploadPicture(Long id, Blob picture)
  {
    User user = User.findById(id);
    user.profilePicture = picture;
    user.save();
    index();
  }
}
package controllers;

import play.*;

import java.util.*;

import models.User;
import play.mvc.*;

public class PublicProfile extends Controller
{

  /**
   * Method not used
   * 
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
   * Method index displays the views/PublicProfile/visit page whenever the user
   * clicks on any of his friend's name. This is only done when the users is
   * logged in so in the beginning of the method this is checked first (by
   * looking for the session created when the userssi logs in). Otherwise the
   * non-logged-in viewer is redirected to the page views/Accounts/login. The
   * page displays form for the user to send a message to this friend and it
   * also displays list of all messages that the user has ever sent to this
   * friend. The information about visiting the user's friend page is printed in
   * the Console.
   *
   * @param id
   *          unique id number of type long by which the user's friend is
   *          identified
   */
  public static void visit(Long id)
  {
    User user = User.findById(id);
    Logger.info("Just visiting the page for " + user.firstName + ' ' + user.lastName);
    Logger.info("Just visiting the page for " + id);
    render(user);
  }

  /**
   * Method sendMessage is initialized by clicking on the "Send" button on the
   * friend's profile page. The user can type the message subject and message
   * itself (message Text) into the fields provided on the page. These are
   * recognized by the app as a String irrespectively of symbols or number of
   * words used. When the Send button is clicked on, the message is send to this
   * particular friend with the help of method sendMessage, which is introduced
   * in the User model. Afterwards is the user's friend's PublicProfile page
   * refreshed (method visit(id))and the sent message displays in the field on
   * the right under the "Messages" header.
   * 
   * @param id
   *          unique id number of type long by which the user's friend is
   *          identified
   * @param subject
   *          anything can be typed in the field Subject provided on the page,
   *          it's recognized as string by the app
   * @param messageText
   *          anything can be typed in the field MessageText provided on the
   *          page, it's recognized as string by the app
   */
  public static void sendMessage(Long id, String subject, String messageText)
  {
    String userId = session.get("logged_in_userid");
    User fromUser = User.findById(Long.parseLong(userId));
    User toUser = User.findById(id);
    Logger.info("Message from user ID " + fromUser.firstName + " " + fromUser.lastName + " to: " + toUser.firstName
        + ' ' + toUser.lastName + ": " + "subject: " + subject + messageText);
    fromUser.sendMessage(toUser, subject, messageText);
    visit(id);
  }
}
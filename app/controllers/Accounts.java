package controllers;

import play.*;
import java.util.*;
import models.*;
import play.db.jpa.Blob;
import play.mvc.*;

public class Accounts extends Controller
{
  /**
   * Method signup is to display the views/Accounts/signup.html page when user
   * hits the "Signup" button on the welcoming (initial) page.
   */
  public static void signup()
  {
    render();
  }

  /**
   * Method login is to display the views/Accounts/login.html page when user
   * hits the "Login" button on the welcoming (initial) page.
   */
  public static void login()
  {

    render();
  }

  /**
   * This method clears the session which means that none of the user's private
   * views are available unless the users logs in again so the new session is
   * established then.
   */
  public static void logout()
  {
    session.clear();
    index();
  }

  public static void index()
  {
    render();
  }

  /**
   * The method edit is initialized when the user hits the link
   * "Edit my details". It'll redirect the user to the new view (page)
   * Accounts.edit. The method has the added checking condition to make sure
   * only the logged-in user can edit their details (by looking for the session
   * created when the users logs in).
   */
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

  /**
   * Following 5 methods (save_firstName, save_lastName, save_email, save_age,
   * save_nationality) are all related to the view "edit" where the logged-in
   * user can change some of his details. The form available displays the
   * current information that is stored for the user in the database. Any of the
   * 5 details can be then changed by typing new information in the field and
   * hitting the "Change" button afterwards. The "Change" buttons are added one
   * for each of the detail that could be changed. However each "Change" button
   * is related only to the field that is to be changed and it only changes this
   * particular detail about the user (it will only call the method that is
   * related to this particular detail). Thus 5 different methods were needed,
   * one for each Change button. Once the detail is successfully changed, the
   * user is redirected to their home page and the changed detail is displayed.
   */
  public static void save_firstName(String firstName)
  {
    String userId = session.get("logged_in_userid");
    User me = User.findById(Long.parseLong(userId));
    if (me != null)
    {
      me.firstName = firstName;
      me.save();
      Home.index();
    }
  }

  public static void save_lastName(String lastName)
  {
    String userId = session.get("logged_in_userid");
    User me = User.findById(Long.parseLong(userId));
    if (me != null)
    {
      me.lastName = lastName;
      me.save();
      Home.index();
    }
  }

  public static void save_email(String email)
  {
    String userId = session.get("logged_in_userid");
    User me = User.findById(Long.parseLong(userId));
    if (me != null)
    {
      me.email = email;
      me.save();
      Home.index();
    }
  }

  public static void save_age(int age)
  {
    String userId = session.get("logged_in_userid");
    User me = User.findById(Long.parseLong(userId));
    if (me != null)
    {
      me.age = age;
      me.save();
      Home.index();
    }
  }

  public static void save_nationality(String nationality)
  {
    String userId = session.get("logged_in_userid");
    User me = User.findById(Long.parseLong(userId));
    if (me != null)
    {
      me.nationality = nationality;
      me.save();
      Home.index();
    }
  }

  /**
   * Method register is used when a new user wants to sign up. When the user
   * fills in the form on views/signup.html and hits the "Submit" button, the
   * button will initialize the register method and the new user is successfully
   * added to the database where the unique UserId is assigned to the new user.
   * The new user is then redirected to their new home page where they can
   * explore the Spacebook app to the full extend. The user doesn't have to fill
   * in all of the details in order to by successfully signed up. However,
   * fields marked with * must be filled in, that's to avoid adding up an empty
   * user. Thus before the new user Object is created (new user added to the
   * database), 3 if conditions (checking if the 3 fields are not empty) must be
   * false.
   * 
   * @param firstName
   *          user's first name, recognized by the app as a string, mandatory
   *          field, must be filled in
   * @param lastName
   *          user's first name, recognized by the app as a string
   * @param emailuser
   *          's first name, recognized by the app as a string mandatory field,
   *          must be filled in
   * @param password
   *          user's first name, recognized by the app as a string mandatory
   *          field, must be filled in
   * @param age
   *          user's first name, recognized by the app as a number of an integer
   *          type
   * @param nationality
   *          user's first name, recognized by the app as a string
   */

  public static void register(String firstName, String lastName, String email, String password, int age,
      String nationality)
  {
    if (email != null && email.length() == 0 || email == "")
    {
      Logger.info("No email provided, user not registered");
      Accounts.index();
    }
    else
      if (firstName != null && firstName.length() == 0 || firstName == "")
      {
        Logger.info("No first name provided, user not registered");
        Accounts.index();
      }
      else
        if (password != null && password.length() == 0 || password == "")
        {
          Logger.info("No pasword provided, user not registered");
          Accounts.index();
        }
        else
        {
          Logger.info(firstName + " " + lastName + " " + email + " " + password + " " + age + " " + nationality);
          User user = new User(firstName, lastName, email, password, age, nationality);
          user.save();
          Logger.info("Registration successful");
          session.put("logged_in_userid", user.id);
          Home.index();
        }
  }

  /**
   * When user tries to login, they must type their email and password in the
   * fields provided on the login page. By hitting the "Login" button, method
   * authenticate is initialized upon which users credentials (their email AND
   * their password) are checked and if both are matching one of the User's
   * lines (user's details) in the database, their id is picked by which then a
   * new session is established and the user's home page id displayed.
   * 
   * @param email
   *          user's email
   * @param password
   *          user's password
   */
  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + " " + password);

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
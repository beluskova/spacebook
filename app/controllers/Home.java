package controllers;
import play.*;
import models.Friendship;
import models.Message;
import models.User;
import java.util.*;
import models.*;
import play.db.jpa.JPABase;
import play.mvc.*;
import utils.MessageDateComparator;
import utils.MessageFromComparator;

public class Home extends Controller
{

  /**
   * Each user's own home page is displayed whenever this method is called. The
   * user must be logged in, otherwise the landing (welcoming) page is
   * displayed (this is checked by looking for the session created when
   *  the users logs in in the if condition).
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
      Accounts.index();
    }
  }

  /**
   * This method is used when he user wants to remove (delete) one of his
   * friends displayed in the list of friends on the user's home page. The
   * friend is identified by his unique id (which is stored in the database) and
   * by help of the unfriend method (created in the User model) the friend is
   * then removed from the list of friends and the users home page is displayed
   * (refreshed), showing a new list of friends, without the one that was after
   * being removed from the list. The method is initialized by pressing the
   * "Drop" button.
   * 
   * @param id
   *          number of type long by which the user's friend to be removed
   *          (dropped) is identified
   */
  public static void drop(Long id)
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    User friend = User.findById(id);
    user.unfriend(friend);
    Logger.info("Dropping " + friend.email);
    index();
  }

  /**
   * Method by User sorts out all user's incoming messages by the sender meaning
   * that all messages from one user are listed first before messages from next
   * friend are listed, irrespectively of the date or time they were sent. This
   * is done using the Collections class (sort method) with a help of a
   * MessageFromComparator() class. Once the messages are sorted, the home page
   * is refreshed and the messages are displayed depending on the sender. The
   * method is initialized by pressing the "BY USER" button on the home page.
   * 
   */
  public static void byUser()
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    Collections.sort(user.inbox, new MessageFromComparator());
    render("Home/index.html", user);
    Home.index();
  }

  /**
   * Method by User sorts out all user's incoming messages by the date/time they
   * were sent meaning that all messages are listed by the time they were
   * received by user (oldest first) irrespectively of the friend who sent them.
   * This is done using the Collections class (sort method) with a help of a
   * MessageDateComparator() class. Once the messages are sorted, the home page
   * is refreshed and the messages are displayed depending on the date and time
   * they were received by user, oldest first.The method is initialized by
   * pressing the "BY DATE" button on the home page.
   * 
   */
  public static void byDate()
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    Collections.sort(user.inbox, new MessageDateComparator());
    render("Home/index.html", user);
  }

  /**
   * Method conversation is used when the logged in user wants to have their
   * messages sorted by conversation, each one conversation with one other user
   * (friend) is displayed in one separate box and is sorted by the date/time
   * sent. This is done within the helping method getConversation. The main
   * principle behind the method is that an arrayList of arrayLists is created
   * and while looping through the list of user's friends (using a while loop),
   * for each one of the user's friends a helping method getConversation is
   * initiated. This helping method sorts out all messages with this particular
   * friend into a single conversation, which is then added to the list of all
   * conversations that have ever been made by user and their friends. Method
   * conversation will display the user's home page in a new view:
   * home/conversation.
   * 
   * @return complete and sorted conversation as an array list of array lists of
   *         messages
   */
  public static List<ArrayList<Message>> conversation()
  {
    String userId = session.get("logged_in_userid");
    User user = User.findById(Long.parseLong(userId));
    List<ArrayList<Message>> conversation = new ArrayList();
    int index = 0;
    while (index < user.friendships.size())
    {
      List<Message> Conversation = new ArrayList();
      User ThisFriend = user.friendships.get(index).targetUser;
      Conversation = getConversation(user, ThisFriend);
      conversation.add((ArrayList<Message>) Conversation);
      user.save();
      index += 1;
    }
    render("Home/conversation.html", user, conversation);
    return conversation;

  }

  /**
   * A helping method getConversation is initiated within the conversation
   * method (above). This method created list of messages that user has ever
   * exchanged with the particular friend that is (along with the user) a
   * parameter required in order to create the conversation. The method loops
   * through the user's inbox and also through user's outbox and if a message
   * from (or message to) this particular is found, then it is added to the list
   * of messages called conversation. Once all users messages are checked, the
   * final list is sorted by date/time, using the Collections class (sort
   * method) with a help of a MessageDateComparator() class.
   * 
   * @param user
   *          is user who's messages are to be sorted
   * @param friend
   *          is user's friend whose messages are to be added to conversation
   * @return complete and sorted conversation as an array list of messages
   */

  static List<Message> getConversation(User user, User friend)
  {

    List<Message> conversations = new ArrayList();
    // to avoid the Null Pointer Exception Errors:
    if (user.outbox != null)
    {
      for (Message message : user.outbox)
      {
        if (message.to == friend)
        {
          conversations.add(message);
        }
      }
    }
    // to avoid the Null Pointer Exception Errors:
    if (user.inbox != null)
    {
      for (Message message : user.inbox)
      {
        if (message.from == friend)
        {
          conversations.add(message);
        }
      }
    }
    Collections.sort(conversations, new MessageDateComparator());
    return conversations;
  }
}
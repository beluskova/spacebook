package utils;

import java.util.Comparator;
import models.Message;

public class MessageToComparator implements Comparator<Message>
{

  /**
   * Performs a lexicographic comparison of the String name fields of the
   * receiving user (that is User to) in 2 Message objects
   * 
   * @param o1
   *          the first message object
   * @param o2
   *          the second message object
   * @return 0 if the user name in o1 is equal to the name in o2 less than zero
   *         if name in o1 less than name in o2 greater than zero if name in o2
   *         greater than name in o2
   */

  @Override
  public int compare(Message o1, Message o2)
  {
    String o1Name = o1.to.firstName + o1.to.lastName;
    String o2Name = o2.to.firstName + o2.to.lastName;
    return o1Name.compareTo(o2Name);
  }

}

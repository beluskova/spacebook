package utils;

import java.util.Comparator;
import java.lang.Integer;

import models.User;

public class UserLeastTalkativeComparator implements Comparator<User>
{

  /**
   * Performs a comparison of two message outboxes by their size (that is the
   * amount of messages) in 2 User objects
   * 
   * @param a
   *          the first User object
   * @param b
   *          the second User object
   * @return 0 if the user outbox in a is equal to the size in b less than zero
   *         if outbox in a is less than outbox in b greater than zero if outbox
   *         in a is greater than outbox in b
   */

  /**
   * The original method used in John's Java assignment had to be changed to the
   * old one because the play uses Java 1.6 where this new method is not
   * recognized, it's left here, but it' commented out.
   */
  @Override
  public int compare(User a, User b)
  {
    // return Integer.compare (a.outbox.size(), b.outbox.size());
    return Integer.valueOf(a.outbox.size()).compareTo(Integer.valueOf(b.outbox.size()));
  }

}

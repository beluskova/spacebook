package utils;

import java.util.Comparator;

import models.User;

public class UserSocialComparator implements Comparator<User>
{
  /**
   * Performs a comparison of two friendships by their size (that is the amount
   * of friends) in 2 User objects
   * 
   * @param a
   *          the first User object
   * @param b
   *          the second User object
   * @return 0 if the size if friendship in a is equal to the size in b less
   *         than zero if size of friendship in a is less than size in b greater
   *         than zero if size of friendship in a is greater than size in b
   */

  /**
   * The original method used in John's Java assignment had to be changed to the
   * old one because the play uses Java 1.6 where this new method is not
   * recognized, it's left here, but it' commented out.
   */

  @Override
  public int compare(User a, User b)
  {
    // return Integer.compare (b.friendships.size(), a.friendships.size());
    return Integer.valueOf(b.friendships.size()).compareTo(Integer.valueOf(a.friendships.size()));

  }
}

package utils;

import java.util.Comparator;
import java.util.Date;
import models.Message;

/**
 * Performs a time comparison of the Date fields in 2 Message objects
 * 
 * @param o1
 *          the first message object
 * @param o2
 *          the second message object
 * @return 0 if the date in a is equal to the date in b less than zero if date
 *         in a less than date in b greater than zero if date in a greater than
 *         date in b
 */
public class MessageDateComparator implements Comparator<Message>
{
  public int compare(Message a, Message b)
  {
    return a.postedAt.compareTo(b.postedAt);
  }
}

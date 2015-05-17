package utils;

import java.util.Comparator;
import java.util.Date;

import models.Message;

public class MessageDateComparator implements Comparator<Message>
{
  public int compare(Message a, Message b)
  {
    return a.postedAt.compareTo(b.postedAt);
  }
}

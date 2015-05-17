package utils;

import java.util.Comparator;
import java.lang.Integer;

import models.User;

public class UserLeastTalkativeComparator implements Comparator<User>
{
  @Override
  public int compare(User a, User b)
  {
    // return Integer.compare (a.outbox.size(), b.outbox.size());
    return Integer.valueOf(a.outbox.size()).compareTo(Integer.valueOf(b.outbox.size()));
  }

}

package utils;

import java.util.Comparator;

import models.User;

import java.lang.Integer;

public class UserTalkativeComparator implements Comparator<User>
{
  @Override
  public int compare(User a, User b)
  {
    // return Integer.compare (b.outbox.size(), a.outbox.size());
    return Integer.valueOf(b.outbox.size()).compareTo(Integer.valueOf(a.outbox.size()));
  }
}

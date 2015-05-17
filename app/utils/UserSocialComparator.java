package utils;

import java.util.Comparator;

import models.User;

public class UserSocialComparator implements Comparator<User>
{
  @Override
  public int compare(User a, User b)
  {
    // if ( (a.friendships.size() > 0) && (b.friendships.size() > 0) )

    // return Integer.compare (b.friendships.size(), a.friendships.size());
    return Integer.valueOf(b.friendships.size()).compareTo(Integer.valueOf(a.friendships.size()));

  }
}

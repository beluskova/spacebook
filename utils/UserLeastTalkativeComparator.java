package utils;

import java.util.Comparator;

import models.User;

public class UserLeastTalkativeComparator implements Comparator<User>
{
  @Override
  public int compare(User a, User b)
  {
    //Algorithmic code: remove when done
    // TODO: Complete implementation of method UserLeastTalkativeComparator.compare
	// Compare outbox sizes and return integer appropriate to sorting smallest to largest
    return Integer.compare (a.outbox.size(), b.outbox.size());
  }
}

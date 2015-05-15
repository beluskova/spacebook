package utils;

import java.util.Comparator;
//import java.util.Date;

import models.Message;

public class MessageDateComparator implements Comparator<Message>
{
  @Override
  public int compare(Message a, Message b)
  {
	//TODO: Complete implementation of method MessageDateComparator.compare
    //Algorithmic code: delete when method complete 
    //compare the time-date attributes of each message
    //use the String compareTo method
    //use attribute of Message b as the parameter
   
        return a.postedAt.compareTo(b.postedAt);
     }
  }
   

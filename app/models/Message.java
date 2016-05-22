package models;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Message extends Model
{
  public String messageText;
  public String subject;
  public Date postedAt;

  @ManyToOne
  public User from;

  @ManyToOne
  public User to;
   
  public Message(User from, User to, String subject, String messageText)
  {
    this.from = from;
    this.to = to;
    this.subject = subject;
    this.messageText = messageText;
    postedAt = new Date();
    pause();
  }

  private void pause()
  {
    try
    {
      Thread.sleep(1000);
    }
    catch (InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public String toString()

  {
    return this.from.firstName + " says... " + this.messageText;
  }

  public String log()
  {
    return postedAt + from.firstName + " says... " + messageText + "\n";
  }

  public void displayMessage()
  {
    System.out.print(log());
    // System.out.println(toString());
  }

  public static void displayMessage(ArrayList<Message> messages)
  {
    for (Message message : messages)
    {
      message.displayMessage();
    }
  }
}

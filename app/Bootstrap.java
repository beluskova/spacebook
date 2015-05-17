import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import play.*;
import play.jobs.*;
import play.libs.MimeTypes;
import play.test.*;
import play.db.jpa.Blob;
import models.*;

//@OnApplicationStart
public class Bootstrap extends Job 
{ 
  public void doJob() throws FileNotFoundException
  {
   if (User.count() == 0)
    {
      Fixtures.deleteDatabase();
      Fixtures.loadModels("data.yml");
      
     
      String photoName = "app/homer.gif";
      Blob blob =  new Blob ();
      blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
      User homer = User.findByEmail("homer@simpson.com");
      homer.profilePicture = blob;
      homer.save();
      
      String photoName1 = "app/lisa.gif";
      Blob blob1 =  new Blob ();
      blob1.set(new FileInputStream(photoName1), MimeTypes.getContentType(photoName1));
      User lisa = User.findByEmail("lisa@simpson.com");
      lisa.profilePicture = blob1;
      lisa.save();
      
      String photoName2 = "app/maggie.gif";
      Blob blob2 =  new Blob ();
      blob2.set(new FileInputStream(photoName2), MimeTypes.getContentType(photoName2));
      User maggie = User.findByEmail("maggie@simpson.com");
      maggie.profilePicture = blob2;
      maggie.save();
      
      String photoName3 = "app/marge1.jpg";
      Blob blob3 =  new Blob ();
      blob3.set(new FileInputStream(photoName3), MimeTypes.getContentType(photoName3));
      User marge = User.findByEmail("marge@simpson.com");
      marge.profilePicture = blob3;
      marge.save();
      
      String photoName4 = "app/bart.gif";
      Blob blob4 =  new Blob ();
      blob4.set(new FileInputStream(photoName4), MimeTypes.getContentType(photoName4));
      User bart = User.findByEmail("bart@simpson.com");
      bart.profilePicture = blob4;
      bart.save();

      String photoName5 = "app/barney.jpg";
      Blob blob5 =  new Blob ();
      blob5.set(new FileInputStream(photoName5), MimeTypes.getContentType(photoName5));
      User barney = User.findByEmail("barney@simpson.com");
      barney.profilePicture = blob5;
      barney.save();

   }
  }
}
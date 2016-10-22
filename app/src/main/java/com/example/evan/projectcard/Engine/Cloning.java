package com.example.evan.projectcard.Engine;
import java.io.*;
import java.util.*;

public class Cloning
{
  private Cloning(){}

  static public Object deepCopy(Serializable oldObj) throws Exception
  {
     ObjectOutputStream oos = null;
     ObjectInputStream ois = null;
     try
     {
        ByteArrayOutputStream bos = 
              new ByteArrayOutputStream();
        oos = new ObjectOutputStream(bos);
        // serialize and pass the object
        oos.writeObject(oldObj);
        oos.flush();              
        ByteArrayInputStream bin = 
              new ByteArrayInputStream(bos.toByteArray());
        ois = new ObjectInputStream(bin);                 
        // return the new object
        return ois.readObject();
     }
     catch(Exception e)
     {
        android.util.Log.d("cloning", "Exception in Cloning = " + e);
        throw(e);
     }
     finally
     {
        oos.close();
        ois.close();
     }
  }
  
}

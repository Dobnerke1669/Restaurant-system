package DataAccess;

import BusinessLogicLayer.DeliveryService;

import java.io.*;

public class Serializator {
    public void serialize(Object object,String file)
    {

        try {
            FileOutputStream fileOut=new FileOutputStream(file);
            ObjectOutputStream objectOut= new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            objectOut.close();
            fileOut.close();
            System.out.println("Object has been serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object deserialize(String file)
    {
        try {
            FileInputStream fileIn=new FileInputStream(file);
            ObjectInputStream objectIn=new ObjectInputStream(fileIn);
            Object obj=objectIn.readObject();
            objectIn.close();
            fileIn.close();
            System.out.println("Object " + file +" has been deserialized");
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

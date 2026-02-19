package com.epam.practice.coreJava;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

 

public  class SerializeDemo {

    public static void write() throws IOException {

    	
    	Serialize s = new Serialize(1, "Ramya");
    	//Serialize s = new Serialize(1);
        FileOutputStream file = new FileOutputStream("Serialize.ser");
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(s);

        out.close();
        file.close();

        System.out.println("Object serialized");
    }
    public static void read() throws IOException, ClassNotFoundException {
    	FileInputStream file = new FileInputStream("Serialize.ser");
        ObjectInputStream in = new ObjectInputStream(file);

        Serialize s = (Serialize) in.readObject();

        in.close();
        file.close();

        System.out.println("Object deserialized: " + s);
    }
    
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	//write();  // first write
    	read();  // second read
    }
}

 class Serialize implements Serializable {

   private static final long serialVersionUID = 1L; // if we change it after writing -- com.epam.practice.coreJava.Serialize; local class incompatible: stream classdesc serialVersionUID = 1, local class serialVersionUID = 2
// if we add new fields but the version uid is same then no error just 1, null
    private int id;
   private String name;

    public Serialize(int id, String name) {
        this.id = id;
      this.name = name; // comment this to observer the change after write is performed
    }

   @Override
    public String toString() {
      return id + " "+name ;
	  //return id+ " ";
    }
}


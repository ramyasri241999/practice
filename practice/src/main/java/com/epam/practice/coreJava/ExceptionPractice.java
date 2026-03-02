package com.epam.practice.coreJava;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.epam.practice.exceptionHandler.AccountNotFoundException;

public class ExceptionPractice {
	
	public static void main(String[] args) {
		
		checkedExample();
		uncheckedExample();
		customException();
		
	}
	
	public static void customException() {
		int b =0;
		if(b==0)
			throw new AccountNotFoundException("Account is notFound"); //custom exception
		
	}
	public static void checkedExample() {
		int a = 5, b=0;
		try {
		    System.out.println("A");  //prints
		    int x = 10 / 0;
		    System.out.println("B"); //ignores as exception occured
		} catch (ArithmeticException e) {
		    System.out.println("C");  //prints
		} finally {
		    System.out.println("D");  //prints - finally always executes
		}
		System.out.println("E"); //prints bcz the exception has been caught
		
		
		try {
			Job job = new Job();
			job.setSalary(-100);
		}
		catch(Exception e) {
			System.out.println("Exception due to salary :: "+e.getMessage());
		}
		
		try (FileReader fr = new FileReader("text.txt")) {
		}
		catch(IOException io) {
			System.out.println("Exception in IO exception "+ io.getMessage());
		}
		
	}
	
	public static void uncheckedExample() {
		try {
			Connection c = DriverManager.getConnection("test");
		} catch (SQLException e) {
			System.out.println("Exception in SQLException "+ e.getMessage());
			//e.printStackTrace();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        try {
            sdf.parse("2024/01/01"); // wrong format
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        
        
        try {
        	Class.forName("Ram.class");
        	
        }
        catch(ClassNotFoundException e) {
        	System.out.println("Exception in class not found checkd exception "+ e.getMessage());
        }
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }
        
        String s = null;
        try {
        	s.length();
        }
        catch(NullPointerException ne) {
        System.out.println("Null pointer exception :: "+ ne.getMessage());
	}
        
        int[] arr = {1,2,3};
        try {
        	  System.out.println(arr[5]);
        }
        catch(ArrayIndexOutOfBoundsException iob) {
        	 System.out.println("ArrayIndexOutOfBoundsException "+ iob.getMessage());
        }
      try {
    	  System.out.println(Integer.parseInt("abc"));
      }
      catch(NumberFormatException nfe) {
    	  System.out.println("Exception in Numberformat exception :: "+ nfe.getMessage());
      }
      
      Object obj = "Hello";
      try {
      Integer num = (Integer) obj;  // ClassCastException
      System.out.println("num "+num);
      }
      catch(ClassCastException cce) {
    	  System.out.println("Exception in class cast exception "+ cce.getMessage());
      }
       
try {
	List<String> list = List.of("A", "B");
	list.add("C");  // immutable list

}
catch(UnsupportedOperationException uoe) {
	System.out.println("Exception in UnsupportedOperationException "+ uoe.getMessage());
}
        
//        try {}
//        catch(NullPointerException e | RuntimeException re) { } // nullpointer will be covered by runtime so no need to give again
        	
//        try {}
//      catch(IOException ioe | Exception re) { } // IOException will be covered by Exception so no need to give again
        //so sub Exception will be covered if parent is there in the catch pipe
        
      try {}
      catch(NullPointerException | UnsupportedOperationException re) { }  //multi catch -- this is valid since both are same level
      
      
      try {}
      catch(NullPointerException npe) {}
      catch(RuntimeException rte) {}  // This works
   
      
      	
	}

}


class Job{
	private double salary;
	public void setSalary(double salary) {
	//public void setSalary(double salary) throws InvalidSalaryException {  // So checked exceptions FORCE handling. if customexception extends Exception then we need to handle 
		//that exception .exception so java forces handling
		if(salary <0) {
			throw new InvalidSalaryException("salary cannot be negative");
		}
		this.salary= salary;
	}
	
	public double getSalary() {
		return this.salary;
	}
}

class InvalidSalaryException extends RuntimeException{  // This is unchecked exception bcz it extends runtimeexception
	// if the class extends the Exception then it will be checked exception
	//class InvalidSalaryException extends Exception
	public InvalidSalaryException(String message) {
		super(message);
	} 
}  // This class is not accessible to other packages as it it set to default


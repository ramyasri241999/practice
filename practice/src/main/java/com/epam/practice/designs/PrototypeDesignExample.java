package com.epam.practice.designs;

import java.util.HashMap;
import java.util.Map;

public class PrototypeDesignExample {
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Address address = new Address("city");
		Contractor c1= new Contractor(1,"Ramya","IT",60, address);
		
		Contractor c2 = (Contractor) c1.clone();  //shallow copy
		c2.setName("lakshmi");
		c2.getAddress().setCity("Hyderabad");
		
		System.out.println("c1 :: "+c1);
		System.out.println("c2 :: "+ c2); 
		
		
		Map<String, Prototype> registry = new HashMap<>();

		registry.put("manager", new TempContractor("Sri"));

		TempContractor newManager = (TempContractor) registry.get("manager").clone();   //advanced registry
	
	}

}



class Contractor implements Cloneable{
	
	private int id;
	private String name;
	private String department;
	private int age;
	private Address address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Address getAddress() {
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address= address;
	}
	public Contractor(int id, String name, String department, int age, Address address) {  // we need to use the parameter constructor to avoid attacks
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.age = age;
		this.address = address;
	}
	
	public Contractor(Contractor other) {   //copy Constructor
		this.id = other.id;
		this.name = other.name;
		this.department = other.department;
		this.age = other.age;
		this.address = other.address;
	}
	
	
	@Override
	public String toString() {
		return "Contractor [id=" + id + ", name=" + name + ", department=" + department + ", age=" + age + ", address="
				+ address + "]";
	}
//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		return super.clone();  // shallow copy  -- address wont change
//	} 
//	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		Contractor cloned = (Contractor)super.clone();
		cloned.address = new Address(this.address.getCity());
		return cloned;  // deep copy  -- address also change
	}
	
//	   @Override
//	    public Prototype clone() {
//	        return new Contractor(this);   // using copy constructor   This is when we implements Prototype
//	    }
	
	
}

class Address{
	private String city;

	public Address(String city) {
		super();
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + "]";
	}
	
}


interface Prototype {
    public Prototype clone();
} // real prototype


class TempContractor implements Prototype{
private String name;


	public TempContractor(String name) {
	super();
	this.name = name;
}


	public TempContractor(TempContractor other) {
	super();
	this.name = other.name;
}


	@Override
	public Prototype clone() {
		// TODO Auto-generated method stub
		return new TempContractor(this);
	}

	
	
}

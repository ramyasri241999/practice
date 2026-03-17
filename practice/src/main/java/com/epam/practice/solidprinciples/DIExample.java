package com.epam.practice.solidprinciples;
/*
 * Dependency Inversion Principle (DIP) states that high-level modules should not depend on low-level modules. 
 * Both should depend on abstractions.
 * In other words, it suggests that we should depend on abstractions (interfaces) rather than concrete implementations.
 * spring ex: Data Access Object (DAO) pattern, where the high-level business logic depends on an abstraction (interface) for data access, rather than a concrete implementation. 
 * This allows for flexibility and easier maintenance, as the underlying data access implementation can be changed without affecting the business logic.
 */
public class DIExample {

}

/*
 * first lets see the example without following the DIP principle, where the high-level module (BusinessLogic) depends on a low-level module (MySQLDatabase).
 */
class MySQLDatabase{
	public void connect() {
		System.out.println("Connecting to MySQL database...");
	}
}
class UserS{
	private MySQLDatabase database;
	// for suppose, in future we want to switch to another database like MongoDB, then we have to change the code of UserService class, which is not good, because it violates the DIP principle.

	
	public UserS() { 
		this.database = new MySQLDatabase(); // tight coupling, because UserService is directly dependent on MySQLDatabase
	}
	
	public void performDatabaseOperation() {
		database.connect();
	}
}


/********** Now, let's see how we can implement the same functionality while following the DIP principle. **********/

interface Database{
	void connect();
}

class MySql implements Database{
	@Override
	public void connect() {
		System.out.println("Connecting to MySQL database...");
	}
}

class UserServ {
	private Database database; 
	
	public UserServ(Database database) { // constructor injection, we are injecting the dependency through the constructor
		this.database = database; // now UserServ is not directly dependent on MySql, it depends on the abstraction (Database interface)
	}
	/*
	 * client can do 
	 * Database database = new MySql(); // create an instance of the concrete implementation
	 * UserServ userService = new UserServ(database); // inject the dependency into the UserServ class
	 * or
	 * Database database = new MongoDB(); // create an instance of another concrete implementation
	 * UserServ userService = new UserServ(database); // inject the dependency into the UserServ class
	 */
	
	public void performDatabaseOperation() {
		database.connect();
	}
}
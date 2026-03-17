package com.epam.practice.solidprinciples;
/*
 * Interface Segregation Principle states that clients should not be forced to depend on interfaces they do not use.
 * clients should not be forced to implement interfaces they do not use. 
 * Instead, they should be provided with specific interfaces that are relevant to their needs.
 * Spring ex:crud repositories, we have separate interfaces for different operations like CrudRepository, PagingAndSortingRepository, JpaRepository etc. 
 * which allows us to implement only the interfaces that are relevant to our needs and we are not forced to implement all the methods of a single interface which may not be relevant to our needs.
 * ex: even collections framework in java is designed based on the interface segregation principle, 
 * we have separate interfaces for different types of collections like List, Set, Map etc. which allows us to implement only the interfaces that are relevant to our needs and we are not forced to implement all the methods of a single interface which may not be relevant to our needs.
 */
public class InterfaceSeggExample {

}

/*
 * This is a violation of the Interface Segregation Principle because the RobotWorker class is forced to implement the eat() method, which it does not use.
 */

interface Worker{
	void work();
	void eat();
}

class HumanWorker implements Worker{

	@Override
	public void work() {
		System.out.println("Human is working");
	}

	@Override
	public void eat() {
		System.out.println("Human is eating");
	}
	
}

class RobotWorker implements Worker{

	@Override
	public void work() {
		System.out.println("Robot is working");
	}

	@Override
	public void eat() { //Robot cannot eat, but we are forced to implement this method because of the Worker interface, which violates the Interface Segregation Principle
		throw new UnsupportedOperationException("Robot cannot eat");
	}
	
}

/*
 * Now lets see how we can achieve the same functionality without violating the Interface Segregation Principle by creating separate interfaces for work and eat.
 */

interface Workable{
	void work();
}

interface Eatable{
	void eat();
}

class Human implements Workable,Eatable{
	
	@Override
	public void work() {
		System.out.println("Human is working");
	}

	@Override
	public void eat() {
		System.out.println("Human is eating");
	}
}

class Robot implements Workable{ // we implement only the workable interface since robot cannot eat, we do not need to implement the eatable interface
	@Override
	public void work() {
		System.out.println("Robot is working");
	}
}

/*
 * 1.Fat interfaces: Avoid creating interfaces that have too many methods, as this can lead to clients being forced to implement methods they do not use.
 * 2. Empty interfaces: Avoid creating interfaces that have no methods, as this can lead to confusion and make it difficult for clients to understand the purpose of the interface.
 * 3.Forcing unrelated behaviour
 * 4.
 */

package com.epam.practice.designs.behaviour;

import java.util.ArrayList;
import java.util.List;

/*
 * Defines a one-to-many dependency between objects so that when one object changes state,
 *  all its dependents are notified and updated automatically.
 *  one object: the subject
 *  many objects: the observers
 *  EventListeners, forEach , and the publish-subscribe pattern are all examples of the Observer pattern in Java.
 */
public class ObserverExample {
public static void main(String[] args) {
		NewsChannel newsChannel = new NewsChannel(); // create a subject
		mobileUser user1 = new mobileUser("Ramya"); // create some observers
		WebUser user2 = new WebUser("Sita");
		
		newsChannel.register(user2); // register the observers with the subject
		newsChannel.register(user1); // register the observers with the subject
		
		newsChannel.notifyObservers("Breaking news: Observer pattern implemented!"); // notify the observers of a change in the subject's state
		
		newsChannel.unregister(user1); // unregister an observer from the subject
		newsChannel.notifyObservers("More news: Observer pattern is working!"); // notify the remaining observers of another change in the subject's state
}
}

interface Observer{					// interface for the observers to implement
		void update(String message); // method to be called when the subject changes state
}

interface Subject{				// interface for the subject to implement
		void register(Observer obsever);
		void unregister(Observer obsever);
		void notifyObservers(String message);
}

class NewsChannel implements Subject{	// the subject implementation
		private List<Observer> observers = new ArrayList<>();
		
		@Override
		public void register(Observer observer) {
				observers.add(observer);
		}
		
		@Override
		public void unregister(Observer observer) {
				observers.remove(observer);
		}
		
		@Override
		public void notifyObservers(String message) {
				for(Observer observer : observers) {
						observer.update(message);  // when the subject changes state, it calls the update method of all the registered observers to notify them of the change
				}
		}
}

class mobileUser implements Observer{	// the observer implementation
		private String name;
		
		public mobileUser(String name) {
				this.name = name;
		}
		
		@Override
		public void update(String message) {
				System.out.println(name + " received news: " + message); // when the observer receives a notification from the subject, it can perform some action based on the message received, in this case, it simply prints the message to the console.
		}
}


class WebUser implements Observer{
	 private String name;
	 public WebUser(String name) {
		 this.name = name;
	 }
	 
	 @Override
	 public void update(String message) {
		 System.out.println(name + " received news: " + message); // similar to the mobileUser, it simply prints the message to the console when it receives a notification from the subject.
	 } 
}


/*
 * Best Practices

✔ Use interfaces for observers
✔ Keep update methods simple
✔ Use async event handling for heavy work
✔ Remove observers when not needed
✔ Prefer event objects instead of primitives
 */

/*
 * Common mistakes to avoid
 * ✔ Not unregistering observers	
 * ✔ Tight coupling between subject and observers
 * ✔ Complex update logic in observers
 * 
 */

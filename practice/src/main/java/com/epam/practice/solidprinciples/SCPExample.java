package com.epam.practice.solidprinciples;

public class SCPExample {

	public static void main(String[] args) {
		
		User user = new User("John Doe");
		UserService userService = new UserService();
		userService.saveUser(user);	
		userService.sendWelcomeEmail(user);
		userService.reportUserActivity(user); // this class violates the Single Responsibility Principle, as it has multiple reasons to change, which are to save a user, send a welcome email, and report user activity. This makes the code more difficult to maintain and understand, as changes to one responsibility may affect the other responsibilities.
		
		/*
		 * this class adheres to the Single Responsibility Principle, as it has only one reason to change, 
		 * which is to register a user. The responsibilities of saving a user, sending a welcome email, 
		 * and reporting user activity are now separated into different classes, making the code more maintainable and easier to understand.
		
		 */
		UserServiceV2 userServicev2 = new UserServiceV2(new UserRepository(), new EmailService(), new AnalyticsService()); 
	}
}




class UserService{  // this class has multiple responsibilities, violating the Single Responsibility Principle
	void saveUser(User user) {
		// save user to database
	}
	
	void sendWelcomeEmail(User user) {
		// send welcome email to user
	}
	
	void reportUserActivity(User user) {
		// report user activity to analytics service
	}
}

class User{
	private String name;
	
	public User(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}


class UserRepository {
	void saveUser(User user) {
		// save user to database
	}
}

class EmailService {
	void sendWelcomeEmail(User user) {
		// send welcome email to user
	}
}

class AnalyticsService {
	void reportUserActivity(User user) {
		// report user activity to analytics service
	}
}

class UserServiceV2 {
	private UserRepository userRepository;
	private EmailService emailService;
	private AnalyticsService analyticsService;
	
	public UserServiceV2(UserRepository userRepository, EmailService emailService, AnalyticsService analyticsService) {
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.analyticsService = analyticsService;
	}
	
	void registerUser(User user) {
		userRepository.saveUser(user);
		emailService.sendWelcomeEmail(user);
		analyticsService.reportUserActivity(user);  // this method is still responsible for multiple tasks, but the responsibilities are now separated into different classes, adhering to the Single Responsibility Principle
	}
}

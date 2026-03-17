package com.epam.practice.designs.structural;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StaticProxyDesignPattern {

	public static void main(String[] args) {
		UserService userService = new UserServiceProxy(new UserServiceImpl());
		userService.register("Ramya");  //Manual proxy example
		
		
		UserService realObject = new UserServiceImpl();
		UserService proxy = (UserService)Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[] {UserService.class}, new LoggingHandler(realObject));
		//Java creates a new class at runtime like  class $Proxy0 implements UserService
		
		proxy.register("Book");
	}
}


interface UserService{
	void register(String username);
}

class UserServiceImpl implements UserService{
	 public void register(String username) {
		 System.out.println("Inside the UserServiceImpl Real Object");
	 }
}

class UserServiceProxy implements UserService{
	
	private UserService userService;
	
	public UserServiceProxy(UserService userService) {
		this.userService = userService;
	}
	public void register(String username) {
		System.out.println("Entering the userServiceProxy :: ");
		userService.register(username);     // calling real object
		System.out.println("Ending the proxy :: ");
		
	}
}



// ==================== Dynamic Proxy Example ==============================

interface BookInterface{
	void pay(String bookName);
}


class BookServiceImpl implements BookInterface{

	@Override
	public void pay(String bookName) {
		System.out.println("Inside the BookServiceImpl :: real object ");
	}
	
}


class LoggingHandler implements InvocationHandler{

	private Object target;
	
	
	public LoggingHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Inside the proxy :: ");
		Object result = method.invoke(target, args);
		System.out.println("outside the proxy :: ");
		
		return result;
	}
	
	
	
}

//class $Proxy0 implements UserService{   public void register(String username) {
//handler.invoke(this, methodObject, args);
//}}


//visual flow of dynamic proxy -- jdk proxy works with interface

/* Client
↓
Proxy Object (generated)
↓
InvocationHandler.invoke()
↓
method.invoke(realObject)
↓
Real Implementation

 */



/*
CGLIB generates:  It works with class

class PaymentService$$Enhancer extends PaymentService {
    @Override
    public void pay() {
        // intercept
    }
}

*/
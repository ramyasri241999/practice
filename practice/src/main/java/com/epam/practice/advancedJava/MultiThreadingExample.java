package com.epam.practice.advancedJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadingExample {
	
	public static void main(String[] args) throws InterruptedException {
		
		MyThread myThread = new MyThread();      // LifeCycle - New
		//myThread.run();  //Thread running: main		-- just executes like a normal method.
		myThread.join(); // before start so it asks you to handle the InterruptedException and immediately return bcz thread is not alive
		myThread.start();  //Thread running: thread-0     -- creates a new thread.   //LifeCycle - Runnable
		//myThread.start();  // IllegalThreadStateException
		
		Thread myRunnable = new Thread(new MyRunnable());
		myRunnable.start();  //Runnable running: Thread-1
		//myRunnable.run();  //Runnable running: main
		
		
		ExecutorService ex = Executors.newFixedThreadPool(2);
		ex.submit(()->{System.out.println("Inside the executor service");});
		
		ex.shutdown();
		
		
		//myThread.wait();  wait is used outside synchronised block so  java.lang.IllegalMonitorStateException: current thread is not owner at run time
		
		
		
		SharedResource resource = new SharedResource();

        // Thread 1 → Object Lock
        Thread t1 = new Thread(() -> {
            resource.objectLockMethod();
        }, "T1");

        // Thread 2 → Competing for same object lock
        Thread t2 = new Thread(() -> {
            resource.objectLockMethod();
        }, "T2");

        // Thread 3 → Wait
        Thread t3 = new Thread(() -> {
            resource.waitMethod();
        }, "T3");

        // Thread 4 → Notify
        Thread t4 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
            resource.notifyMethod();
        }, "T4");

        // Thread 5 → Class Lock
        Thread t5 = new Thread(() -> {
            SharedResource.classLockMethod();
        }, "T5");

        Thread t6 = new Thread(() -> {
            SharedResource.classLockMethod();
        }, "T6");

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        // Main waits for t1 to finish
        t1.join();
        System.out.println("Main thread waited for T1 using JOIN");
		
	}
}

class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }

}

/*
 Why Runnable Is Better?

Java doesn’t support multiple inheritance

You can extend another class

Better design separation
 */
class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Runnable running: " + Thread.currentThread().getName());
    }

}



class SharedResource {
	
	

    // Object-level lock method
    synchronized void objectLockMethod() {
        System.out.println(Thread.currentThread().getName() + " acquired OBJECT lock");
        try {
            Thread.sleep(2000); // TIMED_WAITING
        } catch (InterruptedException e) {}
        System.out.println(Thread.currentThread().getName() + " releasing OBJECT lock");
    }

    // Class-level lock method
    static synchronized void classLockMethod() {
        System.out.println(Thread.currentThread().getName() + " acquired CLASS lock");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println(Thread.currentThread().getName() + " releasing CLASS lock");
    }
    
    

    // wait/notify example
    synchronized void waitMethod() {
        try {
            System.out.println(Thread.currentThread().getName() + " going to WAIT");
            wait(); // WAITING state
            System.out.println(Thread.currentThread().getName() + " resumed from WAIT");
        } catch (InterruptedException e) {}
    }

    synchronized void notifyMethod() {
        System.out.println(Thread.currentThread().getName() + " calling NOTIFY");
        notify();
    }
}

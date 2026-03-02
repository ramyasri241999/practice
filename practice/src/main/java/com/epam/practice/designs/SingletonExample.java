package com.epam.practice.designs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonExample {
	
	
	
	public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
		constructor.setAccessible(true); // reflection attack
		Singleton s1= Singleton.getInstance();
		Singleton s2 = constructor.newInstance();
		System.out.println("Reflection Attack");
		System.out.println(s1 == s2);  // false -- singleton attacked
		
		
		}

}

class Singleton{
	private static volatile Singleton instance;  //Instruction reordering and visibility to all threads

	private Singleton() {
		if(instance != null) {
			throw new RuntimeException("use getInstance()"); // to avoid reflection attack
		}
	}
	public static Singleton getInstance() {
		if(instance == null) {
			synchronized(Singleton.class){			// class level lock. it will always check for the lock so added above null check
				if(instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	protected Object readResolve() {
		return getInstance();  // to avoid serialization attack
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
	    throw new CloneNotSupportedException();
	}

}


 enum SingletonEnum {
    INSTANCE;    // It can protect from reflection,serialisation and clone attack
}
// Enum singleton is the safest and simplest implementation. However, it cannot extend other classes and may not suit scenarios requiring inheritance or complex initialization.

 // usgae :: SingletonEnum obj = SingletonEnum.INSTANCE;

 
 
//Bill Pugh singleton 

//public class Singleton {   
//
//    private Singleton() {}
//
//    private static class Helper {
//        private static final Singleton INSTANCE = new Singleton();
//    }
//
//    public static Singleton getInstance() {
//        return Helper.INSTANCE;
//    }
//}




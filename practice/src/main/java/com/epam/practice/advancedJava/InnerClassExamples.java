package com.epam.practice.advancedJava;

public class InnerClassExamples {
	public static void main(String[] args) {
		OuterClass outer = new OuterClass();
		OuterClass.InnerClass inner = outer.new InnerClass();
		inner.innerMethod(); //member inner class can access outer class members directly
	
		OuterClass.StaticInnerClass staticInner =new OuterClass.StaticInnerClass();
		staticInner.staticInnerMethod(); //static inner class cannot access outer class members directly
		
		outer.methodWithLocalInnerClass(); //local inner class can only be accessed within the method where it is defined
	
		//Anonymous inner class
		AnonymousInterface anonymous = new AnonymousInterface() {
			@Override
			public void anonymousMethod() {
				System.out.println("Anonymous method implementation");
			}
			
			@Override
			public void anotherMethod() {
				System.out.println("Another method implementation");
			}
		};
		
		anonymous.anonymousMethod();
		anonymous.anotherMethod();
	
	}
				
}

class OuterClass {
	private int outerField;
	
	public void outerMethod() {
		System.out.println("Outer method");
	}
	
	class InnerClass {  //member inner class
		public void innerMethod() {
			System.out.println("Inner method accessing outer field: " + outerField);
			outerMethod();
		}
	}
	
	static class StaticInnerClass {  //static
		public void staticInnerMethod() {
			//System.out.println("Static inner method "+ outerFiled); can't access outer class members directly bcs it is static class
			 System.out.println("Static inner method");
		}
	}
	
	public void methodWithLocalInnerClass() {
		class LocalInnerClass {  //local inner class
			public void localInnerMethod() {
				System.out.println("Local inner method");
			}
		}
		LocalInnerClass localInner = new LocalInnerClass();
		localInner.localInnerMethod();
	}

//LocalInnerClass localInner = new LocalInnerClass(); // can't access local inner class outside the method where it is defined
}

interface AnonymousInterface {
	void anonymousMethod();
	void anotherMethod();
}


package com.epam.practice.java9features;

/*
 * JPMS (Java Platform Module System) is a module system introduced in Java 9 that allows developers to modularize their applications.
 * It provides a way to organize code into modules, which can have explicit dependencies on other modules and can control the visibility of their internal components.
 * The main features of JPMS include:
 * 1. Module Declaration: Developers can declare modules using the module-info.java file, which specifies the module's name, its dependencies on other modules, and the packages it exports.
 * 2. Strong Encapsulation: JPMS allows developers to control the visibility of their internal components by specifying which packages are exported and which are not. This helps to improve security and maintainability by preventing unintended access to internal code.
 * 3. Improved Performance: By modularizing applications, JPMS can help to improve performance by allowing the JVM to optimize the loading and execution of modules based on their dependencies.
 * 4. Better Tooling Support: JPMS provides better tooling support for developers, including improved IDE integration and support for modular JAR files.
 * 
 * Overall, JPMS is a powerful tool for developers to modularize their applications and improve the maintainability, security, and performance of their code.
 * 
 */
public class JPMSModuleExample {
	public static void main(String[] args) {
		System.out.println("This is an example of JPMS (Java Platform Module System) in Java 9.");
	}
}

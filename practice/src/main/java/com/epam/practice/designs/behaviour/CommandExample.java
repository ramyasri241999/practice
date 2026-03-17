package com.epam.practice.designs.behaviour;

/*
 * turns a request into a stand-alone object that contains all information about the request. 
 * This transformation lets you parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.
 * this is old style of programming, but it is still used in some cases, for example in GUI applications where you want to decouple the UI from the business logic.
 * find the latest example at the end of the file, it is more modern and uses lambda expressions.
 * Ex: Runnable is a functional interface that has a single method run(), and it can be used as a command.
 * Ex: callable, supplier,consumer, function, etc are also functional interfaces that can be used as commands.
 */
public class CommandExample {
	public static void main(String[] args) {
		//old way to implement command pattern using concrete command classes
		Light light = new Light();
		Command lightOnCommand = new LightOnCommand(light);
		Command lightOffCommand = new LightOffCommand(light);
		RemoteControl remote = new RemoteControl();
		remote.setCommand(lightOnCommand);
		remote.pressButton();
		remote.setCommand(lightOffCommand);
		remote.pressButton();
		
		// modern way to implement command pattern using lambda expressions and functional interfaces
		remote.setCommand(() -> light.on()); // using lambda expression to create a command that turns on the light
		remote.pressButton();
		
		Runnable lightOffCommandLambda = () -> light.off(); // using lambda expression to create a command that turns off the light
		lightOffCommandLambda.run(); // executing the command to turn off the light
		
		
	}
}

@FunctionalInterface
interface CommandFI{
	void execute();
}

interface Command{
		void execute();
}

class Light{
	public void on() {
		System.out.println("light is on");
	}
	public void off() {
		System.out.println("light is off");
	}
}

class LightOnCommand implements Command{
	Light light;
	public LightOnCommand(Light light) {
		this.light = light;
	}
	@Override
	public void execute() {
		light.on();
	}
}

class LightOffCommand implements Command{
	Light light;
	public LightOffCommand(Light light) {
		this.light = light;
	}
	@Override
	public void execute() {
		light.off();
	}
}

class RemoteControl{
	Command command;
	public void setCommand(Command command) {
		this.command = command;
	}
	public void pressButton() {
		if(command!=null) {
			command.execute();
		}
		else {
			System.out.println("No command assigned to the button.");
		}
	}
}

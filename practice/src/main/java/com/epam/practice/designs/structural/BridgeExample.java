package com.epam.practice.designs.structural;

public class BridgeExample {
	//Definition: Decouples an abstraction from its implementation, allowing them to vary independently.
	public static void main(String[] args) {
		device sonyTv = new SonyTv();
		Remote remote = new Remote(sonyTv);
		remote.turnOn(); // the remote turns on the Sony TV by delegating the action to the Sony TV's turnOn method
		remote.turnOff();
		
		device samsungTv = new SamsungTv();
		Remote advancedRemote = new AdvancedRemote(samsungTv);
		advancedRemote.turnOn();
		advancedRemote.turnOff();
		
		if(advancedRemote instanceof AdvancedRemote) {
			((AdvancedRemote) advancedRemote).setChannel(5);
		}
		
	}
}

interface device{
	void turnOn();
	void turnOff();
}

class SonyTv implements device{
	
	@Override
	public void turnOn() {
		System.out.println("Sony TV is turned on.");
	}
	
	@Override
	public void turnOff() {
		System.out.println("Sony TV is turned off.");
	}
}

class SamsungTv implements device{

	@Override
	public void turnOn() {
		System.out.println("Samsung TV is turned on.");
	}

	@Override
	public void turnOff() {
		System.out.println("Samsung TV is turned off.");
	}
	
}

class Remote{
	private device device;  // composition - Remote has a reference to a device, and it can work with any device that implements the device interface
	
	public Remote(device device) {
		this.device = device;
	}
	
	public void turnOn() {
		device.turnOn();  // the remote delegates the turn on action to the device
	}
	
	public void turnOff() {
		device.turnOff();  // the remote delegates the turn off action to the device
	}
}


class AdvancedRemote extends Remote{
	public AdvancedRemote(device device) {
		super(device);
	}
	
	public void setChannel(int channel) {
		System.out.println("Channel set to " + channel);
	}
}
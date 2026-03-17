package com.epam.practice.designs.behaviour;

import java.util.ArrayList;
import java.util.List;

/*
 * Mediator design Pattern : It defines an object that encapsulates how a set of objects interact. 
 * It promotes loose coupling by keeping objects from referring to each other explicitly, and it lets you vary their interaction independently.
 * Ex: A chat room can be a mediator between different users. The chat room will handle the communication between the users.
 * Spring Ex: ApplicationEventPublisher, it is a mediator between different components in the application. It allows different components to communicate with each other without knowing about each other.
 */

public class MediatorExample {
	public static void main(String[] args) {
	ChatRoomMediator chatRoom = new ChatRoom();
	ChatUser user1 = new ChatUserImpl(chatRoom, "Alice");
	ChatUser user2 = new ChatUserImpl(chatRoom, "Bob");
	ChatUser user3 = new ChatUserImpl(chatRoom, "Charlie");
	user1.sendMessage("Hello everyone!");
	chatRoom.addUser(user1);
	chatRoom.addUser(user2);
	chatRoom.addUser(user3);
	user2.sendMessage("Hi! I am Bob.");
	
	}
}

/*
 * responsibilities of the mediator:
 * 1. It defines the interface for communicating with Colleague objects.
 * 2. should be able to show messgae to the users in the chat room.
 * 3. should be able to add users to the chat room.
 */

interface ChatRoomMediator{
	void showMessage(String message, ChatUser user);
	void addUser(ChatUser user);
}
/*
 * ChatRoom responsibilities:
 * 1 In chatroom ypu view the message and the sender of the message.
 * 2. when a user sends a message, the chat room will show the message to all the other users in the chat room except the sender.
 * 3. when a user joins the chat room, the chat room will show a message that the user has joined the chat room.
 */

class ChatRoom implements ChatRoomMediator{
	private List<ChatUser> users = new ArrayList<>();
	
	@Override
	public void showMessage(String msg, ChatUser sender) {
		 if(!users.contains(sender)) {
	            System.out.println(sender.getName() + " is not in the chat room!");
	            return;
	        }

        for(ChatUser user : users) {
            if(user != sender) {
                user.receiveMessage(msg);
            }
        }
    }

	@Override
	public void addUser(ChatUser user) {
		System.out.println(user.getName()+ " has joined the chat room");
	}
}

/*
 * ChatUser responsibilities:
 * 1. when a user sends a message, the chat user will call the showMessage method of the chat room to show the message to all the other users in the chat room except the sender.
 * 2. when a user receives a message, the chat user will print the message to the console.
 */

abstract class ChatUser{
	protected ChatRoomMediator mediator;
	protected String name;
	
	public ChatUser(ChatRoomMediator mediator, String name) {
		this.mediator = mediator;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract  void sendMessage(String message) ;
	public abstract void receiveMessage(String message);
}

/*
 * Implementation of the ChatUser class, it will implement the sendMessage and receiveMessage methods to show the message to all the other users in the chat room except the sender, and to print the message to the console when a user receives a message.
 */

class ChatUserImpl extends ChatUser{
	public ChatUserImpl(ChatRoomMediator mediator, String name) {
		super(mediator, name);
	}

	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		mediator.showMessage(message, this);
	}

	@Override
	public void receiveMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println(name + " receives message: " + message);
		
	}
}

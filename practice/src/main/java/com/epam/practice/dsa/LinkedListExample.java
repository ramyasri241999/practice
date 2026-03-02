package com.epam.practice.dsa;

public class LinkedListExample {
	public static void main(String[] args) {
		
		MyLinkedList ll1 = new MyLinkedList();
		ll1.insertAtBegining(10);
		ll1.insertAtBegining(20);
		ll1.insertAtBegining(40);
		ll1.insertAtEnd(0);
		ll1.print();
		
		
		MyDoublyLinkedlist mdll = new MyDoublyLinkedlist();
		mdll.insertAtEnd("lakshmi");
		mdll.insertAtBeginning("sri");
		mdll.insertAtBeginning("ramya");
		mdll.insertAtEnd("Sunkara");
		mdll.printForward();
		mdll.printBackward();
	}
}


class Node{
	
	int data;
	Node next;
	
	
	public Node(int data) {
		this.data= data;
		this.next= null;
	}
}

class MyLinkedList{
	
	Node head;
	
	public void insertAtBegining(int data) {
		
		Node node = new Node(data);
		node.next = head;
		head = node;
	}
	
	public void insertAtEnd(int data) {
		Node node = new Node(data);
		
		if(head == null) {
			System.out.println("Head is null");
			head = node;
			return;
		}
		
		Node temp = head;
		while(temp.next!=null) {
			temp = temp.next;
		}
		
		temp.next = node;
	}
	
	
	public void print() {
		Node temp = head;
		while(temp!=null) {
			System.out.print(" "+temp.data + " -> ");
			temp = temp.next;
		}
		System.out.println("null");
	}
}



class DNode{
	 String data;
	 DNode prev;
	 DNode next;
	
	DNode(String data){
		this.data= data;
		this.prev= null;
		this.next= null;
	}
}


class MyDoublyLinkedlist{
	
	DNode head;
	DNode tail;
	 
	public void insertAtBeginning(String data) {
		DNode dnode = new DNode(data);
		
		if(head==null) {
			head = tail = dnode;
			return;
		}
		
		dnode.next= head;
		head.prev =dnode;
		head = dnode;
		
	}
	
	public void insertAtEnd(String data) {
		DNode dnode = new DNode(data);
		
		if(tail == null) {
			head= tail = dnode;
			return;
		}
		
		tail.next = dnode;
		dnode.prev = tail;
		tail = dnode;
	}
	
	public void printForward() {
		DNode temp = head;
		while(temp!=null) {
			System.out.print(" "+temp.data + " ->");
			temp = temp.next;
		}
		System.out.println("null");
	}
	
	public void printBackward() {
		DNode temp = tail;
		while(temp!=null) {
			System.out.print(" "+temp.data + " ->");
			temp = temp.prev;
		}
		System.out.println("null");
	}
}




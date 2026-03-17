package com.epam.practice.designs.behaviour;

import java.util.Stack;

/*
 * Memento design Pattern : It allows an object to save its state and restore it later. It is used to implement undo/redo functionality.
 * Real life Ex: A text editor can save the state of the document and restore it later. eg: ctrl + z, ctrl + y.
 * ex: hello ->  hello world -> undo -> hello -> redo -> hello world
 * Spring Ex: Spring Batch, it allows to save the state of the batch job and restore it later.
 * Undo/redo
 * Transaction rollback
`* Game checkpoints
 * IDE editors
 * participants: Originator -> Object whose state we save. Memento -> A snapshot of state. Caretaker ->stores memento
 */
public class MementoExample {
	public static void main(String[] args) {
		Editor editor = new Editor();
		History history = new History(); 
		
		editor.write("Hello"); //
		history.push(editor.save());
		
		editor.write("Hello world");
		history.push(editor.save());
		
		editor.write("Hello Ramya");
		//history.push(editor.save());
		
		editor.print();
		
		editor.restore(history.pop());
		
		editor.print();
		
		editor.restore(history.pop());
		
		editor.print();
		
	}
}

//Originator
class Editor{
	private String content;
	
	public void write(String text) {
		this.content = text;
	}
	
	public EditorMemento save() {
		return new EditorMemento(content);
	}
	
	public void restore(EditorMemento momento) {
		content = momento.getSavedContent();
	}
	
	public void print() {
		System.out.println(content);
	}
}

class EditorMemento {
	private final String content;
	public EditorMemento(String content) {
		this.content = content;
	}
	
	public String getSavedContent() {
		return content;
	}
}

class History{
	private Stack<EditorMemento> history = new Stack<>();
	
	public void push(EditorMemento memento) {
		history.push(memento);
	}
	
	public EditorMemento pop() {
		return history.pop();
	}
}

/*
 * Best Practices

✔ Make memento immutable
✔ Limit history size
✔ Avoid storing large objects repeatedly
✔ Use snapshot compression when needed
 */

/*
 * Common Mistakes
Caretaker modifying memento

Caretaker should only store, not modify.

Storing too many snapshots

Can cause memory issues.

Exposing internal state publicly

Breaks encapsulation.
 */









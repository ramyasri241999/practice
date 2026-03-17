package com.epam.practice.designs.structural;

import java.util.ArrayList;
import java.util.List;

public class CompositeExample { //Definition: Composes objects into tree structures to represent part-whole hierarchies, allowing clients to treat individual objects and compositions of objects uniformly.
	public static void main(String[] args) {
		FileSystemComponent file1 = new File("file1.txt"); //leaf node
		FileSystemComponent file2 = new File("file2.txt");
		
		Folder folder1 = new Folder("folder1"); //composite node
		folder1.add(file1);// the folder1 contains file1 and file2, and it can also contain other folders, which is the main advantage of the composite pattern, it allows us to treat individual objects and compositions of objects uniformly.
		folder1.add(file2);
		
		FileSystemComponent file3 = new File("file3.txt");
		
		Folder folder2 = new Folder("folder2");
		folder2.add(folder1);// the folder2 contains folder1, which in turn contains file1 and file2, and it also contains file3, which demonstrates the recursive nature of the composite pattern, where a folder can contain other folders, and each folder can contain files or other folders.
		folder2.add(file3);
		
		folder2.showDetails();
	}
}

interface FileSystemComponent{
	void showDetails();    // this method will be implemented by both File and Folder classes, and it will be used to display the details of the file or folder, and it will be called recursively for folders to display the details of all the files and subfolders contained within it.
}


class File implements FileSystemComponent{ // leaf node in the composite pattern, it represents a file in the file system, and it implements the showDetails method to display the details of the file
	
	private String name;
	public File(String name) {
		this.name = name;
	}

	@Override
	public void showDetails() {
		System.out.println("File: " + name);
	}
	
}

class Folder implements FileSystemComponent{ // composite node in the composite pattern, it represents a folder in the file system, and it contains a list of FileSystemComponent objects (which can be either files or subfolders), and it implements the showDetails method to display the details of the folder and all its contents recursively.
	
	private String name;
	private List<FileSystemComponent> components = new ArrayList<>();
	
	public Folder(String name) {
		this.name = name;
	}
	
	public void add(FileSystemComponent component) {
		components.add(component);
	}
	
	
	@Override
	public void showDetails() {
		for(FileSystemComponent component : components) {
			component.showDetails();
		}
	}
}



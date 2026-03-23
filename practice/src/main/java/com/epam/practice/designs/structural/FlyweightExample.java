package com.epam.practice.designs.structural;

import java.util.HashMap;
import java.util.Map;

public class FlyweightExample {
	/*Definition: Uses sharing to support large numbers of fine-grained objects efficiently.
	 * Spring ex: Spring uses the Flyweight pattern to manage beans in the application context. 
	 * When a bean is requested, Spring checks if an instance of that bean already exists in the context. 
	 * If it does, Spring returns the existing instance instead of creating a new one. 
	 * This allows Spring to efficiently manage memory and resources by sharing instances of beans across the application.
	 * SpringBoot Ex: Spring Boot uses the Flyweight pattern to manage the configuration properties of the application.
	*/
	public static void main(String[] args) {
		String document = "hello world";
		int row = 1;
		int column = 1;
		
		for(char character : document.toCharArray()) {  // iterating through each character in the document
			CharacterFlyweight flyweight = CharacterFactory.getcharacter(character);  // getting the flyweight object for the character from the factory
			flyweight.display(row, column);  // displaying the character at the specified row and column
			column++;  // incrementing the column for the next character
		}
	}
}

interface CharacterFlyweight {
	void display(int row, int column);
}

class TextCharacter implements CharacterFlyweight {

    private final char symbol; // intrinsic state

    public TextCharacter(char symbol) {
        this.symbol = symbol;
    }

    public void display(int row, int column) {
        System.out.println(symbol + " at " + row + "," + column);
    }
}

class CharacterFactory {
	private static final Map<Character, CharacterFlyweight> characterMap = new HashMap<>();  // a map to store the flyweight objects, where the key is the character and the value is the flyweight object

	public static CharacterFlyweight getcharacter(char character) {
		CharacterFlyweight flyweight = characterMap.get(character);// check if the flyweight object already exists in the map
		if(!characterMap.containsKey(character)) {
			characterMap.put(character, new TextCharacter(character)); // if the flyweight object does not exist, create a new one and add it to the map	
		}
		return characterMap.get(character); // return the flyweight object from the map
	}
}

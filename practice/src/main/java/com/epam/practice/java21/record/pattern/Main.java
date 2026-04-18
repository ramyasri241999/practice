package com.epam.practice.java21.record.pattern;

public class Main {

    public static void main(String[] args) {

        var product1 = new FoodItem("Apple", 0.99);
        var product2 = new DrinkItem("Water", 1.29);

        printDetails(product1);
        printDetails(product2);

//        printDetailsRecordPattern(product1);
//        printDetailsRecordPattern(product2);

    }

    private static void printDetails(Item item) {
        if(item instanceof FoodItem foodItem) { //java 16
            //var foodItem = (FoodItem) item;
            var name = foodItem.name();
            var price = foodItem.price();
            System.out.println("Food item with name " + name + " has price " + price);
        } else if(item instanceof DrinkItem) {
            var drinkItem = (DrinkItem) item; //pre-java 16
            var name = drinkItem.name();
            var price = drinkItem.price();
            System.out.println("Drink item with name " + name + " has price " + price);
        }
    }

//    private static void printDetailsRecordPattern(Item item) {
//        if(item instanceof FoodItem(var name, var price)) {
//            System.out.println("Food item with name " + name + " has price " + price);
//        } else if(item instanceof DrinkItem(String name, double price)) {
//            System.out.println("Drink item with name " + name + " has price " + price);
//        }
//    }

}
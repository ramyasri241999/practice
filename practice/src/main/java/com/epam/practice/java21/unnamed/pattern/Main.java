package com.epam.practice.java21.unnamed.pattern;

public class Main {

    public static void main(String[] args) {

        var house = new House("123 Main St", 3, true);

        printNumberOfBedrooms(house);
        //printNumberOfBedroomsUnnamed(house);
    }

    private static void printNumberOfBedrooms(Building building) {
        if (building instanceof House house) {
            System.out.println("Number of bedrooms: " + house.numberOfBedrooms());
        }
    }

//    private static void printNumberOfBedroomsUnnamed(Building building) {
//        if (building instanceof House(_, var numberOfBedrooms, _)) {
//            System.out.println("Number of bedrooms: " + numberOfBedrooms);
//        }
//    }
}
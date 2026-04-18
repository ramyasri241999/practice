//package com.epam.practice.java21.unnamed.variable;
//
//import java.util.List;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        List<String> items = List.of(
//                "1",
//                "2",
//                "3"
//        );
//
//        var basketTotal = 0;
//
//        for (String item : items) {
//            basketTotal ++;
//        }
//
////        for (String _ : items) {
////            basketTotal ++;
////        }
//
//        System.out.println("Basket total: " + basketTotal);
//
//        var lastItem = items.getLast();
//        try {
//            int i = Integer.parseInt(lastItem);
//            System.out.println("Last item: " + i);
//        } catch (NumberFormatException e) {
//            System.out.println("Bad number: " + lastItem);
//        }
//
////        try {
////            int i = Integer.parseInt(lastItem);
////            System.out.println("Last item: " + i);
////        } catch (NumberFormatException _) {
////            System.out.println("Bad number: " + lastItem);
////        }
//
//
//
//    }
//
//
//
//}
//package com.epam.practice.java21.switch1.pattern;
//
//public class SwitchPatternMatchingDemo {
//
//    public static void run() {
//        System.out.println("-- Pattern Matching for switch (JEP 441) --");
//
//        printTypeInfo(42);
//        printTypeInfo("Java 21");
//        printTypeInfo(3.14);
//        printTypeInfo(null);
//    }
//
//    private static void printTypeInfo(Object obj) {
//        String description = switch (obj) {
//            case null -> "It is null";
//            case String s when !s.isEmpty() -> "Non-empty String: '" + s + "'";
//            case String s -> "Empty String";
//            case Integer i when i > 0 -> "Positive Integer: " + i;
//            case Integer i -> "Non-positive Integer: " + i;
//            case Double d -> "Double: " + d;
//            default -> "Some other type: " + obj.getClass().getSimpleName();
//        };
//
//        System.out.println(description);
//    }
//
//    private static void printTypeInfoPre21(Object obj) {
//        String description;
//
//        if (obj == null) {
//            description = "It is null";
//        } else if (obj instanceof String s) { // Pattern matching (Java 16+)
//            if (!s.isEmpty()) {
//                description = "Non-empty String: '" + s + "'";
//            } else {
//                description = "Empty String";
//            }
//        } else if (obj instanceof Integer i) {
//            if (i > 0) {
//                description = "Positive Integer: " + i;
//            } else {
//                description = "Non-positive Integer: " + i;
//            }
//        } else if (obj instanceof Double d) {
//            description = "Double: " + d;
//        } else {
//            description = "Some other type: " + obj.getClass().getSimpleName();
//        }
//
//        System.out.println(description);
//    }
//}
//
//
//

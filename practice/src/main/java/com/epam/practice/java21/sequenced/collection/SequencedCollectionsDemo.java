//package com.epam.practice.java21.sequenced.collection;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.SequencedCollection;
//import java.util.SequencedMap;
//
//public class SequencedCollectionsDemo {
//
//    public static void main() {
//        System.out.println("-- Sequenced Collections (JEP 431) --");
//
//        SequencedCollection<String> names = new ArrayList<>();
//        names.addFirst("Alice");
//        names.addLast("Bob");
//        names.addLast("Charlie");
//
//        System.out.println("Names: " + names);
//        System.out.println("First: " + names.getFirst());
//        System.out.println("Last : " + names.getLast());
//
//        System.out.print("Reverse iteration: ");
//        names.reversed().forEach(n -> System.out.print(n + " "));
//        System.out.println();
//
//        SequencedMap<Integer, String> map =
//                (SequencedMap<Integer, String>) new LinkedHashMap<Integer, String>();
//        map.putFirst(2, "two");
//        map.putLast(3, "three");
//        map.putFirst(1, "one");
//
//        System.out.println("Map iteration order: " + map);
//        System.out.println("First entry: " + map.firstEntry());
//        System.out.println("Last entry : " + map.lastEntry());
//
//        System.out.print("Reversed map keys: ");
//        for (Map.Entry<Integer, String> e : map.reversed().entrySet()) {
//            System.out.print(e.getKey() + " ");
//        }
//        System.out.println();
//    }
//}
//

package com.epam.practice.java21.sequenced.collection;

import java.util.*;

public class CollectionIssue {

    public static void main(String[] args) {
        System.out.println("ArrayList example");
        List<String> arrayList = new ArrayList<>(Arrays.asList("Banana", "Cherry", "Date"));
        arrayList.add(0, "Apple");
        arrayList.add(arrayList.size(),"Elderberry");

        System.out.println(arrayList);
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(arrayList.size() - 1));

        System.out.println("\nDeque example");
        ArrayDeque<String> deque = new ArrayDeque<>(Arrays.asList("Banana", "Cherry", "Date"));
        deque.addFirst("Apple");
        deque.addLast("Elderberry");

        System.out.println(deque);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());

        System.out.println("\nLinkedHashSet example");
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList("Banana", "Cherry", "Date"));
        linkedHashSet.add("Elderberry");

        System.out.println(linkedHashSet);
        System.out.println(linkedHashSet.iterator().next());
        System.out.println(linkedHashSet.toArray()[linkedHashSet.size() - 1]);
    }

}
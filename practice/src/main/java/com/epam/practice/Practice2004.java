package com.epam.practice;

import java.time.LocalDateTime;

import java.util.*;

import java.util.stream.Collectors;

public class Practice2004 {

    static class Order {

        private int id;

        private LocalDateTime date;

        private List<OrderItem> items;

        // Constructor

        public Order(int id, LocalDateTime date, List<OrderItem> items) {

            this.id = id;

            this.date = date;

            this.items = items;

        }

        // Getters

        public int getId() { return id; }

        public LocalDateTime getDate() { return date; }

        public List<OrderItem> getItems() { return items; }

    }

    static class OrderItem {

        private String name;

        private int quantity;

        private Double price;

        // Constructor

        public OrderItem(String name, int quantity, Double price) {

            this.name = name;

            this.quantity = quantity;

            this.price = price;

        }

        // Getters

        public String getName() { return name; }

        public int getQuantity() { return quantity; }

        public Double getPrice() { return price; }

    }

    public static void main(String[] args) {

        List<Order> orders = createDummyOrders();

        // System.out.println(); for copy/paste purposes

//       1. Calculate total sales
// 
//2.Find average price of products
// 
//3.Calculate total number of products sold
// 
//4.Find top 3 most sold products
// 
//5. Print each product name with total quantity

    }

    private static List<Order> createDummyOrders() {

        return Arrays.asList(

            new Order(1, LocalDateTime.of(2025, 1, 15, 10, 0), Arrays.asList(

                new OrderItem("ProductA", 2, 10.0),

                new OrderItem("ProductB", 1, 25.5)

            )),

            new Order(2, LocalDateTime.of(2025, 1, 16, 11, 30), Arrays.asList(

                new OrderItem("ProductA", 1, 15.0),

                new OrderItem("ProductC", 5, 5.0)

            )),

            new Order(3, LocalDateTime.of(2025, 2, 20, 14, 0), Arrays.asList(

                new OrderItem("ProductB", 3, 20.5),

                new OrderItem("ProductD", 1, 50.0)

            )),

            new Order(4, LocalDateTime.of(2025, 3, 5, 9, 15), Arrays.asList(

                new OrderItem("ProductA", 4, 5.0)

            )),

            new Order(5, LocalDateTime.of(2025, 3, 25, 18, 45), Arrays.asList(

                new OrderItem("ProductC", 2, 10.0),

                new OrderItem("ProductB", 1, 30.5)

            ))

        );

    }

}
 
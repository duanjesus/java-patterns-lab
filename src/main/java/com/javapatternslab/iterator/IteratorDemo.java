package com.javapatternslab.iterator;

public class IteratorDemo {

    public static void main(String[] args) {
        OrderHistory history = new OrderHistory();
        history.add(new Order("ORD-1", "PAID"));
        history.add(new Order("ORD-2", "CREATED"));
        history.add(new Order("ORD-3", "PAID"));
        history.add(new Order("ORD-4", "CANCELLED"));

        System.out.println("-- Iterator: every order --");
        for (Order order : history) {
            System.out.println(order);
        }

        System.out.println("-- Iterator: paid orders only --");
        for (Order order : history.paidOrders()) {
            System.out.println(order);
        }
    }
}

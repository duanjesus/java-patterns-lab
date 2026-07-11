package com.javapatternslab.state;

public class StateDemo {

    public static void main(String[] args) {
        System.out.println("-- State: an order walking through its valid transitions --");

        OrderContext order = new OrderContext();
        System.out.println(order.pay());
        System.out.println(order.ship());
        System.out.println(order.deliver());
        System.out.println("Final state: " + order.getStateName());

        System.out.println();
        System.out.println("-- A second order, cancelled right away --");
        OrderContext cancelledOrder = new OrderContext();
        System.out.println(cancelledOrder.cancel());

        System.out.println();
        System.out.println("-- Invalid transition: shipping a freshly created order --");
        OrderContext freshOrder = new OrderContext();
        System.out.println(freshOrder.ship());
    }
}

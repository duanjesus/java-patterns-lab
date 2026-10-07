package com.javapatternslab.prototype;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderTemplate implements Prototype<OrderTemplate> {

    private String name;
    private final ShippingAddress shippingAddress;
    private final List<OrderLine> lines = new ArrayList<>();

    public OrderTemplate(String name, ShippingAddress shippingAddress) {
        this.name = name;
        this.shippingAddress = shippingAddress;
    }

    public OrderTemplate addLine(String sku, int quantity, BigDecimal unitPrice) {
        lines.add(new OrderLine(sku, quantity, unitPrice));
        return this;
    }

    public void rename(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public List<OrderLine> getLines() {
        return List.copyOf(lines);
    }

    public BigDecimal getTotal() {
        return lines.stream()
                .map(OrderLine::subtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public OrderTemplate copy() {
        // The address is mutable, so it is copied too; OrderLine is an immutable record and can be shared.
        OrderTemplate copy = new OrderTemplate(name, shippingAddress.copy());
        copy.lines.addAll(lines);
        return copy;
    }
}

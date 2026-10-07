package com.javapatternslab.memento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The originator: the only class that can create a snapshot of its state or read one back.
 */
public class OrderDraft {

    private final List<DraftItem> items = new ArrayList<>();
    private String couponCode = "";
    private String shippingAddress = "";

    public void addItem(String name, BigDecimal price) {
        items.add(new DraftItem(name, price));
    }

    public boolean removeItem(String name) {
        return items.removeIf(item -> item.name().equals(name));
    }

    public void applyCoupon(String couponCode) {
        this.couponCode = couponCode;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<DraftItem> getItems() {
        return List.copyOf(items);
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(DraftItem::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Snapshot save() {
        return new Snapshot(List.copyOf(items), couponCode, shippingAddress);
    }

    public void restore(Snapshot snapshot) {
        items.clear();
        items.addAll(snapshot.items);
        couponCode = snapshot.couponCode;
        shippingAddress = snapshot.shippingAddress;
    }

    /**
     * The memento. Its constructor and fields are private, so code outside OrderDraft
     * can hold and pass a Snapshot around but cannot build, read or change one.
     */
    public static final class Snapshot {

        private final List<DraftItem> items;
        private final String couponCode;
        private final String shippingAddress;

        private Snapshot(List<DraftItem> items, String couponCode, String shippingAddress) {
            this.items = items;
            this.couponCode = couponCode;
            this.shippingAddress = shippingAddress;
        }
    }
}

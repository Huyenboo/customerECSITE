package com;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Product product;
    private int quantity;
    private String deliveryDate;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.deliveryDate = "未定"; // default value
    }

    public CartItem(Product product, int quantity, String deliveryDate) {
        this.product = product;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getSubtotal() {
        return product.getProUnitNum() * quantity;
    }
}

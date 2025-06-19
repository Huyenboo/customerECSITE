package com;

import java.io.Serializable;
import java.sql.Date;

public class CartItem implements Serializable {
    private Product product;
    private int quantity;
    private Date deliveryDate;

    public CartItem() {}

    public CartItem(Product product, int quantity, Date deliveryDate) {
        this.product = product;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getSubtotal() {
        return product.getProUnitNum() * quantity;
    }
}

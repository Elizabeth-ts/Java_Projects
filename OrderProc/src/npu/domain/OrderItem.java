/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npu.domain;

import java.util.Objects;

/**
 *
 * @author Arvin
 */
public class OrderItem {

    private Product product;
    private long quantity;

    public OrderItem(Product product) {
        this.product = product;
        this.quantity = 0;
    }

    public OrderItem(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderItem(final OrderItem obj) {
        this(new Product(obj.product), obj.quantity);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "product=" + product + ", quantity=" + quantity + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderItem other = (OrderItem) obj;
        return product.equals(other.product);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npu.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arvin
 */
public class Order {

    private Customer customer;
    private final List<OrderItem> orderList = new ArrayList<>();
    private double subTotal = 0.0, tax, total;

    public Order(Customer customer, List<OrderItem> orderList) {
        this.customer = customer;
        for (OrderItem o : orderList) {
            addItem(o);
        }
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItem> getOrderList() {
        return orderList;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" + "customer=" + customer + ", orderList=" + orderList + ", subTotal=" + subTotal + ", tax=" + tax + ", total=" + total + '}';
    }

    public void addItem(OrderItem item) {
        int index = orderList.indexOf(item);
        if (index == -1) {
            orderList.add(item);
        } else {
            OrderItem oldItem = orderList.get(index);
            oldItem.setQuantity(item.getQuantity() + oldItem.getQuantity());
        }
        subTotal += (item.getProduct().getPrice() * item.getQuantity());
    }

    public void removeProduct(Product prod) {
        int index = orderList.indexOf(new OrderItem(prod));
        if (index == -1) {
            return;
        }
        OrderItem oldItem = orderList.get(index);
        subTotal -= (oldItem.getQuantity() * oldItem.getProduct().getPrice());
        orderList.remove(index);
    }

}

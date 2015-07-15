/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npu.domain;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Arvin
 */
public class Product {

    private String name;
    private double price;

    public Product() {

    }

    public Product(String name) {
        this.name = name;
        this.price = 0.0;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(final Product product) {
        this(product.name, product.price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", price=" + price + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        return other.name.equalsIgnoreCase(name);
    }
}

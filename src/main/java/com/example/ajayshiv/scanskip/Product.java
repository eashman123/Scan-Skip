package com.example.ajayshiv.scanskip;

import java.io.Serializable;

public class Product implements Serializable {
    double price;
    String name;
    public Product(String name, double price) {
        this.price = price;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ": $" + price;
    }
}

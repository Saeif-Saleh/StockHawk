package com.udacity.stockhawk.ui;

/**
 * Created by Sayyaf on 12/17/2016.
 */

public class History {
    private  Long date;
    private double price;

    public History(Long date, double price) {
        this.date = date;
        this.price = price;
    }

    public Long getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }
}

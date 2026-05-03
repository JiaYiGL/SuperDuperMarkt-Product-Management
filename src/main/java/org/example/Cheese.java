package org.example;

import java.time.LocalDate;

public class Cheese extends Product {

    public Cheese(String name, double basePrice, int quality, LocalDate dueDate) {
        super(name, basePrice,quality, dueDate);
    }

    public double getPrice() {
        double price = basePrice + quality * 0.10;
        return Math.round(price * 100.0) / 100.0;
    }
    public void update(LocalDate date){
        quality -=1;
        if(isBad(date)){
            remove();
        }
    }

    public boolean isBad(LocalDate date){
        return quality <= 30 || !date.isBefore(dueDate);
    }

}

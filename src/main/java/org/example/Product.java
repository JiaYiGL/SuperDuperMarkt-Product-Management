package org.example;

import java.time.LocalDate;

public abstract class Product {
    protected String name;
    protected double basePrice;
    protected boolean removed = false;
    protected LocalDate dueDate;
    protected int quality;

    public Product(String name, double basePrice, int quality, LocalDate dueDate) {
        this.name = name;
        this.basePrice = basePrice;
        this.dueDate = dueDate;
        this.quality = quality;
    }

    // aktualisiert den Qualitätszutand vom Produkt
    public abstract void update(LocalDate time);

    // genaue Preisberechnung wird in Unterklassen definiert
    public abstract double getPrice();

    public abstract boolean isBad(LocalDate date);


    public void remove() {
        removed = true;
    }

    public String getName() {
        return name;
    }
    public double getBasePrice() {
        return basePrice;
    }

    public boolean isRemoved() {
        return removed;
    }

    public int getQuality() {
        return quality;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    @Override
    public String toString() {
        return  "Name: " + getName() + " Quality: " + getQuality() + " Price: " + getPrice() ;
    }


}

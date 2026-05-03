package org.example;
import java.time.LocalDate;

public class Wine extends Product {
    final double PRICE = Math.round(basePrice * 100.0) / 100.0;
    private int daysAfterDueDate = 0;
    public Wine(String name, double basePrice, int quality, LocalDate dueDate) {
        super(name, basePrice, quality, dueDate);

    }

    public double getPrice() {
        return PRICE;
    }


    public void update(LocalDate date){
        if(dueDate.isBefore(date)){

            daysAfterDueDate +=1;
            if(quality< 50 && (daysAfterDueDate % 10 == 0)){
                quality +=1;
            }
        }

        if(isBad(date)){
            remove();
        }
    }

    public boolean isBad(LocalDate date){
        return quality <= 0 ;
    }
}

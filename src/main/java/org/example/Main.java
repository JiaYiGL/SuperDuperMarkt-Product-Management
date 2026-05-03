package org.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    // Format für die Ausgabe der Simulation
    static final String FORMAT = "%-12s %-10s %10d %-12s %8.2f %-16s%n";
    public static void main(String[] args) {

        List<Product> regal = new ArrayList<>();
        // Startwert-Datum auf dem aktuellen Tag setzen
        LocalDate today = LocalDate.now();

        Cheese cheese1 = new Cheese("Gouda", 2.0,40, today.plusDays(10)); // Tag 9 schlecht
        Cheese cheese2 = new Cheese("Brie", 2.5,35, today.plusDays(5));   //  Tag 4 : schlecht
        Cheese cheese3 = new Cheese("Cheddar", 5.0, 31, today.plusDays(10)); // Tag 0 : Schlecht
        Cheese cheese4 = new Cheese("Parmesan", 3.0, 1000, today.plusDays(40)); // Tag 40 : Schlecht

        Wine wine1 = new Wine("Merlot", 10.0,20, today.plusDays(3));      // erhöht Qualität ab Tag 13
        Wine wine2 = new Wine("Chianti",12.0, 45, today.minusDays(1)); // erhöht Qualität ab Tag 9

        Cheese cheese5 =   new Cheese("Parmesan", 3.0, 30, today.plusDays(40)); // 30 >= Qualität - Wird nicht im Regal gestellt
        Wine wine3 = new Wine("Chianti",12.0, 0, today.plusDays(1));//  Negatives Qualität - Wird nicht im Regal gestellt
        // Einräumen
        einraeumen(regal,cheese1);
        einraeumen(regal,cheese2);
        einraeumen(regal,cheese3);
        einraeumen(regal,cheese4);
        einraeumen(regal,cheese5);
        einraeumen(regal,wine1);
        einraeumen(regal,wine2);
        einraeumen(regal,wine3);

        // 60 Tagen - Simulation
        for (int day= 0; day < 60; day++) {
            LocalDate currentDay = today.plusDays(day);

            System.out.println("\nTag " + day + " ( " + currentDay +" ) ");
            printHeaderTitle();
            // Produkte können im Zustand - "Gut", "Bitte entfernen" oder "Entfernt" sein.
            for (Product p : regal) {
                if (p.isRemoved()) {
                    printRemoved(p);
                    continue;
                }
                p.update(currentDay);

                if (p.isBad(currentDay)) {
                    // im aktuellen Tag zu entfernen
                    printToRemove(p);
                    // das Produkt entfernen
                    p.remove();
                } else {
                        printProduct(p);
                    }
                }
            }
        }
    public static void einraeumen (List<Product> regal, Product p) {
        if (p instanceof Cheese && p.quality <= 30) {
            //throw new IllegalArgumentException("Käse: " + p.getName() + " erfüllt Mindestqualität von 30 nicht!");
            System.out.println("Käse: " + p.getName() + " ist zu schlecht fürs Einräumen!");
            return;
        }
        if (p instanceof Wine && p.quality <= 0) {
            // throw new IllegalArgumentException("Wein: " + p.getName() + " erfüllt Mindestqualität von 1 nicht!");
            System.out.println("Wein: " + p.getName() + " ist zu schlecht fürs Einräumen!");
            return;
        }
        regal.add(p);
    }
    private static void printHeaderTitle() {
        System.out.printf("%-12s %-10s %-10s %-12s %-8s %-16s%n",
                "Name","Typ", "Qualität", "Stichtag", "Preis", "Status"
        );
        System.out.println("------------------------------------------------------------------");
    }

    private static void printProduct(Product p) {
        System.out.printf(FORMAT,
                p.getName(),
                getType(p),
                p.getQuality(),
                p.getDueDate(),
                p.getPrice(),
                "Gut"
        );
    }
    private static void printToRemove(Product p) {
        System.out.printf(FORMAT,
                p.getName(),
                getType(p),
                p.getQuality(),
                p.getDueDate(),
                p.getPrice(),
                "Bitte Entfernen!"
        );
    }
    private static void printRemoved(Product p) {
        System.out.printf(FORMAT,
                p.getName(),
                getType(p),
                p.getQuality(),
                p.getDueDate(),
                p.getPrice(),
                "Entfernt"
        );
    }

    private static Object getType(Product p) {

        if (p instanceof Cheese) {
            return "Käse";
        } else return "Wein";
    }
}


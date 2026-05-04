import org.example.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LogicTest {
    private LocalDate today;
    private List<Product> regal;
    @BeforeEach
    void setUp() {
        // Wird vor jedem @Test ausgeführt
        today = LocalDate.now();
        regal = new ArrayList<>();
    }
    @Test
    @DisplayName("Käse verliert täglich an Qualität")
    void testCheeseQualityLoss() {
        Cheese cheese = new Cheese("Gouda", 2.0, 40, today.plusDays(10));
        cheese.update(today.plusDays(1));
        assertEquals(39, cheese.getQuality());
    }
    @Test
    @DisplayName("Käse-Preis Änderung")
    void testCheesePriceChange() {
        Cheese  cheese = new Cheese("Gouda", 2.0, 40, today);
        double price = cheese.getPrice();
        cheese.update(today.plusDays(1));
        double newPrice = cheese.getBasePrice() + cheese.getQuality() * 0.10;
        newPrice = Math.round(newPrice * 100.0) / 100.0;
        assertEquals(newPrice, cheese.getPrice());
    }
    @Test
    @DisplayName("Wein gewinnt ab Stichtag alle 10 Tage an Qualität")
    void testWineQualityGain(){
        Wine wine = new Wine("Chianti",12.0, 45, today);
        // Simuliere 10 Tage
        for (int day = 0; day <= 10; day++) {
            wine.update(today.plusDays(day));
        }
        assertEquals(46, wine.getQuality());
    }
    @Test
    @DisplayName("Wein-Preis darf sich nach dem Einräumen nicht ändern")
    void testWinePriceStability() {
        Wine wine = new Wine("Alt-Wein", 20.0, 10, LocalDate.now().minusDays(20));
        final double price = wine.getPrice();
        assertEquals(20.0, price);
        // Simuliere 10 Tage
        for (int day = 0; day <= 10; day++) {
            wine.update(today.plusDays(day));
        }
        assertEquals(20.0, wine.getPrice());
    }
    @Test
    @DisplayName("Ungültige Qualität beim Einräumen sollte abgelehnt werden")
    void testInvalidQualityCheeseInsertion() {
        Cheese badCheese = new Cheese("Alter Käse", 1.0, 20, today.plusDays(5));
        Cheese goodCheese = new Cheese("Frischer Käse", 1.0, 50, today.plusDays(5));
        Main.einraeumen(regal, badCheese);
        Main.einraeumen(regal, goodCheese);
        assertTrue(regal.contains(goodCheese));
        assertFalse(regal.contains(badCheese));
    }

    @Test
    @DisplayName("Ungültige Qualität beim Einräumen sollte abgelehnt werden")
    void testInvalidQualityWineInsertion() {
        Wine badWine = new Wine("schlechter Wein", 1.0, -1, today.plusDays(5));
        Wine goodWine = new Wine(" Wein", 1.0, 50, today.plusDays(5));
        Main.einraeumen(regal, badWine);
        Main.einraeumen(regal, goodWine);
        assertTrue(regal.contains(goodWine));
        assertFalse(regal.contains(badWine));
    }
}

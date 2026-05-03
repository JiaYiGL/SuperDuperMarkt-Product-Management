# SuperDuperMarkt - Produktverwaltung
Dieses Projekt ist eine kurze Lösung für die Coding Challenge zur Verwaltung von Produkten (Käse und Wein) eines Supermarktes.
Es simuliert die tägliche Veränderung von Qualität und Preis sowie die automatische Warnung/Aussortierung minderwertiger Ware.

## Kernfunktionen:
*   ** Qualitätsupdates:** Simulation der täglichen Wertänderung.
*   **Dynamische Preisberechnung:** Preisermittlung basierend auf der aktuellen Qualität für die Käse-Produkte ($Grundpreis + 0,10€ \cdot Qualität$).
*   **Eingangsüberprüfung:** Sicherstellung, dass nur Produkte, die den Qualitätsstandards entsprechen, ins Regal gelangen.
*   **Wein-logik:** Fixierung des Preises beim Einräumen und Qualitätssteigerung ab einem definierten Stichtag.

## 📦 Projektstruktur
Das Projekt folgt der Standard-Maven-Struktur:
*   `src/main/java/org/example/`: Enthält die Klasse (`Product`, `Cheese`, `Wine`) und die Simulations-Klasse (`Main`).
*   `src/test/java/org/example/`: Enthält automatisierte Unit-Tests im (`LogicTest`).
## 🛠 Tech Stack 
*   **Sprache:** Java 21 
*   **IDE:** IntelliJ IDEA
*   Maven
### Kompilieren und Testen
Um die Lösung zu bauen und die Unit-Tests auszuführen, nutze folgende Befehle:
```bash
# Projekt bauen
mvn clean compile

# Unit-Tests ausführen
mvn test

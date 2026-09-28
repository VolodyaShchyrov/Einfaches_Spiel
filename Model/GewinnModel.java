package vshchyrov.Einfaches_Spiel.Model;

/**
 *
 * @author Volodymyr Shchyrov
 * @version 28.09.26
 *
 * Das Model verwaltet die gesamte Geschäftslogik und den Zustand
 * des Zahlen-Gewinnspiels (MVC-Prinzip: Model).
 * Es enthält keine UI-Elemente und ist völlig unabhängig von der Ansicht.
 */
public class GewinnModel {
    private int gesamtPunkte;
    private int spielerZahl;
    private int computerZahl;
    private int rundenErgebnis;

    /**
     * Konstruktor: Initialisiert ein neues Spiel
     * mit einem Startkapital von 30 Gesamtpunkten.
     */
    public GewinnModel() {
        gesamtPunkte = 30; // Start der Punkte für den Spieler
    }

    /**
     * Generiert eine zufällige Ganzzahl für den Computer zwischen 1 und 9.
     */
    public void berechneComputerZahl() {
        computerZahl = (int) (Math.random() * 9) + 1;
    }

    /**
     * Führt eine Spielrunde aus:
     * 1. Speichert die Spielereingabe.
     * 2. Generiert die Computerzahl.
     * 3. Berechnet das Rundenergebnis basierend auf dem Abstand.
     * 4. Aktualisiert den Punktestand.
     *
     * @param spielerZahl Die vom Spieler eingegebene Zahl (1-9)
     */
    public void berechneRunde(int spielerZahl) {
        this.spielerZahl = spielerZahl;
        berechneComputerZahl();

        // Exakter Treffer gibt die meisten Punkte
        if (spielerZahl == this.computerZahl) {
            this.rundenErgebnis = 20;
        }
        // Knapp daneben (Differenz von 1) gibt kleine Pluspunkte
        else if (Math.abs(spielerZahl - this.computerZahl) == 1) {
            this.rundenErgebnis = 5;
        }
        // Falsch geraten gibt Punktabzug
        else {
            this.rundenErgebnis = -10;
        }

        // Gesamtpunkte um das Rundenergebnis aktualisieren
        this.gesamtPunkte += this.rundenErgebnis;
    }

    /**
     * Prüft, ob das Spiel gewonnen wurde (100 oder mehr Punkte).
     *
     * @return true, wenn gewonnen, sonst false
     */
    public boolean hatGewonnen() {
        return gesamtPunkte >= 100;
    }

    /**
     * Prüft, ob das Spiel verloren wurde (0 oder weniger Punkte).
     *
     * @return true, wenn verloren, sonst false
     */
    public boolean hatVerloren() {
        return gesamtPunkte <= 0;
    }

    /**
     * Liefert die aktuellen Gesamtpunkte des Spielers zurück.
     *
     * @return gesamtPunkte
     */
    public int getGesamtPunkte() {
        return gesamtPunkte;
    }

    /**
     * Liefert die zuletzt generierte Computerzahl zurück.
     *
     * @return computerZahl
     */
    public int getComputerZahl() {
        return computerZahl;
    }

    /**
     * Liefert das Ergebnis der letzten Runde zurück.
     *
     * @return rundenErgebnis (+20, +5 oder -10)
     */
    public int getRundenErgebnis() {
        return rundenErgebnis;
    }
}
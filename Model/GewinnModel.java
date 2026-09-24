package vshchyrov.Einfaches_Spiel.Model;


public class GewinnModel {
    private int gesamtPunkte;
    private int spielerZahl;
    private int computerZahl;
    private int rundenErgebnis;

    public GewinnModel() {
        gesamtPunkte = 30;// start der spiel
    }
//Zahl fur computer
    public void berechneComputerZahl() {
        computerZahl = (int) (Math.random() * 9) + 1;
    }

    public void berechneRunde(int spielerZahl) {
        this.spielerZahl = spielerZahl;
        berechneComputerZahl();

        if (spielerZahl == this.computerZahl) {
            this.rundenErgebnis = 20;
        }
        else if (Math.abs(spielerZahl - this.computerZahl) == 1) {//Rechnet differens
            this.rundenErgebnis = 5;
        }
        else {
            this.rundenErgebnis = -10;
        }

        this.gesamtPunkte += this.rundenErgebnis;
    }

    public boolean hatGewonnen() {
        return gesamtPunkte >= 100;
    }

    public boolean hatVerloren() {
        return gesamtPunkte <= 0;
    }

    public int getGesamtPunkte() {
        return gesamtPunkte;
    }

    public int getComputerZahl() {
        return computerZahl;
    }

    public int getRundenErgebnis() {
        return rundenErgebnis;
    }
}
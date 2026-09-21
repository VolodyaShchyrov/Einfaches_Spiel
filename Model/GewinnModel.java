package vshchyrov.Einfaches_Spiel.Model;


import java.util.Random;

public class GewinnModel{
    private int gesamtPunkte;
    private int spielerZahl;
    private int computerZahl;
    private int rundenErgebnis;

    public int getGesamtPunkte() {
        return gesamtPunkte;
    }

    public int getSpielerZahl() {
        return spielerZahl;
    }

    public int getComputerZahl() {
        return computerZahl;
    }

    public int getRundenErgebnis() {
        return rundenErgebnis;
    }


    public void setGesamtPunkte(int gesamtPunkte) {
        if(this.gesamtPunkte > 0){this.gesamtPunkte = gesamtPunkte;}

    }

    public void setSpielerZahl(int spielerZahl) {
        if(this.spielerZahl >= 1 && spielerZahl <=9){this.spielerZahl = spielerZahl;}

    }

    public void setComputerZahl(int computerZahl) {         //Random
        if(this.computerZahl >= 1 && computerZahl <=9){this.computerZahl = computerZahl;}
    }
    public void setComputerZahl() {
        Random rand = new Random();
        this.computerZahl = rand.nextInt(9)+1;
    }

    public void setRundenErgebnis(int rundenErgebnis) {
        this.rundenErgebnis = rundenErgebnis;
    }

    public GewinnModel()
    {
        this.setComputerZahl();
        this.setGesamtPunkte(30);
        this.setRundenErgebnis(0);
        this.setSpielerZahl(1);
    }

//berechnet eine zufällige Zahl von 1–9 für den Computer und speichert sie im Attribut computerZahl.
    public int berechneComputerZahl()
    {
        return 0;
    }
//übernimmt die eingegebene Zahl, berechnet das Rundenergebnis
// (+20, +5 oder −10) sowie den neuen Gesamtpunktestand und
// speichert diese Werte samt Spielerzahl in die entsprechenden Attribute.
    public void berechneRunde(int spielerZahl)
    {

    }

    public boolean hatGewonnen()
    {
        return false;
    }


    public boolean hatVerloren()
    {
        return false;
    }




}

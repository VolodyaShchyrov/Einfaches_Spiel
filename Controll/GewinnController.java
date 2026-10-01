package vshchyrov.Einfaches_Spiel.Controll;

import vshchyrov.Einfaches_Spiel.Model.*;
import vshchyrov.Einfaches_Spiel.Veiw.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Volodymyr Shchyrov
 * @version 28.09.26
 *
 * Der Controller steuert den Ablauf des Spiels, verbindet das Model mit der View
 * und reagiert auf Benutzerinteraktionen (MVC-Prinzip: Controller).
 */
public class GewinnController implements ActionListener {
    private final GewinnModel model;
    private final GewinnView view;

    /**
     * Konstruktor: Verknüpft Model und View, initialisiert Button-Zustände (v1.1)
     * und registriert den Controller selbst als ActionListener.
     */
    public GewinnController(GewinnModel model, GewinnView view) {
        this.model = model;
        this.view = view;

        // v1.1: Button startet deaktiviert, da noch nicht gespielt wurde
        this.view.getBtnNochmal().setEnabled(false);

        // Der Controller selbst ist der Listener (Enter im Textfeld + Button-Klick)
        this.view.getTxtSpielerZahl().addActionListener(this);
        this.view.getBtnNochmal().addActionListener(this);
    }

    /**
     * Unterscheidet anhand der Quelle, welche Aktion ausgelöst wurde.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object quelle = e.getSource();

        if (quelle == view.getTxtSpielerZahl()) {
            spieleRunde();
        } else if (quelle == view.getBtnNochmal()) {
            zuruecksetzen();
        }
    }

    /**
     * Führt eine Spielrunde aus: liest die Eingabe, aktualisiert das Model,
     * setzt die farbliche Rückmeldung (v2.0) und sperrt die Eingabe (v1.1).
     */
    private void spieleRunde() {
        String eingabe = view.getTxtSpielerZahl().getText();
        if (eingabe == null) {
            eingabe = "";
        }

        int zahl;
        try {
            zahl = Integer.parseInt(eingabe.trim());
        } catch (NumberFormatException ex) {
            zeigeFehler("Ungültige Eingabe!");
            return;
        }

        if (zahl < 1 || zahl > 9) {
            zeigeFehler("Nur 1-9 erlaubt!");
            return;
        }

        model.berechneComputerZahl();
        model.berechneRunde(zahl);

        view.getTxtComputerZahl().setText(String.valueOf(model.getComputerZahl()));
        view.getLblGesamtPunkte().setText("Gesamtpunkte: " + model.getGesamtPunkte());

        int erg = model.getRundenErgebnis();
        String text;
        Color farbe;

        if (erg > 0) {
            text = "+" + erg;
            farbe = Color.GREEN;
        } else {
            text = String.valueOf(erg);
            farbe = Color.RED;
        }

        // Sieg/Niederlage überschreibt Text und Farbe
        if (model.hatGewonnen()) {
            text = "Gewonnen!";
            farbe = Color.GREEN;
        } else if (model.hatVerloren()) {
            text = "Verloren";
            farbe = Color.RED;
        }

        view.getLblRundenErgebnis().setText(text);
        setzeFarbe(farbe);

        // v1.1: Eingabefeld sperren, Reset-Button aktivieren
        view.getTxtSpielerZahl().setEnabled(false);
        view.getBtnNochmal().setEnabled(true);
    }

    /**
     * Setzt die Runde zurück (Gesamtpunkte bleiben erhalten).
     */
    private void zuruecksetzen() {
        view.getTxtSpielerZahl().setText("");
        view.getTxtComputerZahl().setText("");
        view.getLblRundenErgebnis().setText("Tippe eine Zahl von 1 bis 9");

        setzeFarbe(Color.WHITE);                       // v2.0

        view.getTxtSpielerZahl().setEnabled(true);     // v1.1
        view.getBtnNochmal().setEnabled(false);
        view.getTxtSpielerZahl().requestFocusInWindow();
    }

    /** Zeigt eine Fehlermeldung an, ohne Spielstand oder Sperren zu verändern. */
    private void zeigeFehler(String meldung) {
        view.getLblRundenErgebnis().setText(meldung);
        view.getLblRundenErgebnis().setBackground(Color.WHITE);
    }

    /** Färbt beide Labels gleichzeitig (vermeidet Code-Verdoppelung). */
    private void setzeFarbe(Color farbe) {
        view.getLblRundenErgebnis().setBackground(farbe);
        view.getLblGesamtPunkte().setBackground(farbe);
    }

    public static void main(String[] args) {
        GewinnModel model = new GewinnModel();
        GewinnView view = new GewinnView();
        new GewinnController(model, view);
        view.setVisible(true);
    }
}
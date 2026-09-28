package vshchyrov.Einfaches_Spiel.Controll;

import vshchyrov.Einfaches_Spiel.Model.*;
import vshchyrov.Einfaches_Spiel.Veiw.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Volodymyr Shchyrov
 * @version 28.09.26
 *
 * Der Controller steuert den Ablauf des Spiels, verbindet das Model mit der View
 * und reagiert auf Benutzerinteraktionen (MVC-Prinzip: Controller).
 */
public class GewinnController {
    private GewinnModel model;
    private GewinnView view;

    /**
     * Konstruktor: Verknüpft Model und View, initialisiert Button-Zustände (v1.1)
     * und registriert die Action-Listener für Eingaben und Resets.
     *
     * @param model Das Spiel-Modell (Logik)
     * @param view Die Benutzeroberfläche (GUI)
     */
    public GewinnController(GewinnModel model, GewinnView view) {
        this.model = model;
        this.view = view;

        // v1.1: Button "Noch einmal!" startet anfangs deaktiviert, da noch nicht gespielt wurde
        this.view.getBtnNochmal().setEnabled(false);

        // Enter-Taste im Textfeld fängt die Spielereingabe ab
        this.view.getTxtSpielerZahl().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spieleRunde();
            }
        });

        // Klick auf den "Noch einmal!"-Button setzt das Spiel zurück
        this.view.getBtnNochmal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zuruecksetzen();
            }
        });
    }

    /**
     * Führt eine Spielrunde aus: liest die Eingabe, aktualisiert das Model,
     * setzt die farbliche Rückmeldung (v2.0) und sperrt die Eingabe ab (v1.1).
     */
    private void spieleRunde() {
        try {
            int zahl = Integer.parseInt(view.getTxtSpielerZahl().getText());
            if (zahl >= 1 && zahl <= 9) {
                model.berechneRunde(zahl);

                view.getTxtComputerZahl().setText(String.valueOf(model.getComputerZahl()));
                view.getLblGesamtPunkte().setText("Gesamtpunkte: " + model.getGesamtPunkte());

                int erg = model.getRundenErgebnis();

                // v2.0: Farbliche Rückmeldung (Grün bei Plus-Punkten, Rot bei Minus)
                if (erg > 0) {
                    view.getLblRundenErgebnis().setText("+" + erg);
                    view.getLblRundenErgebnis().setBackground(Color.GREEN);
                    view.getLblGesamtPunkte().setBackground(Color.GREEN);
                } else {
                    view.getLblRundenErgebnis().setText(String.valueOf(erg));
                    view.getLblRundenErgebnis().setBackground(Color.RED);
                    view.getLblGesamtPunkte().setBackground(Color.RED);
                }

                // Prüfung auf Sieg oder Niederlage
                if (model.hatGewonnen()) {
                    view.getLblRundenErgebnis().setText("Gewonnen!");
                    view.getLblRundenErgebnis().setBackground(Color.GREEN);
                    view.getLblGesamtPunkte().setBackground(Color.GREEN);
                } else if (model.hatVerloren()) {
                    view.getLblRundenErgebnis().setText("Verloren");
                    view.getLblRundenErgebnis().setBackground(Color.RED);
                    view.getLblGesamtPunkte().setBackground(Color.RED);
                }

                // v1.1: Nach dem Tipp Eingabefeld sperren und Reset-Button aktivieren
                view.getTxtSpielerZahl().setEnabled(false);
                view.getBtnNochmal().setEnabled(true);

            } else {
                view.getLblRundenErgebnis().setText("Nur 1-9 erlaubt!");
            }
        } catch (NumberFormatException ex) {
            view.getLblRundenErgebnis().setText("Ungültige Eingabe!");
        }
    }

    /**
     * Setzt das Spielfeld zurück: stellt den weißen Standard-Hintergrund wieder her (v2.0),
     * gibt das Eingabefeld frei und deaktiviert den Reset-Button (v1.1).
     */
    private void zuruecksetzen() {
        view.getTxtSpielerZahl().setText("");
        view.getTxtComputerZahl().setText("");
        view.getLblRundenErgebnis().setText("Tippe eine Zahl von 1 bis 9");

        // v2.0: Farben auf Weiß zurücksetzen
        view.getLblRundenErgebnis().setBackground(Color.WHITE);
        view.getLblGesamtPunkte().setBackground(Color.WHITE);

        // v1.1: Eingabefeld wieder freigeben, Button erneut deaktivieren
        view.getTxtSpielerZahl().setEnabled(true);
        view.getBtnNochmal().setEnabled(false);
    }

    /**
     * Main-Methode: Startpunkt der Anwendung. Initialisiert Model, View und Controller.
     */
    public static void main(String[] args) {
        GewinnModel model = new GewinnModel();
        GewinnView view = new GewinnView();
        new GewinnController(model, view);
        view.setVisible(true);
    }
}
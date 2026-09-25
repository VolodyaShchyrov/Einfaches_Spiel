package vshchyrov.Einfaches_Spiel.Controll;

import vshchyrov.Einfaches_Spiel.Model.*;
import vshchyrov.Einfaches_Spiel.Veiw.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GewinnController {
    private GewinnModel model;
    private GewinnView view;

    public GewinnController(GewinnModel model, GewinnView view) {
        this.model = model;
        this.view = view;

        // v1.1: Button "Noch einmal!" startet anfangs deaktiviert
        this.view.getBtnNochmal().setEnabled(false);

        // Enter-Taste im Textfeld
        this.view.getTxtSpielerZahl().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spieleRunde();
            }
        });

        // Klick auf "Noch einmal!" Button
        this.view.getBtnNochmal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zuruecksetzen();
            }
        });
    }

    /**
     * Führt eine Spielrunde aus, verarbeitet die Eingabe,
     * färbt die Labels ein (v2.0) und sperrt die Eingabe (v1.1).
     */
    private void spieleRunde() {
        try {
            int zahl = Integer.parseInt(view.getTxtSpielerZahl().getText());
            if (zahl >= 1 && zahl <= 9) {
                model.berechneRunde(zahl);

                view.getTxtComputerZahl().setText(String.valueOf(model.getComputerZahl()));
                view.getLblGesamtPunkte().setText("Gesamtpunkte: " + model.getGesamtPunkte());

                int erg = model.getRundenErgebnis();

                // v2.0: Farbliche Rückmeldung (Grün bei Gewinn, Rot bei Verlust)
                if (erg > 0) {
                    view.getLblRundenErgebnis().setText("+" + erg);
                    view.getLblRundenErgebnis().setBackground(Color.GREEN);
                    view.getLblGesamtPunkte().setBackground(Color.GREEN);
                } else {
                    view.getLblRundenErgebnis().setText(String.valueOf(erg));
                    view.getLblRundenErgebnis().setBackground(Color.RED);
                    view.getLblGesamtPunkte().setBackground(Color.RED);
                }

                if (model.hatGewonnen()) {
                    view.getLblRundenErgebnis().setText("Gewonnen!");
                    view.getLblRundenErgebnis().setBackground(Color.GREEN);
                    view.getLblGesamtPunkte().setBackground(Color.GREEN);
                } else if (model.hatVerloren()) {
                    view.getLblRundenErgebnis().setText("Verloren");
                    view.getLblRundenErgebnis().setBackground(Color.RED);
                    view.getLblGesamtPunkte().setBackground(Color.RED);
                }

                // v1.1: Nach dem Tipp Eingabe sperren und Reset-Button aktivieren
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
     * Setzt das Spielfeld zurück, stellt den weißen Standard-Hintergrund her (v2.0)
     * und gibt das Eingabefeld wieder frei (v1.1).
     */
    private void zuruecksetzen() {
        view.getTxtSpielerZahl().setText("");
        view.getTxtComputerZahl().setText("");
        view.getLblRundenErgebnis().setText("Tippe eine Zahl von 1 bis 9");

        // v2.0: Farben auf Weiß zurücksetzen
        view.getLblRundenErgebnis().setBackground(Color.WHITE);
        view.getLblGesamtPunkte().setBackground(Color.WHITE);

        // v1.1: Eingabefeld freigeben, Button wieder deaktivieren
        view.getTxtSpielerZahl().setEnabled(true);
        view.getBtnNochmal().setEnabled(false);
    }

    public static void main(String[] args) {
        GewinnModel model = new GewinnModel();
        GewinnView view = new GewinnView();
        new GewinnController(model, view);
        view.setVisible(true);
    }
}
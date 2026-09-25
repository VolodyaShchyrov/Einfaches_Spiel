package vshchyrov.Einfaches_Spiel.Controll;

import vshchyrov.Einfaches_Spiel.Model.*;
import vshchyrov.Einfaches_Spiel.Veiw.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GewinnController {
    private GewinnModel model;
    private GewinnView view;

    public GewinnController(GewinnModel model, GewinnView view) {
        this.model = model;
        this.view = view;

        // v1.1: Button "Noch einmal!" ist anfangs deaktiviert
        this.view.getBtnNochmal().setEnabled(false);

        //Enter
        this.view.getTxtSpielerZahl().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spieleRunde();
            }
        });


        //noch einmal
        this.view.getBtnNochmal().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zuruecksetzen();
            }
        });
    }

    /**
     * Führt eine Spielrunde aus, verarbeitet die Eingabe,
     * aktualisiert das Model und sperrt nach dem Enter das Eingabefeld (v1.1).
     */
    private void spieleRunde() {
        try {
            int zahl = Integer.parseInt(view.getTxtSpielerZahl().getText());
            if (zahl >= 1 && zahl <= 9) {
                model.berechneRunde(zahl);

                view.getTxtComputerZahl().setText(String.valueOf(model.getComputerZahl()));
                view.getLblGesamtPunkte().setText("Gesamtpunkte: " + model.getGesamtPunkte());

                int erg = model.getRundenErgebnis();
                if (erg > 0) {
                    view.getLblRundenErgebnis().setText("+" + erg);
                } else {
                    view.getLblRundenErgebnis().setText(String.valueOf(erg));
                }

                if (model.hatGewonnen()) {
                    view.getLblRundenErgebnis().setText("Gewonnen!");
                    view.getTxtSpielerZahl().setEnabled(false);
                } else if (model.hatVerloren()) {
                    view.getLblRundenErgebnis().setText("Verloren");
                    view.getTxtSpielerZahl().setEnabled(false);
                }

                // v1.1: Eingabe-Textfeld sperren und den "Noch einmal!"-Button aktivieren
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
     * Setzt das Spielfeld zurück: gibt das Eingabefeld wieder frei
     * und deaktiviert den "Noch einmal!"-Button (v1.1).
     */
    private void zuruecksetzen() {
        view.getTxtSpielerZahl().setText("");
        view.getTxtComputerZahl().setText("");
        view.getLblRundenErgebnis().setText("Tippe eine Zahl von 1 bis 9");

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
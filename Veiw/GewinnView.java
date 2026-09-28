package vshchyrov.Einfaches_Spiel.Veiw;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Volodymyr Shchyrov
 * @version 28.09.26
 *
 * Die View-Klasse ist für die grafische Benutzeroberfläche (GUI)
 * des Zahlen-Gewinnspiels zuständig (MVC-Prinzip: View).
 * Sie erbt von JFrame und baut das Layout sowie alle UI-Komponenten auf.
 */
public class GewinnView extends JFrame {
    private JLabel lblRundenErgebnis;
    private JLabel lblGesamtPunkte;
    private JTextField txtSpielerZahl;
    private JTextField txtComputerZahl;
    private JButton btnNochmal;

    /**
     * Konstruktor: Initialisiert das Fenster, setzt Titel, Größe,
     * Schließverhalten und fügt alle UI-Panels und Komponenten hinzu.
     */
    public GewinnView() {
        setTitle("Zahlen-Gewinnspiel (v2.0)");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Oben: Labels für Ergebnisse und Punkte (BorderLayout.NORTH)
        JPanel pnlNorth = new JPanel(new GridLayout(2, 2));
        pnlNorth.add(new JLabel("Rundenergebnis:", SwingConstants.CENTER));
        pnlNorth.add(new JLabel("Gesamtpunkte:", SwingConstants.CENTER));

        lblRundenErgebnis = new JLabel("Tippe eine Zahl von 1 bis 9", SwingConstants.CENTER);
        // Wichtig: setOpaque(true) ist bei JLabels nötig, damit setBackground() die Farbe anzeigt (v2.0)
        lblRundenErgebnis.setOpaque(true);
        lblRundenErgebnis.setBackground(Color.WHITE);

        lblGesamtPunkte = new JLabel("Gesamtpunkte: 30", SwingConstants.CENTER);
        lblGesamtPunkte.setOpaque(true);
        lblGesamtPunkte.setBackground(Color.WHITE);

        pnlNorth.add(lblRundenErgebnis);
        pnlNorth.add(lblGesamtPunkte);
        add(pnlNorth, BorderLayout.NORTH);

        // Mitte: Eingabefelder und Beschriftungen (BorderLayout.CENTER)
        JPanel pnlCenter = new JPanel(new GridLayout(2, 2, 10, 10));
        // Ein leerer Rand (Padding) für besseres Design
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCenter.add(new JLabel("Deine Zahl:", SwingConstants.CENTER));
        pnlCenter.add(new JLabel("Computer:", SwingConstants.CENTER));

        txtSpielerZahl = new JTextField();
        txtSpielerZahl.setHorizontalAlignment(JTextField.CENTER);
        txtSpielerZahl.setFont(new Font("Arial", Font.BOLD, 24));

        txtComputerZahl = new JTextField();
        txtComputerZahl.setEditable(false); // Computer-Feld ist nur zum Anzeigen da
        txtComputerZahl.setHorizontalAlignment(JTextField.CENTER);
        txtComputerZahl.setFont(new Font("Arial", Font.BOLD, 24));

        pnlCenter.add(txtSpielerZahl);
        pnlCenter.add(txtComputerZahl);
        add(pnlCenter, BorderLayout.CENTER);

        // Unten: Reset-Button (BorderLayout.SOUTH)
        JPanel pnlSouth = new JPanel();
        btnNochmal = new JButton("Noch einmal!");
        pnlSouth.add(btnNochmal);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    // --- Getter-Methoden für den Controller ---

    /**
     * Liefert das Texteingabefeld für die Spielereingabe zurück.
     * @return txtSpielerZahl
     */
    public JTextField getTxtSpielerZahl() {
        return txtSpielerZahl;
    }

    /**
     * Liefert das Textfeld für die Computerzahl zurück (read-only).
     * @return txtComputerZahl
     */
    public JTextField getTxtComputerZahl() {
        return txtComputerZahl;
    }

    /**
     * Liefert das Label für das aktuelle Rundenergebnis zurück.
     * @return lblRundenErgebnis
     */
    public JLabel getLblRundenErgebnis() {
        return lblRundenErgebnis;
    }

    /**
     * Liefert das Label für die Gesamtpunkte zurück.
     * @return lblGesamtPunkte
     */
    public JLabel getLblGesamtPunkte() {
        return lblGesamtPunkte;
    }

    /**
     * Liefert den "Noch einmal!"-Button zurück.
     * @return btnNochmal
     */
    public JButton getBtnNochmal() {
        return btnNochmal;
    }
}
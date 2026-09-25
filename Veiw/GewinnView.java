package vshchyrov.Einfaches_Spiel.Veiw;

import javax.swing.*;
import java.awt.*;

public class GewinnView extends JFrame {
    private JLabel lblRundenErgebnis;
    private JLabel lblGesamtPunkte;
    private JTextField txtSpielerZahl;
    private JTextField txtComputerZahl;
    private JButton btnNochmal;

    public GewinnView() {
        setTitle("Zahlen-Gewinnspiel (v2.0)");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Oben: Labels
        JPanel pnlNorth = new JPanel(new GridLayout(2, 2));
        pnlNorth.add(new JLabel("Rundenergebnis:", SwingConstants.CENTER));
        pnlNorth.add(new JLabel("Gesamtpunkte:", SwingConstants.CENTER));

        lblRundenErgebnis = new JLabel("Tippe eine Zahl von 1 bis 9", SwingConstants.CENTER);
        lblRundenErgebnis.setOpaque(true); // Wichtig, damit die Hintergrundfarbe angezeigt wird (v2.0)
        lblRundenErgebnis.setBackground(Color.WHITE);

        lblGesamtPunkte = new JLabel("Gesamtpunkte: 30", SwingConstants.CENTER);
        lblGesamtPunkte.setOpaque(true); // Wichtig, damit die Hintergrundfarbe angezeigt wird (v2.0)
        lblGesamtPunkte.setBackground(Color.WHITE);

        pnlNorth.add(lblRundenErgebnis);
        pnlNorth.add(lblGesamtPunkte);
        add(pnlNorth, BorderLayout.NORTH);

        // Mitte: Eingabefelder
        JPanel pnlCenter = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCenter.add(new JLabel("Deine Zahl:", SwingConstants.CENTER));
        pnlCenter.add(new JLabel("Computer:", SwingConstants.CENTER));

        txtSpielerZahl = new JTextField();
        txtSpielerZahl.setHorizontalAlignment(JTextField.CENTER);
        txtSpielerZahl.setFont(new Font("Arial", Font.BOLD, 24));

        txtComputerZahl = new JTextField();
        txtComputerZahl.setEditable(false);
        txtComputerZahl.setHorizontalAlignment(JTextField.CENTER);
        txtComputerZahl.setFont(new Font("Arial", Font.BOLD, 24));

        pnlCenter.add(txtSpielerZahl);
        pnlCenter.add(txtComputerZahl);
        add(pnlCenter, BorderLayout.CENTER);

        // Unten: Button
        JPanel pnlSouth = new JPanel();
        btnNochmal = new JButton("Noch einmal!");
        pnlSouth.add(btnNochmal);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    // Getter
    public JTextField getTxtSpielerZahl() { return txtSpielerZahl; }
    public JTextField getTxtComputerZahl() { return txtComputerZahl; }
    public JLabel getLblRundenErgebnis() { return lblRundenErgebnis; }
    public JLabel getLblGesamtPunkte() { return lblGesamtPunkte; }
    public JButton getBtnNochmal() { return btnNochmal; }
}
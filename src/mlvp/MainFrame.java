package mlvp;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    // ----- Attributs -----

    JButton nextTurnBtn;

    JPanel gridPanel;
    JPanel aboutPanel;

    // ----- Constructeur -----

    public MainFrame() {
        // Création de la fenêtre
        super("Chasse au trésor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);

        // Container de la fenêtre
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // --- Panel "Tour suivant" ---
        JPanel nextTurnPanel = new JPanel();
        nextTurnBtn = new JButton("Tour suivant");
        nextTurnPanel.add(nextTurnBtn);

        // --- Panel "Grille" ---
        gridPanel = new JPanel();


        // --- Panel "Informations sur les joueurs" ---
        aboutPanel = new JPanel();

        // Ajout des panels
        c.add(nextTurnPanel, BorderLayout.NORTH);
        c.add(gridPanel, BorderLayout.CENTER);
        c.add(aboutPanel, BorderLayout.SOUTH);

        // Contrôleur de la fenêtre
        Controller contr = new Controller(this);
        nextTurnBtn.addActionListener(contr);

        // Affichage
        setVisible(true);
    }

    // ----- Getters -----

    public JPanel getGridPanel() {
        return gridPanel;
    }

    public JPanel getAboutPanel() {
        return aboutPanel;
    }
}

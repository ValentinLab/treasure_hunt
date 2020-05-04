package mlvp;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    // ----- Attributs -----

    JButton nextTurnBtn;

    // ----- Constructeur -----

    public MainFrame() {
        // Création de la fenêtre
        super("Chasse au trésor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 700);

        // Container de la fenêtre
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // --- Panel "Tour suivant" ---
        JPanel nextTurnPanel = new JPanel();
        nextTurnBtn = new JButton("Tour suivant");
        nextTurnPanel.add(nextTurnBtn);

        // --- Panel "Grille" ---
        JPanel gridPanel = new JPanel(new GridLayout(12, 12));
        for(int i = 0; i < 12; ++i) {
            for(int j = 0; j < 12; ++j) {
                gridPanel.add(new JLabel(i + "." + j));
            }
        }

        // --- Panel "Informations sur les joueurs" ---
        JPanel aboutPanel = new JPanel(new GridLayout(3,1));
        for(int i = 0; i < 3; ++i) {
            aboutPanel.add(new JLabel("coucou"));
        }

        // Ajout des panels
        c.add(nextTurnPanel, BorderLayout.NORTH);
        c.add(gridPanel, BorderLayout.CENTER);
        c.add(aboutPanel, BorderLayout.SOUTH);

        // Affichage
        setVisible(true);
    }
}

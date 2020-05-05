package mlvp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {
    // ----- Attributs -----

    JButton nextTurnBtn;

    JPanel gridPanel;
    JLabel[][] gridLabels;

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

    public JButton getNextTurnBtn() {
        return nextTurnBtn;
    }

    public JLabel getGridLabel(int x, int y) {
        return gridLabels[x][y];
    }

    public JPanel getAboutPanel() {
        return aboutPanel;
    }

    // ----- Fonctions -----

    public void initGrid(int size) {
        gridPanel.setLayout(new GridLayout(size, size));

        gridLabels = new JLabel[size][size];
        for(int y = 0; y < size; ++y) {
            for(int x = 0; x < size; ++x) {
                gridLabels[x][y] = new JLabel(".", SwingConstants.CENTER);
                gridLabels[x][y].setOpaque(true);
                gridLabels[x][y].setBorder(new LineBorder(Color.BLACK));
                gridPanel.add(gridLabels[x][y]);
            }
        }
    }
}

package mlvp;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Interface graphique principale du jeu
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class MainFrame extends JFrame {
    // ----- Attributs -----

    JButton nextTurnBtn;

    JPanel gridPanel;
    JLabel[][] gridLabels;

    JPanel aboutPanel;
    JLabel[] aboutLabels;

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

    public JLabel getAboutLabel(int index) {
        return aboutLabels[index];
    }

    // ----- Fonctions -----

    /**
     * Initialier la grille de labels
     *
     * @param size Taille de la grille
     */
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

    /**
     * Initialiser les labels contenant les informations sur les joueurs
     *
     * @param size Nombre de joueurs
     */
    public void initPlayersDatas(int size) {
        aboutLabels = new JLabel[size];
        aboutPanel.setLayout(new GridLayout(size, 1));

        for(int i = 0; i < size; ++i) {
            aboutLabels[i] = new JLabel();
            aboutPanel.add(aboutLabels[i]);
        }
    }

    /**
     * Afficher la boîte de dialogue de victoire
     *
     * @param playerName Nom du joueur gagnant
     */
    public void printWinnerBox(String playerName) {
        JOptionPane.showMessageDialog(this,
            "Le joueur " + playerName + " a gagné !", "Victoire !",
            JOptionPane.INFORMATION_MESSAGE);
    }
}

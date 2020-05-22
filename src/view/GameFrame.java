package view;

import controller.GameController;
import model.Wall; // Le constructeur a besoin de connaitre Wall

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Interface graphique du jeu
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class GameFrame extends JFrame {
    // ----- Attributs -----

    JButton nextTurnBtn;
    JButton cancelBtn;

    JPanel gridPanel;
    JLabel[][] gridLabels;

    JPanel aboutPanel;
    JLabel[] aboutLabels;

    // ----- Constructeurs -----

    public GameFrame(String builtBoard, Wall[] builtWalls) {
        // Création de la fenêtre
        super("Chasse au trésor - Jeu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 600);

        // Container de la fenêtre
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // --- Panel "Tour suivant" ---
        JPanel nextTurnPanel = new JPanel();
        cancelBtn = new JButton("Retour au menu");
        nextTurnPanel.add(cancelBtn);
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
        GameController contr = new GameController(this, builtBoard, builtWalls);
        nextTurnBtn.addActionListener(contr);
        cancelBtn.addActionListener(contr);

        // Affichage
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public GameFrame() {
        this("", null);
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
                gridLabels[x][y] = new JLabel("", SwingConstants.CENTER);
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
        aboutPanel.setLayout(new GridLayout(size, 1));

        aboutLabels = new JLabel[size];
        for(int i = 0; i < size; ++i) {
            aboutLabels[i] = new JLabel();
            aboutPanel.add(aboutLabels[i]);
        }
    }

    /**
     * Afficher la boîte de dialogue de victoire
     *
     * @param playerName Nom du joueur gagnant
     * @return Valeur de retour d'une JOptionPane
     */
    public int printWinnerBox(String playerName) {
        return JOptionPane.showConfirmDialog(this,
            "Le joueur " + playerName + " a gagné ! \n Voulez vous recommencer une partie ?", "Victoire !",
            JOptionPane.YES_NO_OPTION);
    }

    /**
     * Nettoyer l'affichage lors d'une nouvelle partie
     */
    public void cleanFrame() {
        gridPanel.removeAll();
        aboutPanel.removeAll();

        setVisible(true);
    }
}

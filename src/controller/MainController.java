package controller;

import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur de la fenêtre de menu (fenêtre principale)
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class MainController implements ActionListener {
    // ----- Attributs -----

    MainFrame mf;

    // ----- Constructeur -----

    public MainController(MainFrame frame) {
        mf = frame;
    }

    // ----- Fonction -----

    /**
     * Actions à réaliser lors d'un événement
     *
     * @param e Événement
     */
    public void actionPerformed(ActionEvent e) {
        // Fermeture de la fenêtre principale
        mf.setVisible(false);
        mf.dispose();

        // Lancement de la nouvelle fenêtre
        if(e.getSource() == mf.getPlayBtn()) {
            GameFrame gf = new GameFrame();
        } else {
            EditFrame ef = new EditFrame();
        }
    }
}

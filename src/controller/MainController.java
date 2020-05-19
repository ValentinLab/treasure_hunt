package controller;

import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    // ----- Attributs -----

    MainFrame mf;

    // ----- Constructeur -----

    public MainController(MainFrame frame) {
        mf = frame;
    }

    // ----- Fonction -----

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

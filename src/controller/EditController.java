package controller;

import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditController implements ActionListener {
    // ----- Attributs -----

    EditFrame ef;
    char currentBrush;

    // ----- Constructeur -----

    public EditController(EditFrame edFrame) {
        ef = edFrame;
        currentBrush = 'X';
    }

    // ----- Fonction -----

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ef.getToolBtn(0)) { // Bouton radio "Trésor"
            currentBrush = 'T';
        } else if(e.getSource() == ef.getToolBtn(1)) { // Bouton radio "Joueur"
            currentBrush = 'J';
        } else if(e.getSource() == ef.getToolBtn(2)) { // Bouton radio "Téléportation"
            currentBrush = 'M';
        } else if(e.getSource() == ef.getCancelBtn()) { // Bouton "Annuler"
            cancelEdit();
        } else if(e.getSource() == ef.getAddWall()) { // Bouton "Ajouter un mur"
            wallPosition(ef.getXStartField(), ef.getYStartField(), ef.getXEndField(), ef.getYEndField());
        } else if(
            e.getSource() != ef.getXStartField()
            && e.getSource() != ef.getYStartField()
            && e.getSource() != ef.getXEndField()
            && e.getSource() != ef.getYEndField()
        ) { // Grille
            System.out.println("Grid");
        }
    }

    private void cancelEdit() {
        // Fermeture de la fenêtre principale
        ef.setVisible(false);
        ef.dispose();

        // Lancement de la nouvelle fenêtre
        MainFrame mf = new MainFrame();
    }

    private void wallPosition(JTextField xStart, JTextField yStart, JTextField xEnd, JTextField yEnd) {
        System.out.println("New wall");
    }
}

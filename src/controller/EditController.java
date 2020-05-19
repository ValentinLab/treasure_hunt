package controller;

import view.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditController implements ActionListener {
    // ----- Attributs -----

    EditFrame ef;
    char currentBrush;
    Position treasurePos;
    String board;
    char[] boardChar;
    int playerNumber;

    // ----- Constructeur -----

    public EditController(EditFrame edFrame) {
        ef = edFrame;
        currentBrush = 'T';
        treasurePos = null;
        board = "++++++++++++\n" +
                "+..........+\n" +
                "+..........+\n" +
                "+..........+\n" +
                "+..........+\n" +
                "+..........+\n" +
                "+..........+\n" +
                "+..........+\n" +
                "+..........+\n" +
                "+..........+\n" +
                "+..........+\n" +
                "++++++++++++";
        boardChar = board.toCharArray();
        playerNumber = 0;
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
            addElementOnGrid(e.getSource());
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
    }

    private void addElementOnGrid(Object obj) {
        int xCurrent = -1, yCurrent = -1;

        // Rechercher le bouton
        int gridSize = ef.getGridSize();
        for(int y = 1; y < gridSize-1; ++y) {
            for(int x = 1; x < gridSize-1; ++x) {
                if(obj == ef.getGridBtn(x, y)) {
                    xCurrent = x;
                    yCurrent = y;
                    break;
                }
            }

            if(xCurrent != -1 && yCurrent != -1) {
                break;
            }
        }

        // Ajouter l'élément
        if(currentBrush == 'T') { // Ajout du trésor
            int newPos = posToIndex(gridSize, xCurrent, yCurrent);
            if(boardChar[newPos] == '.') {
                if(treasurePos != null) {
                    int posInString = posToIndex(gridSize, treasurePos.getX(),treasurePos.getY());
                    boardChar[posInString] = '.';

                    ef.getGridBtn(treasurePos.getX(), treasurePos.getY()).setText("");
                    ef.getGridBtn(treasurePos.getX(), treasurePos.getY()).setBackground(Color.LIGHT_GRAY);
                }

                treasurePos = new Position(xCurrent, yCurrent);

                boardChar[newPos] = 'T';

                ef.getGridBtn(xCurrent, yCurrent).setText("T");
                ef.getGridBtn(xCurrent, yCurrent).setBackground(Color.YELLOW);
            }
            // TODO Ajout de l'erreur
        } else if(currentBrush == 'M') { // Téléportation
            int posInString = posToIndex(gridSize, xCurrent, yCurrent);
            if(boardChar[posInString] == '.') {
                boardChar[posInString] = '?';

                ef.getGridBtn(xCurrent, yCurrent).setText("?");
                ef.getGridBtn(xCurrent, yCurrent).setBackground(Color.PINK);
            } else if(boardChar[posInString] == '?') {
                boardChar[posInString] = '.';

                ef.getGridBtn(xCurrent, yCurrent).setText("");
                ef.getGridBtn(xCurrent, yCurrent).setBackground(Color.LIGHT_GRAY);
            }
            // TODO Ajout de l'erreur
        } else if(currentBrush == 'J') {
            int posInString = posToIndex(gridSize, xCurrent, yCurrent);
            if(boardChar[posInString] == '.') {
                char playerLetter = (char)('A' + playerNumber);
                playerNumber += 1;

                boardChar[posInString] = playerLetter;

                ef.getGridBtn(xCurrent, yCurrent).setText(String.valueOf(playerLetter));
                ef.getGridBtn(xCurrent, yCurrent).setBackground(Color.LIGHT_GRAY);
            } else if(
                boardChar[posInString] != '.'
                && boardChar[posInString] != 'T'
                && boardChar[posInString] != '#'
            ) {
                boardChar[posInString] = '.';

                ef.getGridBtn(xCurrent, yCurrent).setText("");
                ef.getGridBtn(xCurrent, yCurrent).setBackground(Color.LIGHT_GRAY);
            }
            // TODO Ajout de l'erreur
        }

        // -----> DEBUG
        System.out.println(new String(boardChar));
    }

    private int posToIndex(int size, int x, int y) {
        return (size+1)*y + x;
    }
}

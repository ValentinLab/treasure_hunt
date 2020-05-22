package controller;

import view.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class EditController implements ActionListener {
    // ----- Attributs -----

    EditFrame ef;

    char currentBrush;

    Position treasurePos;
    String board;
    char[] boardChar;
    LinkedList<Wall> walls;
    int playerNumber;
    int playerNumberReal;

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
        walls = new LinkedList<Wall>();
        playerNumber = 0;
        playerNumberReal = 0;
    }

    // ----- Fonction -----

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ef.getToolBtn(0)) { // Bouton radio "Trésor"
            currentBrush = 'T';
        } else if(e.getSource() == ef.getToolBtn(1)) { // Bouton radio "Joueur"
            currentBrush = 'J';
        } else if(e.getSource() == ef.getToolBtn(2)) { // Bouton radio "Téléportation"
            currentBrush = 'M';
        } else if(e.getSource() == ef.getValidateBtn()) { // Bouton "Valider le plateau"
            // Fermeture de la fenêtre
            ef.setVisible(false);
            ef.dispose();

            // Ouverture du jeu
            Wall[] builtWalls = new Wall[walls.size()];
            GameFrame gf = new GameFrame(new String(boardChar), walls.toArray(builtWalls));
        } else if(e.getSource() == ef.getCancelBtn()) { // Bouton "Annuler"
            cancelEdit();
        } else if(e.getSource() == ef.getAddWallBtn()) { // Bouton "Ajouter un mur"
            addWallOnGrid();
        } else if(e.getSource() == ef.getCancelWallBtn()) { // Bouton "Annule le mur"
            cancelWallOnGrid();
        } else { // Grille
            addElementOnGrid(e.getSource());
        }
    }

    private void enableTools() {
        for(int i = 0; i < 3; ++i) {
            ef.getToolBtn(i).setEnabled(true);
            if(ef.getToolBtn(i).isSelected()) {
                switch(i) {
                    case 0:
                        currentBrush = 'T';
                        break;
                    case 1:
                        currentBrush = 'J';
                        break;
                    case 2:
                        currentBrush = 'M';
                        break;
                }
            }
        }
    }

    private void cancelEdit() {
        // Fermeture de la fenêtre principale
        ef.setVisible(false);
        ef.dispose();

        // Lancement de la nouvelle fenêtre
        MainFrame mf = new MainFrame();
    }

    private void addWallOnGrid() {
        // Désactiver les checkboxs
        for(int i = 0; i < 3; ++i) {
            ef.getToolBtn(i).setEnabled(false);
        }
        currentBrush = 'W';

        // Activer le bouton d'annulation
        ef.getCancelWallBtn().setEnabled(true);

        // Désactiver le bouton actuel
        ef.getAddWallBtn().setEnabled(false);
    }

    private void cancelWallOnGrid() {
        // Activer les checkboxs
        enableTools();

        // Activer le bouton d'ajout
        ef.getAddWallBtn().setEnabled(true);

        // Désactiver le bouton acteuel
        ef.getCancelWallBtn().setEnabled(false);
    }

    private boolean checkWallPosition(int gridSize, int xCurrent, int yCurrent) {
        boolean isGood = true;
        for(int y = -1; y <= 1; ++y) {
            for(int x = -1; x <= 1; ++x) {
                int checkX = xCurrent + x, checkY = yCurrent + y;
                if(checkX < 1 || checkX >= gridSize || checkY < 1 || checkY >= gridSize) {
                    continue;
                }

                int checkPos = posToIndex(gridSize, checkX, checkY);
                if(boardChar[checkPos] == '#') {
                    isGood = false;
                    break;
                }
            }
        }

        return isGood;
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

            if(xCurrent != -1) {
                break;
            }
        }

        // Ajouter l'élément
        if(currentBrush == 'T') {
            // -- Ajout du trésor --

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
            } else {
                JOptionPane.showMessageDialog(
                    ef,
                    "Le trésor doit être posé sur une case vide.",
                    "Erreur dans l'ajout du trésor",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } else if(currentBrush == 'M') {
            // -- Ajout d'une case de téléportation --

            int posInString = posToIndex(gridSize, xCurrent, yCurrent);
            if(boardChar[posInString] == '.') {
                boardChar[posInString] = '?';

                ef.getGridBtn(xCurrent, yCurrent).setText("?");
                ef.getGridBtn(xCurrent, yCurrent).setBackground(Color.PINK);
            } else if(boardChar[posInString] == '?') {
                boardChar[posInString] = '.';

                ef.getGridBtn(xCurrent, yCurrent).setText("");
                ef.getGridBtn(xCurrent, yCurrent).setBackground(Color.LIGHT_GRAY);
            } else {
                JOptionPane.showMessageDialog(
                    ef,
                    "Une case de téléportation doit être posé sur une case vide.",
                    "Erreur dans l'ajout d'une case de téléportation",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } else if(currentBrush == 'J') {
            // -- Ajout d'un joueur --

            int posInString = posToIndex(gridSize, xCurrent, yCurrent);
            if(boardChar[posInString] == '.') {
                char playerLetter = (char)('A' + playerNumber);
                playerNumber += 1;
                playerNumberReal += 1;

                boardChar[posInString] = playerLetter;

                ef.getGridBtn(xCurrent, yCurrent).setText(String.valueOf(playerLetter));
                ef.getGridBtn(xCurrent, yCurrent).setBackground(Color.LIGHT_GRAY);
            } else if(
                boardChar[posInString] != '.'
                && boardChar[posInString] != 'T'
                && boardChar[posInString] != '#'
            ) {
                boardChar[posInString] = '.';
                playerNumberReal -= 1;

                ef.getGridBtn(xCurrent, yCurrent).setText("");
                ef.getGridBtn(xCurrent, yCurrent).setBackground(Color.LIGHT_GRAY);
            } else {
                JOptionPane.showMessageDialog(
                    ef,
                    "Un joueur doit être posé sur une case vide.",
                    "Erreur dans l'ajout d'un joueur",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } else if(currentBrush == 'W') {
            // -- Ajout d'un mur --
            int posInString = posToIndex(gridSize, xCurrent, yCurrent);

            if(boardChar[posInString] == '.') {
                // Vérifier qu'il n'y a pas de murs autour
                boolean isGood = checkWallPosition(gridSize, xCurrent, yCurrent);

                // Vérifier qu'il n'y a aucun mur autour
                if(!isGood) {
                    JOptionPane.showMessageDialog(
                        ef,
                        "Vous ne pouvez pas placer de mur si proche d'un autre.",
                        "Erreur dans l'ajout d'un mur",
                        JOptionPane.ERROR_MESSAGE
                    );
                }

                // Vérifier que le mur n'est pas au bord du plateau
                if(
                    isGood
                    && (xCurrent == 1 || xCurrent == gridSize - 2
                    || yCurrent == 1 || yCurrent == gridSize - 2)
                ) {
                    JOptionPane.showMessageDialog(
                        ef,
                        "Vous ne pouvez pas placer de mur au bord du plateau.",
                        "Erreur dans l'ajout d'un mur",
                        JOptionPane.ERROR_MESSAGE
                    );
                    isGood = false;
                }

                if(
                    walls.isEmpty()
                    || (walls.getLast().getTo().getX() != 0 && walls.getLast().getTo().getY()  != 0 )
                ) {
                    // -> Ajout du point de départ du mur

                    // Annuler l'ajout du mur
                    if(!isGood) {
                        cancelWallOnGrid();
                        ef.getGridBtn(xCurrent, yCurrent).setText("");
                        return;
                    }

                    // Ajouter le mur
                    walls.add(new Wall(new Position (xCurrent, yCurrent), new Position(0, 0)));

                    ef.getGridBtn(xCurrent, yCurrent).setText("#");
                    ef.getGridBtn(xCurrent, yCurrent).setBackground(Color.CYAN);
                } else {
                    // -> Ajout du point d'arrivé du mur

                    Position from = walls.getLast().getFrom();

                    // Annuler l'ajout du mur
                    if(!isGood) {
                        cancelWallOnGrid();
                        ef.getGridBtn(from.getX(), from.getY()).setText("");
                        ef.getGridBtn(from.getX(), from.getY()).setBackground(Color.LIGHT_GRAY);
                        walls.removeLast();
                        return;
                    }

                    int xFrom = from.getX(), yFrom = from.getY();
                    if ((xFrom == xCurrent && yFrom < yCurrent) || (xFrom < xCurrent && yFrom == yCurrent)) {
                        // Vérifier que le mur n'empiète rien
                        isGood = true;
                        for(int y = from.getY(); y <= yCurrent; ++y) {
                            for(int x = from.getX(); x <= xCurrent; ++x) {
                                int targetPos = posToIndex(gridSize, x, y);
                                if(boardChar[targetPos] != '.') {
                                    isGood = false;
                                    break;
                                }
                            }
                            if(!isGood) {
                                break;
                            }
                        }

                        // Ajouter le mur
                        if(isGood) {
                            walls.removeLast();
                            walls.add(new Wall(from, new Position(xCurrent, yCurrent)));

                            for(int y = from.getY(); y <= yCurrent; ++y) {
                                for(int x = from.getX(); x <= xCurrent; ++x) {
                                    int targetPos = posToIndex(gridSize, x, y);
                                    boardChar[targetPos] = '#';

                                    ef.getGridBtn(x, y).setText("#");
                                    ef.getGridBtn(x, y).setBackground(Color.BLUE);
                                }
                            }

                            // Remettre les boutons à l'état par défaut
                            ef.getAddWallBtn().setEnabled(true);
                            ef.getCancelWallBtn().setEnabled(false);
                            enableTools();
                        } else {
                            JOptionPane.showMessageDialog(
                                ef,
                                "Le mur ne peut pas empiéter des élément.",
                                "Erreur dans l'ajout d'un mur",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else {
                        // Message d'erreur
                        JOptionPane.showMessageDialog(
                            ef,
                            "La position de départ ne peut pas être après la position d'arrivé.",
                            "Erreur dans l'ajout d'un mur",
                            JOptionPane.ERROR_MESSAGE
                        );

                        // Annulation de l'ajout du mur
                        cancelWallOnGrid();
                        ef.getGridBtn(from.getX(), from.getY()).setText("");
                        ef.getGridBtn(from.getX(), from.getY()).setBackground(Color.LIGHT_GRAY);
                        walls.removeLast();
                    }
                }
            }
        }

        // Activer le bouton nouveau jeu si besoin
        if(treasurePos != null && playerNumberReal > 0) {
            ef.getValidateBtn().setEnabled(true);
        } else {
            ef.getValidateBtn().setEnabled(false);
        }
    }

    private int posToIndex(int size, int x, int y) {
        return (size+1)*y + x;
    }
}

package controller;

import model.Board;
import model.Hunter;
import view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur de l'interface graphique
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class GameController implements ActionListener {
    // ----- Attributs -----

    Board board;
    GameFrame mf;
    String[] movements;

    // ----- Constructeur -----

    public GameController(GameFrame f) {
        board = new Board();
        mf = f;

        initFrame();
    }


    // ----- Fonctions -----

    /**
     * Actions à réalisé lors d'un événement
     *
     * @param e Événement
     */
    public void actionPerformed(ActionEvent e) {
        // Source de l'action
        Object source = e.getSource();

        // Nouveau tour de jeu
        if(source == mf.getNextTurnBtn()) {
            // Jouer un nouveau tour
            board.playRound(movements);

            // Mettre à jour l'affichage
            drawGrid();
            for(int i = 0; i < movements.length; ++i) {
                mf.getAboutLabel(i).setText(movements[i]);
            }

            // Vérifier si un joueur a gagné
            Hunter winner = board.checkVictory();
            if(winner != null) {
                mf.getGridLabel(winner.getPos().getX(), winner.getPos().getY()).setText(winner.toString());
                mf.getNextTurnBtn().setEnabled(false);
                int playerDecision = mf.printWinnerBox(winner.toString());
                if(playerDecision == JOptionPane.YES_OPTION) {
                    board = new Board();

                    mf.cleanFrame();
                    initFrame();
                    mf.getNextTurnBtn().setEnabled(true);
                }
            }
        }
    }


    /**
     * Initialiser l'interface graphique en fonction du terrain de jeu
     */
    public void initFrame() {
        // Affichage initial de la grille
        int boardSize = board.getSize();
        mf.initGrid(boardSize);
        drawGrid();

        // Affichage initial des joueurs
        int playersNb = board.getPlayersNumber();
        mf.initPlayersDatas(playersNb);
        movements = new String[playersNb];
        for(int i = 0; i < playersNb; ++i) {
            Hunter player = board.getPlayer(i);
            mf.getAboutLabel(i).setText("Personnage " + player.toString() + " " + player.getPos() + " dir " + player.getDir().toString());
        }
    }

    /**
     * Dessiner la grille en fonction des cases
     */
    public void drawGrid() {
        // Obtenir la taille de la grille
        int boardSize = board.getSize();

        // Afficher les éléments de la grille
        String labelTxt;
        Color labelColor;
        for(int y = 0; y < boardSize; ++y) {
            for(int x = 0; x < boardSize; ++x) {
                if(board.getCell(x, y).toString().equals("#")) {
                    labelTxt = "";
                    labelColor = Color.BLUE;
                } else if(board.getCell(x, y).toString().equals("+")) {
                    if(x == 0 || x == boardSize-1) {
                        labelTxt = (y > 0 && y < boardSize-1) ? String.valueOf(y) : "";
                    } else if(y == 0 || y == boardSize-1) {
                        labelTxt = (x > 0 && x < boardSize-1) ? String.valueOf(x) : "";
                    } else {
                        labelTxt = "";
                    }
                    labelColor = Color.RED;
                } else if(board.getCell(x, y).toString().equals("T")) {
                    labelTxt = "";
                    labelColor = Color.YELLOW;
                } else if(board.getCell(x, y).toString().equals(".")) {
                    labelTxt = "";
                    labelColor = Color.LIGHT_GRAY;
                } else if(board.getCell(x, y).toString().charAt(0) == '?') {
                    if(board.getCell(x, y).toString().length() > 1) {
                        labelTxt = String.valueOf(board.getCell(x, y).toString().charAt(1));
                        labelColor = Color.MAGENTA;
                    } else {
                        labelTxt = "?";
                        labelColor = Color.PINK;
                    }
                } else {
                    labelTxt = board.getCell(x, y).toString();
                    labelColor = Color.LIGHT_GRAY;
                }

                mf.getGridLabel(x, y).setText(labelTxt);
                mf.getGridLabel(x, y).setBackground(labelColor);
            }
        }
    }
}

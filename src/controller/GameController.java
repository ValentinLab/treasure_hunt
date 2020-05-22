package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur de la fenêtre de jeu
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class GameController implements ActionListener {
    // ----- Attributs -----

    Board board;
    GameFrame mf;
    String[] movements;

    // ----- Constructeur -----

    public GameController(GameFrame f, String builtBoard, Wall[] builtWalls) {
        board = new Board(builtBoard, builtWalls);
        mf = f;

        initFrame();
    }

    // ----- Fonctions -----

    /**
     * Actions à réaliser lors d'un événement
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
        } else {
            // Fermeture de la fenêtre principale
            mf.setVisible(false);
            mf.dispose();

            // Lancement de la nouvelle fenêtre
            MainFrame mainFr = new MainFrame();
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
        String labelImg;
        String labelTxt;
        for(int y = 0; y < boardSize; ++y) {
            for(int x = 0; x < boardSize; ++x) {
                if(board.getCell(x, y).toString().equals("#")) {
                    labelTxt = "";
                } else if(board.getCell(x, y).toString().equals("+")) {
                    labelTxt = "";
                } else if(board.getCell(x, y).toString().equals("T")) {
                    labelTxt = "";
                } else if(board.getCell(x, y).toString().equals(".")) {
                    labelTxt = "";
                } else if(board.getCell(x, y).toString().charAt(0) == '?') {
                    if(board.getCell(x, y).toString().length() > 1) {
                        labelTxt = String.valueOf(board.getCell(x, y).toString().charAt(1));
                    } else {
                        labelTxt = "";
                    }
                } else {
                    labelTxt = board.getCell(x, y).toString();
                }

                labelImg = board.getCell(x, y).getImagePath();
                int labelSize = 500/boardSize;
                ImageIcon imageIcon = new ImageIcon(
                    new ImageIcon(labelImg).getImage().getScaledInstance(labelSize, labelSize, Image.SCALE_DEFAULT)
                );
                mf.getGridLabel(x, y).setIcon(imageIcon);

                mf.getGridLabel(x, y).setText(labelTxt);
                mf.getGridLabel(x, y).setForeground(Color.WHITE);
                mf.getGridLabel(x, y).setHorizontalTextPosition(JLabel.CENTER);
            }
        }

        mf.repaint();
    }
}

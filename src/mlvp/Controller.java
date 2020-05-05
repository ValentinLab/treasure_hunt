package mlvp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    // ----- Attributs -----

    Board board;
    MainFrame mf;

    // ----- Constructeur -----

    Controller(MainFrame f) {
        board = new Board();
        mf = f;

        initFrame();
    }

    // ----- Fonctions -----

    public void actionPerformed(ActionEvent e) {
        // Source de l'action
        Object source = e.getSource();

        // Nouveau tour de jeu
        if(source == mf.getNextTurnBtn()) {
            board.playRound();
            drawGrid();
        }
    }

    public void initFrame() {
        // Affichage initial de la grille
        int boardSize = board.getSize();
        mf.initGrid(boardSize);
        drawGrid();

        // Affichage initial des joueurs
        int playersNb = board.getPlayersNumber();
        mf.getAboutPanel().setLayout(new GridLayout(playersNb, 1));
        for(int i = 0; i < playersNb; ++i) {
            Hunter player = board.getPlayer(i);
            mf.getAboutPanel().add(new JLabel("Personnage " + player.toString() + " " + player.getPos() + " dir " + player.getDir().toString()));
        }
    }

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

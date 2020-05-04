package mlvp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

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

        System.out.println("Src : " + source);
    }

    public void initFrame() {
        // Affichage initial de la grille
        int boardSize = board.getSize();
        mf.getGridPanel().setLayout(new GridLayout(boardSize, boardSize));
        for(int i = 0; i < boardSize; ++i) {
            for(int j = 0; j < boardSize; ++j) {
                String cellData = board.getCell(j, i).toString();
                JLabel cellLabel = new JLabel(cellData, SwingConstants.CENTER);
                switch(cellData) {
                    case ".":
                        cellLabel.setBackground(Color.GRAY);
                        break;
                    case "#":
                        cellLabel.setBackground(Color.BLUE);
                        break;
                    case "+":
                        cellLabel.setBackground(Color.RED);
                        break;
                    case "T":
                        cellLabel.setBackground(Color.YELLOW);
                        break;
                    default:
                        cellLabel.setBackground(Color.GRAY);
                        break;
                }
                cellLabel.setOpaque(true);
                cellLabel.setBorder(new LineBorder(Color.BLACK));

                mf.getGridPanel().add(cellLabel);
            }
        }

        // Affichage initial des joueurs
        int playersNb = board.getPlayersNumber();
        mf.getAboutPanel().setLayout(new GridLayout(playersNb, 1));
        for(int i = 0; i < playersNb; ++i) {
            Hunter player = board.getPlayer(i);
            mf.getAboutPanel().add(new JLabel("Personnage " + player.toString() + " " + player.getPos() + " dir " + player.getDir().toString()));
        }
    }
}

package view;

import controller.EditController;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Interface graphique de l'éditeur de plateau
 *
 * @author Medhi Louison et Valentin Perignon
 */
public class EditFrame extends JFrame {
    // ----- Attributs -----

    int gridSize;

    private JButton validateBtn;
    private JButton cancelBtn;

    private JRadioButton[] toolsRadios;

    private JButton[][] gridBtns;

    private JButton addWallBtn;
    private JButton cancelWallBtn;

    // ----- Constructeur -----

    public EditFrame() {
        // Création de la fenêtre
        super("Chasse au trésor - Édition d'un plateau");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 650);

        // Contrôleur de la fenêtre
        EditController contr = new EditController(this);

        // Container de la fenêtre
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // --- Panel "Outils" ---
        JPanel toolsPanel = new JPanel(new GridLayout(2, 1));
        // Boutons
        JPanel validatePanel= new JPanel();
        toolsPanel.add(validatePanel);
        cancelBtn = new JButton("Retour au menu");
        cancelBtn.addActionListener(contr);
        validatePanel.add(cancelBtn);
        validateBtn = new JButton("Valider le plateau");
        validateBtn.setEnabled(false);
        validateBtn.addActionListener(contr);
        validatePanel.add(validateBtn);
        // Choix de l'outil de dessin
        JPanel radioPanel = new JPanel();
        toolsPanel.add(radioPanel);
        ButtonGroup toolsBR = new ButtonGroup();
        toolsRadios = new JRadioButton[3];
        String[] radiosLabels = {"Trésor", "Joueur", "Téléportation"};
        for(int i = 0; i < 3; ++i) {
            toolsRadios[i] = new JRadioButton(radiosLabels[i]);
            toolsBR.add(toolsRadios[i]);
            toolsRadios[i].addActionListener(contr);
            radioPanel.add(toolsRadios[i]);
        }
        toolsRadios[0].setSelected(true);

        // --- Panel "Grid" ---
        gridSize = 12;
        JPanel gridPanel = new JPanel(new GridLayout(gridSize, gridSize));
        gridBtns = new JButton[gridSize][gridSize];
        for(int y = 0; y < gridSize; ++y) {
            for(int x = 0; x < gridSize; ++x) {
                gridBtns[x][y] = new JButton("");
                gridBtns[x][y].setBorder(new LineBorder(Color.BLACK));
                gridBtns[x][y].setOpaque(true);
                gridBtns[x][y].setBackground(Color.LIGHT_GRAY);

                if(y == 0 || y == gridSize-1) {
                    gridBtns[x][y].setBackground(Color.RED);
                    if(x > 0 && x < gridSize-1) {
                        gridBtns[x][y].setText(String.valueOf(x));
                    }
                } else if(x == 0 || x == gridSize-1) {
                    gridBtns[x][y].setBackground(Color.RED);
                    if(y > 0 && y < gridSize-1) {
                        gridBtns[x][y].setText(String.valueOf(y));
                    }
                } else {
                    gridBtns[x][y].addActionListener(contr);
                }

                gridPanel.add(gridBtns[x][y]);
            }
        }

        // --- Panel "Ajout d'un mur" ---
        JPanel wallPanel = new JPanel();
        addWallBtn = new JButton("Ajouter un mur");
        addWallBtn.addActionListener(contr);
        wallPanel.add(addWallBtn);
        cancelWallBtn = new JButton("Annuler l'ajout du mur");
        cancelWallBtn.setEnabled(false);
        cancelWallBtn.addActionListener(contr);
        wallPanel.add(cancelWallBtn);

        // Ajout des panels
        c.add(toolsPanel, BorderLayout.NORTH);
        c.add(gridPanel, BorderLayout.CENTER);
        c.add(wallPanel, BorderLayout.SOUTH);

        // Affichage
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ----- Getters -----

    public int getGridSize() {
        return gridSize;
    }

    public JButton getValidateBtn() {
        return validateBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public JRadioButton getToolBtn(int index) {
        return toolsRadios[index];
    }

    public JButton getGridBtn(int x, int y) {
        return gridBtns[x][y];
    }

    public JButton getAddWallBtn() {
        return addWallBtn;
    }

    public JButton getCancelWallBtn() {
        return cancelWallBtn;
    }
}

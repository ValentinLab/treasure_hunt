package view;

import controller.EditController;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class EditFrame extends JFrame {
    // ----- Attributs -----

    private JButton validateBtn;
    private JButton cancelBtn;

    private JRadioButton[] toolsRadios;

    private JButton[][] gridBtns;

    private JTextField xStartField;
    private JTextField yStartField;
    private JTextField xEndField;
    private JTextField yEndField;
    private JButton addWall;

    // ----- Constructeur -----

    public EditFrame() {
        // Création de la fenêtre
        super("Chasse au trésor - Édition d'un plateau");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 700);

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
        validateBtn = new JButton("Valider le plateau");
        validateBtn.setEnabled(false);
        validateBtn.addActionListener(contr);
        validatePanel.add(validateBtn);
        cancelBtn = new JButton("Annuler");
        cancelBtn.addActionListener(contr);
        validatePanel.add(cancelBtn);
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

        // --- Panel "Grid" ---
        JPanel gridPanel = new JPanel(new GridLayout(10, 10));
        gridBtns = new JButton[10][10];
        for(int y = 0; y < 10; ++y) {
            for(int x = 0; x < 10; ++x) {
                gridBtns[x][y] = new JButton("");
                gridBtns[x][y].setBorder(new LineBorder(Color.BLACK));
                gridBtns[x][y].addActionListener(contr);
                gridPanel.add(gridBtns[x][y]);
            }
        }

        // --- Panel "Ajout d'un mur" ---
        JPanel wallPanel = new JPanel(new GridLayout(3, 1));
        // Position de départ
        JPanel startWallPanel = new JPanel();
        wallPanel.add(startWallPanel);
        startWallPanel.add(new JLabel("Position de départ du mur (x et y)"));
        xStartField = new JTextField("1");
        xStartField.setColumns(2);
        yStartField = new JTextField("1");
        yStartField.setColumns(2);
        startWallPanel.add(xStartField);
        startWallPanel.add(yStartField);
        // Position de fin
        JPanel endWallPanel = new JPanel();
        wallPanel.add(endWallPanel);
        endWallPanel.add(new JLabel("Position de fin du mur (x et y)"));
        xEndField = new JTextField("1");
        xEndField.setColumns(2);
        yEndField = new JTextField("1");
        yEndField.setColumns(2);
        endWallPanel.add(xEndField);
        endWallPanel.add(yEndField);
        // Bouton
        JPanel btnWallPanel = new JPanel();
        wallPanel.add(btnWallPanel);
        addWall = new JButton("Ajouter le mur");
        addWall.addActionListener(contr);
        btnWallPanel.add(addWall);

        // Ajout des panels
        c.add(toolsPanel, BorderLayout.NORTH);
        c.add(gridPanel, BorderLayout.CENTER);
        c.add(wallPanel, BorderLayout.SOUTH);

        // Affichage
        setVisible(true);
    }

    // ----- Getters -----

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

    public JTextField getXStartField() {
        return xStartField;
    }

    public JTextField getYStartField() {
        return yStartField;
    }

    public JTextField getXEndField() {
        return xEndField;
    }

    public JTextField getYEndField() {
        return yEndField;
    }

    public JButton getAddWall() {
        return addWall;
    }
}

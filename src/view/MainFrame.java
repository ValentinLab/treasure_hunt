package view;

import controller.MainController;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // ----- Attributs -----

    JButton playBtn;
    JButton editBtn;

    // ----- Constructeur -----

    public MainFrame() {
        // Création de la fenêtre
        super("Chasse au trésor - Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 300);

        // Contrôleur de la fenêtre
        MainController contr = new MainController(this);

        // Container de la fenêtre
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // --- Panel "Titre" ---
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("CHASSE AU TRÉSOR", SwingConstants.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        titlePanel.add(title);

        // --- Panel "Menu de boutons" ---
        JPanel menuPanel = new JPanel(new GridLayout(2, 1));
        playBtn = new JButton("Lancer le jeu");
        playBtn.addActionListener(contr);
        menuPanel.add(playBtn);
        editBtn = new JButton("Créer un terrain de jeu");
        editBtn.addActionListener(contr);
        menuPanel.add(editBtn);

        // Ajout des panels
        c.add(titlePanel, BorderLayout.NORTH);
        c.add(menuPanel, BorderLayout.CENTER);

        // Affichage
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ----- Getters -----

    public JButton getPlayBtn() {
        return playBtn;
    }
}

import java.awt.Dimension;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Box;

public class Main {

    // Constructor

    public Main() {}

    public static void main (String[] args) throws InterruptedException {
        // -------------- Initialising object of MemoryGame and GameActions classes -------------- //
        MemoryGame game = new MemoryGame();
        GameActions actions = new GameActions();
        // -------------------------------------------------------------------- //

        // -------------- Initializing frame for the GUI -------------- //
        JFrame frame = new JFrame ("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ------------------------------------------------------------ //

        // -------------- Main panel that will hold all sub-panels along y-axis -------------- //
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize (new Dimension (600, 600));
        mainPanel.setLayout (new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.BLACK);
        // ---------------------------------------------------------------------------------- //

        // -------------- Panel for title and image -------------- //
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout (new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        titlePanel.setPreferredSize(new Dimension(titlePanel.getPreferredSize().width, -65));
        titlePanel.setBackground(Color.BLACK);

        JLabel titleText = new JLabel("Test Your Memory ");
        titleText.setForeground(Color.decode("#119cda"));
        titleText.setFont(new Font("Arial", Font.BOLD, 26));

        ImageIcon titleIcon = new ImageIcon("images/brain.jpg");
        titleIcon.setImage(titleIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
        JLabel titleIconLabel = new JLabel(titleIcon);

        titlePanel.add(titleText);
        titlePanel.add(titleIconLabel);
        mainPanel.add(titlePanel);
        // ------------------------------------------------------- //

        // -------------- Panel for new game button -------------- //
        JPanel newButtonPanel = new JPanel();
        newButtonPanel.setLayout (new FlowLayout(FlowLayout.CENTER));
        newButtonPanel.setPreferredSize(new Dimension(newButtonPanel.getPreferredSize().width, -90));
        newButtonPanel.setBackground(Color.BLACK);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(80, 26));
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        newGameButton.setForeground(Color.WHITE);

        newButtonPanel.add(newGameButton);
        mainPanel.add(newButtonPanel);
        // ------------------------------------------------------- //

        // -------------- Panel for turns tracker -------------- //
        JPanel turnsPanel = new JPanel();
        turnsPanel.setLayout (new FlowLayout(FlowLayout.CENTER));
        turnsPanel.setPreferredSize(new Dimension(turnsPanel.getPreferredSize().width, -110));
        turnsPanel.setBackground(Color.BLACK);

        JLabel turnsLabel = new JLabel("Turns: " + String.valueOf(game.getTurns()) + "/15");
        turnsLabel.setForeground(Color.decode("#fc9200"));
        turnsLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        turnsPanel.add(turnsLabel);
        mainPanel.add(turnsPanel);
        // ---------------------------------------------------- //

        // -------------- Panel for flippable images -------------- //
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout (new GridLayout(3, 4, 2, 2));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        gridPanel.setPreferredSize(new Dimension(gridPanel.getPreferredSize().width, 300));
        gridPanel.setBackground(Color.BLACK);

        for (int i = 0; i < 12; i++) {
            // At start, we will display the default image of the CustomIcon.
            JLabel label = new JLabel(game.getCustomIcon(i).getDefaultIconImage());
            // Add client property so that properties of CustomIcon object can be tracked.
            label.putClientProperty("customIcon", game.getCustomIcon(i));
            // Add listener to the JLabel
            actions.addMouseListener(label, game, turnsLabel);
            gridPanel.add(label);
        }

        mainPanel.add(gridPanel);
        // ------------------------------------------------------- //

        // -------------- Add action listener to new game button defined inside newButtonPanel above -------------- //
        actions.addActionListener(newGameButton, gridPanel, game, turnsLabel);
        // ---------------------------------------------------- //

        // -------------- Decreasing white space -------------- //
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        // ---------------------------------------------------- //

        // -------------- Adding main panel to frame and setting it as visible -------------- //
        frame.add(mainPanel);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
        // --------------------------------------------------------------------------------- //
    }
}
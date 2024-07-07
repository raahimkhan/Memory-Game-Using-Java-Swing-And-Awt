import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameActions {

    // Constructor

    public GameActions() {}

    public void addMouseListener(JLabel label, MemoryGame game, JLabel turnsLabel) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // If the player has used up all turns, do nothing
                if (game.getTurns() >= 15) {
                    return;
                }
                // Only two cards can be flipped in one turn
                if (game.getIconsFlipped() == 2) {
                    return;
                }
                JLabel currentClickedLabel = (JLabel) e.getSource();
                CustomIcon currentFlippedIcon = (CustomIcon) currentClickedLabel.getClientProperty("customIcon");
                // If the card is already matched or if it's already been flipped, just ignore this click
                if (currentFlippedIcon.getIsDone() == true || currentFlippedIcon.getIsFlipped() == true) {
                    return;
                }
                // If this is the first card to be flipped in a turn, just flip it
                else if (game.getPreviousFlippedCard() == null) {
                    currentClickedLabel.setIcon(currentFlippedIcon.getFlippedIconImage());
                    currentFlippedIcon.setIsFlipped(true);
                    game.incrementIconsFlipped();
                    game.setPreviousFlippedCard(currentClickedLabel);
                }
                // If this is the second card to be flipped in a turn, flip it and
                // try to match it.
                else if (game.getPreviousFlippedCard() != null) {
                    currentClickedLabel.setIcon(currentFlippedIcon.getFlippedIconImage());
                    game.incrementIconsFlipped();
                    JLabel previousLabel = game.getPreviousFlippedCard();
                    CustomIcon previousFlippedIcon = (CustomIcon) previousLabel.getClientProperty("customIcon");
                    // Show the flipped card for one second, and then try to match it
                    Timer timer = new Timer(1000, new ActionListener() {
                        // This action is performed after the timer has been expired
                        @Override
                        public void actionPerformed(ActionEvent action_listener) {
                            // If the flipped card matches the previously flipped card
                            // match them, and check for completion of the game.
                            if (previousFlippedIcon.getFlippedIconPath().equals(currentFlippedIcon.getFlippedIconPath())) {
                                previousFlippedIcon.setIsDone(true);
                                currentFlippedIcon.setIsDone(true);
                                game.incrementPairsFound();
                                game.incrementTurns();
                                turnsLabel.setText("Turns: " + String.valueOf(game.getTurns()) + "/15");
                                game.resetIconsFlipped();
                                game.setPreviousFlippedCard(null);
                                if (game.getPairsFound() == 6) {
                                    JOptionPane.showMessageDialog(null, "Congratulations! You won!");
                                    return;
                                }
                            }
                            // Else, flip back both cards
                            else {
                                game.incrementTurns();
                                turnsLabel.setText("Turns: " + String.valueOf(game.getTurns()) + "/15");
                                previousFlippedIcon.setIsFlipped(false);
                                currentClickedLabel.setIcon(currentFlippedIcon.getDefaultIconImage());
                                previousLabel.setIcon(previousFlippedIcon.getDefaultIconImage());
                                game.resetIconsFlipped();
                                game.setPreviousFlippedCard(null);
                            }
                            // In either case, if there have been 15 turns, end the game.
                            if (game.getTurns() == 15) {
                                JOptionPane.showMessageDialog(null, "Maximum turns reached! Game over!");
                                return;
                            }
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        });
    }

    public void addActionListener(JButton newGameButton, JPanel panel4, MemoryGame game, JLabel turnsLabel) {
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset everything
                game.resetGame();
                turnsLabel.setText("Turns: " + String.valueOf(game.getTurns()) + "/15");
                // Repaint the components inside gridPanel. gridPanel contains the icons that are to be displayed.
                // We will update because icons array has been reshuffled using the resetGame() method.
                Component[] components = panel4.getComponents();
                int counter = 0;
                for (Component component : components) {
                    if (component instanceof JLabel) {
                        ((JLabel)component).setIcon(game.getCustomIcon(counter).getDefaultIconImage());
                        ((JLabel)component).putClientProperty("customIcon", game.getCustomIcon(counter));
                        counter = counter + 1;
                    }
                }
                JOptionPane.showMessageDialog(null, "New game started!");
            }
        });
    }
}
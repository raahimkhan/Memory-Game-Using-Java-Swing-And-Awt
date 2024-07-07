import javax.swing.JLabel;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGame {

    // Class variables

    private int turns; // To store how many turns a user has taken. Total allowed are 15.
    private CustomIcon[] icons; // Array of CustomIcon objects.
    private JLabel previousFlippedCard; // Will store reference to JLabel that was flipped first.
    private int iconsFlipped; // At a time, user is allowed to flip 2 icons at max.
    private int pairsFound; // To keep track of how many pairs found. If 6 found, user wins.

    // Constructor

    public MemoryGame() {
        turns = 0; // Initialize turns to 0.
        iconsFlipped = 0; // Initialize icons flipped to 0 at start.
        pairsFound = 0; // Initialize pairs found to 0 at start.
        previousFlippedCard = null;

        icons = new CustomIcon[12];
        String[] flowerLocations = {
            "images/flower1.jpg",
            "images/flower2.jpg",
            "images/flower3.jpg",
            "images/flower4.jpg",
            "images/flower5.jpg",
            "images/flower6.jpg"
        };
        for (int i = 0; i < 12; i++) {
            String currentFlowerLocation = flowerLocations[i % 6]; // 6 because we have 6 flowers and we will be mapping them twice onto icons array.
            CustomIcon icon = new CustomIcon(currentFlowerLocation);
            icons[i] = icon;
        }

        // Shuffle the icons array to make it random.
        List<CustomIcon> iconsList = new ArrayList<>(Arrays.asList(icons));
        Collections.shuffle(iconsList);
        iconsList.toArray(icons);
    }

    // Getters

    public int getTurns() {
        return turns;
    }

    public CustomIcon getCustomIcon(int index) {
        return icons[index];
    }

    public JLabel getPreviousFlippedCard() {
        return previousFlippedCard;
    }

    public int getIconsFlipped() {
        return iconsFlipped;
    }

    public int getPairsFound() {
        return pairsFound;
    }

    // Setters

    public void incrementTurns() {
        turns = turns + 1;
    }

    public void resetTurns() {
        turns = 0;
    }

    public void setPreviousFlippedCard(JLabel previousFlippedCard) {
        this.previousFlippedCard = previousFlippedCard;
    }

    public void incrementIconsFlipped() {
        iconsFlipped = iconsFlipped + 1;
    }

    public void resetIconsFlipped() {
        iconsFlipped = 0;
    }

    public void incrementPairsFound() {
        pairsFound = pairsFound + 1;
    }

    public void resetPairsFound() {
        pairsFound = 0;
    }

    // Function to reset the entire game state to start a new game.

    public void resetGame() {
        turns = 0;
        previousFlippedCard = null;
        iconsFlipped = 0;
        pairsFound = 0;

        // Re-shuffle the icons array to make it random for new game.
        List<CustomIcon> iconsList = new ArrayList<>(Arrays.asList(icons));
        Collections.shuffle(iconsList);
        iconsList.toArray(icons);

        // Reset isDone and isFlipped variables for all CustomIcon objects.
        for (int i = 0; i < 12; i++) {
            icons[i].setIsDone(false);
            icons[i].setIsFlipped(false);
        }
    }
}
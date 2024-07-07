import java.awt.Image;
import javax.swing.ImageIcon;

public class CustomIcon {

    // Class variables

    private String defaultIconPath; // Path that contains the default icon.
    private String flippedIconPath; // Path that contains the flipped icon.
    private Boolean isDone; // Whether the icon and its pair has been found or not.
    private Boolean isFlipped; // Whether the icon is currently flipped or not.
    ImageIcon defaultIconImage; // Image of the default icon.
    ImageIcon flippedIconImage; // Image of the flipped icon.

    // Constructor

    public CustomIcon(String flippedIconPath) {
        this.defaultIconPath = "images/covered.jpg";
        this.flippedIconPath = flippedIconPath;
        this.isDone = false;
        this.isFlipped = false;

        this.defaultIconImage = new ImageIcon(this.defaultIconPath);
        Image oldDefaultIconImage = this.defaultIconImage.getImage();
        Image updatedDefaultIconImage = oldDefaultIconImage.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        this.defaultIconImage = new ImageIcon(updatedDefaultIconImage);

        this.flippedIconImage = new ImageIcon(this.flippedIconPath);
        Image oldFlippedIconImage = this.flippedIconImage.getImage();
        Image updatedFlippedIconImage = oldFlippedIconImage.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        this.flippedIconImage = new ImageIcon(updatedFlippedIconImage);
    }

    // Getters

    public String getDefaultIconPath() {
        return defaultIconPath;
    }

    public String getFlippedIconPath() {
        return flippedIconPath;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public Boolean getIsFlipped() {
        return isFlipped;
    }

    public ImageIcon getDefaultIconImage() {
        return defaultIconImage;
    }

    public ImageIcon getFlippedIconImage() {
        return flippedIconImage;
    }

    // Setters

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public void setIsFlipped(Boolean isFlipped) {
        this.isFlipped = isFlipped;
    }
}
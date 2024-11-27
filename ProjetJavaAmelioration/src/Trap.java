import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Trap extends SolidSprite {
    private boolean triggered = false;

    public Trap(double x, double y, double width, double height) throws IOException {
        super(x, y, ImageIO.read(new File("./img/trap.png")), width, height);  // Assurer que l'image est chargée correctement
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void trigger() {
        this.triggered = true;
    }

}
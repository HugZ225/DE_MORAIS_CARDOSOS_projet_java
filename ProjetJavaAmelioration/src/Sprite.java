import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Sprite implements Displayable{
    protected double x;
    protected double y;
    protected final Image image;
    protected final double width;
    protected final double height;

    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,(int)x,(int)y,null);
    }

    //getter pour les autres classes
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }
}

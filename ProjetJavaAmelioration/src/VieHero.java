import java.awt.*;

public class VieHero extends DynamicSprite{
    private int health;
    private HealthBar healthBar;

    public VieHero(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
        this.health = 100;
        this.healthBar = new HealthBar(health);
    }

    public HealthBar getHealthBar() {
        return this.healthBar;
    }
    public int getHealth() {
        return this.health;
    }
}

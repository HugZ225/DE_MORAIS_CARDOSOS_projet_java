import java.awt.Color;
import java.awt.Graphics;

public class HealthBar implements Displayable{
    private int maxHealth;
    private int currentHealth;

    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void reduceHealth(int damage) {
        currentHealth= Math.max(0, currentHealth - damage);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(20, 20, 200, 20);
        g.setColor(Color.GREEN);
        int healthWidth = (int) ((currentHealth / (double) maxHealth) * 200);  // Ajuster la largeur de la barre en fonction de la santé
        g.fillRect(20, 20, healthWidth, 20);
        g.setColor(Color.BLACK);
        g.drawRect(20, 20, 200, 20);
    }
    public int getHealth() {
        return currentHealth;
    }
}

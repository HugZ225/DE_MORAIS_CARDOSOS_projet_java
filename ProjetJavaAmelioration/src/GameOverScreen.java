import javax.swing.*;
import java.awt.*;

public class GameOverScreen {
    public void printgameover() {
        JFrame frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 36));
                g.drawString("GAME OVER", getWidth() / 2 -120, getHeight() / 2);
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }
}

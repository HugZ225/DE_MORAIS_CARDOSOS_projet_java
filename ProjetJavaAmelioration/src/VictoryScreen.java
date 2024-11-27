import javax.swing.*;
import java.awt.*;

public class VictoryScreen extends JPanel {

    public void printvictoryscreen() {
        JPanel winPanel = new JPanel();
        winPanel.setBackground(Color.BLACK);
        JLabel winLabel = new JLabel("You Win", JLabel.CENTER);
        winLabel.setFont(new Font("Arial", Font.BOLD, 48));
        winLabel.setForeground(Color.WHITE);
        winPanel.add(winLabel);
        JFrame winFrame = new JFrame("Victoire");
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setSize(400, 200);
        winFrame.setLocationRelativeTo(null);
        winFrame.setContentPane(winPanel);
        winFrame.setVisible(true);

        new Timer(3000, e -> System.exit(0)).start();
    }
}
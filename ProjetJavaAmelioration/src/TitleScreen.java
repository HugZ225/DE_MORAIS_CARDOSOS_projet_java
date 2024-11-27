import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class TitleScreen extends JFrame {

    public TitleScreen(StartGame startGame) {
        setTitle("Écran Titre");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel backgroundLabel = new JLabel(new ImageIcon("./img/tree.png"));
        backgroundLabel.setBounds(0, 100, 400, 600);
        add(backgroundLabel);
        JLabel backgroundLabel2 = new JLabel(new ImageIcon("./img/trap.png"));
        backgroundLabel2.setBounds(50, 100, 400, 600);
        add(backgroundLabel2);
        JLabel backgroundLabel3 = new JLabel(new ImageIcon("./img/rock.png"));
        backgroundLabel3.setBounds(-50, 100, 400, 600);
        add(backgroundLabel3);

        JLabel titleLabel = new JLabel("Bienvenue !!!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start !");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener((ActionEvent e) -> {
            dispose(); // Fermer l'écran titre une fois le bouton  appuyé
            try {
                startGame.launchGameScreen();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        add(startButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}

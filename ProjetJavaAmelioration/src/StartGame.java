import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class StartGame extends JFrame {
    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    HealthBar healthBar = new HealthBar(100);
    Playground level;


    public StartGame() throws Exception {
        // On lance notre écran titre
        SwingUtilities.invokeLater(() -> new TitleScreen(this));
    }


    public void launchGameScreen() throws Exception {
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(1000, 900);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        VieHero hero = new VieHero(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);
        HealthBar healthBar = new HealthBar(100);

        renderEngine = new RenderEngine(displayZoneFrame);
        Playground level = new Playground("./data/level1.txt", renderEngine);
        renderEngine.setGameTimer(level.getGameTimer());

        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, level, healthBar);


        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        renderTimer.start();

        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        gameTimer.start();

        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());
        physicTimer.start();

        Timer timerDisplay = new Timer(1000, (e) -> {
            int remainingTime = level.getGameTimer().getRemainingTime();
            if (remainingTime > 0) {
                System.out.println("Temps restant : " + remainingTime + " secondes.");
            } else {
                renderTimer.stop();
                gameTimer.stop();
                physicTimer.stop();
            }
        });
        timerDisplay.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        renderEngine.addToRenderList(healthBar);

        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }
}

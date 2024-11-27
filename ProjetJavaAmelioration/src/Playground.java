import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();
    private Image imageTrap;
    private RenderEngine renderEngine;
    private boolean gameOver = false; // Indique si le jeu est terminé
    private GameTimer gameTimer;

    public boolean isGameOver() {
        return gameOver;
    }

    public Playground (String pathName, RenderEngine renderEngine) {
        this.renderEngine = renderEngine;
        gameTimer = new GameTimer(
                () -> {
                    GameOverScreen gameOverScreen = new GameOverScreen();
                    gameOverScreen.printgameover();
                },
                () -> {
                    VictoryScreen victoryScreen = new VictoryScreen();
                    victoryScreen.printvictoryscreen();
                }
        );

        try{
            final Image imageTree = ImageIO.read(new File("./img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/rock.png"));
            imageTrap = ImageIO.read(new File("./img/trap.png"));


            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            final int imageTrapWidth = imageTree.getWidth(null);
            final int imageTrapHeight = imageTree.getHeight(null);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line!= null){
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element){
                        case 'T' : environment.add(new SolidSprite(columnNumber*imageTreeWidth,
                                lineNumber*imageTreeHeight,imageTree, imageTreeWidth, imageTreeHeight));
                                    break;
                        case ' ' :
                            //System.out.println("Création d'un Sprite pour la sortie à la position (" + columnNumber + ", " + lineNumber + ")");
                            environment.add(new Sprite(columnNumber*imageGrassWidth,
                                lineNumber*imageGrassHeight, imageGrass, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R' : environment.add(new SolidSprite(columnNumber*imageRockWidth,
                                lineNumber*imageRockHeight, imageRock, imageRockWidth, imageRockHeight));
                            break;
                        case 'P' : environment.add(new Sprite(columnNumber*imageTrapWidth,
                                lineNumber*imageTrapHeight,imageTrap, imageTrapWidth, imageTrapHeight));
                        // on rajoute un sprite . dans le code afin que celui-ci puisse etre detecté
                        case '.':
                            environment.add(new Sprite(columnNumber * imageGrassWidth,
                                    lineNumber * imageGrassHeight, null, imageGrassWidth, imageGrassHeight));
                    }
                    columnNumber++;
                }
                columnNumber =0;
                lineNumber++;
                line=bufferedReader.readLine();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
     public GameTimer getGameTimer() {
      return gameTimer;
    }

    public ArrayList<Sprite> getSolidSpriteList(){
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Displayable> getSpriteList(){
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }

    //On crée une méthode VerifierCollision qui controle toutes les collisions et permet en conséquences d'afficher
    //l'écran adapté, de faire baisser la vie etc...
    public void verifierCollision(VieHero hero, HealthBar healthBar) {
        boolean gameOver = false;
        boolean victory = false;

        for (Sprite sprite : environment) {
            // Vérifie si le sprite correspond à un piège
            if (sprite.getImage() == imageTrap) {
                if (collision(hero, sprite)) {
                    healthBar.reduceHealth(50);
                    renderEngine.repaint();

                    if (healthBar.getHealth() <= 0) {
                        //System.out.println("La vie du héros est tombée à 0. Game Over !");
                        GameOverScreen gameOverScreen = new GameOverScreen();
                        gameOverScreen.printgameover();
                        gameTimer.stopTimer();
                        gameOver = true;
                        break;
                    }
                }
            }

            // Vérifie si le sprite correspond à la sortie (point '.')
            if (sprite.getImage() == null) {
                if (collision(hero, sprite)) {
                    //System.out.println("Collision détectée avec la sortie !");
                    VictoryScreen victoryScreen = new VictoryScreen();
                    victoryScreen.printvictoryscreen();
                    gameTimer.playerWon();
                    victory = true;
                    break;
                }
            }
        }

        if (gameOver || victory) {
            return;
        }
    }



    private boolean collision(VieHero hero, Sprite sprite) {
        double heroX = hero.getX();
        double heroY = hero.getY();
        double heroWidth = hero.getWidth();
        double heroHeight = hero.getHeight();

        double spriteX = sprite.getX();
        double spriteY = sprite.getY();
        double spriteWidth = sprite.getWidth();
        double spriteHeight = sprite.getHeight();

        //System.out.println("Vérification collision : Héros (" + heroX + ", " + heroY + "), Piège (" + spriteX + ", " + spriteY + ")");

        return heroX < spriteX + spriteWidth &&
                heroX + heroWidth > spriteX &&
                heroY < spriteY + spriteHeight &&
                heroY + heroHeight > spriteY;
    }
}

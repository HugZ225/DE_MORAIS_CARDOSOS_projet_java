import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    VieHero hero; //Notre hero n'est plus un DynamicSprite mais un VieHero
    Playground playground;
    HealthBar healthBar;

    public GameEngine(VieHero hero, Playground playground, HealthBar healthBar) {
        this.hero = hero;
        this.playground = playground;
        this.healthBar = healthBar;
    }

    @Override
    public void update() {
        playground.verifierCollision(hero, healthBar);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }



    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_R:
                hero.accelerate();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_R :
                hero.speed_initial();
                break;
        }

    }
}

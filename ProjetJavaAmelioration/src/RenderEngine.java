import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;
    private HealthBar healthBar;
    private GameTimer gameTimer;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
        this.healthBar = new HealthBar(100);
        addToRenderList(healthBar);
    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    public void setGameTimer(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject:renderList) {
            renderObject.draw(g);
        }
        if (gameTimer != null) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Temps restant : " + gameTimer.getRemainingTime() + "s", 500, 30);
        }
    }

    @Override
    public void update(){
        repaint();
    }
}

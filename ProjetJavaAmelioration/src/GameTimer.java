import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTimer {
    private static final int TIME_LIMIT = 30;
    private int remainingTime = TIME_LIMIT;
    private ScheduledExecutorService scheduler;
    private boolean isGameOver = false;
    private boolean gameWon = false;
    private Runnable onGameOver;
    private Runnable onVictory;

    public GameTimer(Runnable onGameOver, Runnable onVictory) {
        this.onGameOver = onGameOver;
        this.onVictory = onVictory;
        startTimer();
    }

    private void startTimer() {
        scheduler = Executors.newScheduledThreadPool(1);
        Runnable countdownTask = new Runnable() {
            @Override
            public void run() {
                if (remainingTime > 0 && !isGameOver && !gameWon) {
                    remainingTime--;
                    System.out.println("Temps restant : " + remainingTime + " secondes.");
                }
                if (remainingTime == 0 && !isGameOver && !gameWon) {
                    System.out.println("Temps écoulé ! Game Over !");
                    isGameOver = true;
                    onGameOver.run();
                }
            }
        };

        scheduler.scheduleAtFixedRate(countdownTask, 1, 1, TimeUnit.SECONDS);
    }

    public void playerWon() {
        if (!isGameOver && !gameWon) {
            gameWon = true;
            System.out.println("Vous avez gagné !");
            onVictory.run();
            scheduler.shutdown();
        }
    }

    public void stopTimer() {
        isGameOver = true;
        scheduler.shutdown();
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }
}
package app.ui.views.animations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceAnimationPanel extends JPanel {

    private ImageIcon[] diceFaces;
    private JLabel diceLabel;
    private JLabel diceLabel2;
    private Thread animationThread;
    private volatile boolean animationRunning;
    private int currentFrame;

    public DiceAnimationPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(110, 80));

        // Create a label to display the dice face image
        diceLabel = new JLabel();
        add(diceLabel, BorderLayout.WEST);

        diceLabel2 = new JLabel();
        add(diceLabel2, BorderLayout.EAST);

        // Load dice face images
        diceFaces = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            String imagePath = "C:\\Users\\efeas\\IdeaProjects\\2023S_TeamMC\\app-service\\src\\main\\java\\app\\" +
                    "__resource__\\assets\\Dice\\Alea_" + (i + 1) + ".png";
            diceFaces[i] = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 50,
                    Image.SCALE_SMOOTH));
        }
    }

    public void startDiceAnimation(int settlingFace1, int settlingFace2) {
        if (animationThread != null && animationThread.isAlive()) {
            return; // Animation already running
        }

        currentFrame = 0;
        animationRunning = true;

        animationThread = new Thread(() -> {
            while (animationRunning && currentFrame < 30) {
                SwingUtilities.invokeLater(() -> {
                    diceLabel.setIcon(diceFaces[currentFrame % 5]);
                    diceLabel2.setIcon(diceFaces[(currentFrame + 1) % 5]);
                });

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                currentFrame++;
            }

            SwingUtilities.invokeLater(() -> {
                diceLabel.setIcon(diceFaces[settlingFace1 - 1]);
                diceLabel2.setIcon(diceFaces[settlingFace2 - 1]);
            });
        });

        animationThread.start();
    }

    public void stopDiceAnimation() {
        animationRunning = false;
        currentFrame = 0;

        SwingUtilities.invokeLater(() -> {
            diceLabel.setIcon(null);
            diceLabel2.setIcon(null);
        });
    }
}

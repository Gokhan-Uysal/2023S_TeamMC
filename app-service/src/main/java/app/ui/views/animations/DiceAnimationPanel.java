package app.ui.views.animations;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DiceAnimationPanel extends JPanel {

    private ImageIcon[] diceFaces;
    private JLabel diceLabel;
    private JLabel diceLabel2;
    private SwingWorker<Void, Integer> animationWorker;
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
        if (animationWorker != null && !animationWorker.isDone()) {
            return; // Animation already running
        }

        currentFrame = 0;

        animationWorker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (!isCancelled() && currentFrame < 30) {
                    publish(currentFrame);
                    Thread.sleep(100);
                    currentFrame++;
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int frame = chunks.get(chunks.size() - 1);
                diceLabel.setIcon(diceFaces[frame % 5]);
                diceLabel2.setIcon(diceFaces[(frame + 1) % 5]);
            }

            @Override
            protected void done() {
                diceLabel.setIcon(diceFaces[settlingFace1 - 1]);
                diceLabel2.setIcon(diceFaces[settlingFace2 - 1]);
            }
        };

        animationWorker.execute();
    }

    public void stopDiceAnimation() {
        if (animationWorker != null && !animationWorker.isDone()) {
            animationWorker.cancel(true);
        }

        diceLabel.setIcon(null);
        diceLabel2.setIcon(null);
    }
}

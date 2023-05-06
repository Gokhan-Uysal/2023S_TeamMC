package app.ui.views.game.player;

import javax.swing.*;

public class PlayerStatePanel extends JPanel {

    public JButton nextButton;
    public JLabel gameStateLabel;

    public PlayerStatePanel() {
        buildPanel();
    }

    public void buildPanel() {
        nextButton = new JButton("Next");
        gameStateLabel = new JLabel("Current State Here...");

        this.add(nextButton);
        this.add(gameStateLabel);
    }

    public void updateGameStateLabel(String info) {
        gameStateLabel.setText(info);
    }

    public void updateNextButtonText(String info) {
        nextButton.setText(info);
    }

}

package app.ui.views.game.player;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TurnStatePanel extends JPanel {
    private JLabel _currentPlayerLabel;

    public TurnStatePanel() {
        _currentPlayerLabel = new JLabel("Player: N/A");
        this.add(_currentPlayerLabel);
    }

    public void updateView(String currentUser) {
        _currentPlayerLabel.setText(String.format("Player: %s", currentUser));
    }
}

package app.ui.views.game.player;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TurnStatePanel extends JPanel {
    private JLabel _currentPlayerLabel;

    public TurnStatePanel() {
        _currentPlayerLabel = new JLabel("Player: N/A");
        Font currentFont = _currentPlayerLabel.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), 16);
        _currentPlayerLabel.setFont(newFont);
        this.add(_currentPlayerLabel);
    }

    public void updateView(String currentUser) {
        _currentPlayerLabel.setText(String.format("Player: %s", currentUser));
    }
}

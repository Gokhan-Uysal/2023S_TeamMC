package app.ui.views.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import app.ui.controllers.game.player.PlayerStatePanelController;
import app.ui.views.game.player.PlayerStatePanel;

public class Footer extends JPanel {
    private int cornerRadius = 50;

    private PlayerStatePanel _playerStatePanel;
    private PlayerStatePanelController _playerStatePanelController;

    public Footer() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(0, 150));
        setBackground(Color.CYAN);
        setForeground(Color.black);
        initializeComponents();
        addComponents();
    }

    public void initializeComponents() {
        _playerStatePanel = new PlayerStatePanel();
        _playerStatePanelController = new PlayerStatePanelController(_playerStatePanel);
    }

    public void addComponents() {
        this.add(_playerStatePanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
    }
}

package app.ui.views.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import app.ui.views.game.state.BaseStatePanel;
import app.ui.views.game.state.DistributePanel;

public class FooterPanel extends JPanel {
    private int cornerRadius = 50;
    private BaseStatePanel _statePanel;

    public FooterPanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(0, 150));
        setBackground(Color.lightGray);
        setForeground(Color.black);
        initializeComponents();
        addComponents();
    }

    public void initializeComponents() {
        _statePanel = new DistributePanel();
    }

    public void addComponents() {
        this.add(_statePanel);
    }

    public void updateStatePanel(BaseStatePanel baseStatePanel) {
        _statePanel = baseStatePanel;
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

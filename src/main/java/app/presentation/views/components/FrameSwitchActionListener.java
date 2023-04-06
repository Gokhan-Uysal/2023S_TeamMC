package app.presentation.views.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public abstract class FrameSwitchActionListener implements ActionListener {

    private JFrame currentFrame;
    private JFrame targetFrame;

    public FrameSwitchActionListener(JFrame currentFrame, JFrame targetFrame) {
        this.currentFrame = currentFrame;
        this.targetFrame = targetFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame.setVisible(false);
        targetFrame.setVisible(true);
    }

}
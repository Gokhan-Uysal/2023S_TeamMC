package app.ui.views.components;

import javax.swing.*;
import java.awt.*;

public class AlertPane extends JOptionPane {
    public AlertPane(Component parentCompoent, String title, String message, int mesageType) {
        super();
        AlertPane.showMessageDialog(parentCompoent, message, title, messageType);
    }
}

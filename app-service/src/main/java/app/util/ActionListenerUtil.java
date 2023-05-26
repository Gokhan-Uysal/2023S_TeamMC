package app.util;

import java.awt.event.ActionListener;
import javax.swing.AbstractButton;

public class ActionListenerUtil {
    public static void addActionListener(AbstractButton button, ActionListener listener) {
        button.addActionListener(listener);
    }
}

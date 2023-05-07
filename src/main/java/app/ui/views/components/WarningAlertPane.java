package app.ui.views.components;

import java.awt.Component;

public class WarningAlertPane extends AlertPane {

    public WarningAlertPane(Component parentCompoent, String message) {
        super(parentCompoent, "Warning", message, AlertPane.WARNING_MESSAGE);

    }

}

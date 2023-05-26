package app.ui.views.components;

import java.awt.Component;

public class ErrorAlertPanel extends AlertPane {

    public ErrorAlertPanel(Component parentCompoent, String message) {
        super(parentCompoent, "Error", message, AlertPane.ERROR_MESSAGE);
    }

}

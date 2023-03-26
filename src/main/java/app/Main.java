package app;

import java.awt.BorderLayout;

import app.common.AppConfig;
import app.presentation.views.App;
import app.presentation.views.Body.Map;
import app.presentation.views.Footer.Footer;
import app.presentation.views.Header.Header;

public class Main {
    public static void main(String[] args) {
        App app = new App(AppConfig.title, AppConfig.appSize, AppConfig.color1);
        app.setLocation(AppConfig.screenSize);
        app.setVisible(true);

        // Add main components here
        Map gameMap = new Map();
        app.add(gameMap, BorderLayout.CENTER);

        Header header = new Header(50);
        app.add(header, BorderLayout.NORTH);

        Footer footer = new Footer(50);
        app.add(footer, BorderLayout.SOUTH);

        app.refresh();
    }
}
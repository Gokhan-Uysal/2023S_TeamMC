package app;

import java.awt.BorderLayout;

import app.common.AppConfig;
import app.presentation.views.Game;
import app.presentation.views.Body.Map;
import app.presentation.views.Footer.Footer;
import app.presentation.views.Header.Header;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(AppConfig.title, AppConfig.appSize, AppConfig.color1);
        game.setLocation(AppConfig.screenSize);
        game.setVisible(true);

        // Add main components here
        Map gameMap = new Map(100, 100);
        game.add(gameMap, BorderLayout.CENTER);

        Header header = new Header(50, AppConfig.color2);
        game.add(header, BorderLayout.NORTH);

        Footer footer = new Footer(50, AppConfig.color3);
        game.add(footer, BorderLayout.SOUTH);

        game.refresh();
    }
}
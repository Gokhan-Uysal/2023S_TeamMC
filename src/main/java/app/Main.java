package app;

import java.awt.BorderLayout;

import app.common.AppConfig;
import app.presentation.views.Game;
import app.presentation.views.Body.Body;
import app.presentation.views.Body.Map;
import app.presentation.views.Footer.Footer;
import app.presentation.views.Header.Header;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(AppConfig.title, AppConfig.appSize, AppConfig.color1);
        game.setLocation(AppConfig.screenSize);
        game.setVisible(true);

        Header header = new Header(50, AppConfig.color2);
        game.add(header, BorderLayout.NORTH);

        Body body = new Body(new Map(100, 150));
        game.add(body);

        Footer footer = new Footer(50, AppConfig.color3);
        game.add(footer, BorderLayout.SOUTH);

        game.refresh();
    }
}
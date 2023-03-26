package app;

import app.common.AppConfig;
import app.presentation.views.App;

public class Main {
    public static void main(String[] args) {
        App app = new App(AppConfig.title, AppConfig.appSize, AppConfig.color1);
        app.setLocation(AppConfig.screenSize);
        app.setVisible(true);
        /*
         * Add main components here
         */

        app.refresh();
    }
}
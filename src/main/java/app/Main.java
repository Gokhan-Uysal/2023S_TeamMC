package app;

import javax.swing.SwingUtilities;

import app.common.AppConfig;
import app.domain.services.Map.MapFactory;
import app.domain.services.Map.MapGraphService;
import app.domain.services.Map.MapReadService;
import app.domain.services.Map.MapService;
import app.domain.services.PlayerService;
import app.ui.controllers.login.LoginFrameController;
import app.ui.views.game.GameFrame;
import app.ui.views.login.LoginFrame;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //
            LoginFrame loginFrame = new LoginFrame("Login", AppConfig.appSize);
            LoginFrameController loginFrameController = new LoginFrameController(loginFrame);

            ArrayList<String> newNames = new ArrayList<String>();
            newNames.add("Berke");
            newNames.add("Mehmet");
            newNames.add("Gökhan");

            //Test of Game State Changer
            PlayerService.createPlayer(newNames);

            Point location = loginFrame.getLocation();
            loginFrame.dispose();

            // Initialize dependencies
            MapReadService mapReadService = new MapReadService("src/main/java/app/resources/map.json");
            MapGraphService mapGraphService = new MapGraphService();
            MapFactory mapFactory = new MapFactory(mapReadService, mapGraphService);
            MapService mapService = new MapService(mapFactory);
            // Create the GameFrame object with the initialized dependencies
            new GameFrame(AppConfig.title, AppConfig.appSize, location, mapService);
        });
    }
}
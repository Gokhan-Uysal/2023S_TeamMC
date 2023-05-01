package app;

import javax.swing.SwingUtilities;

import app.common.AppConfig;

import app.domain.services.Map.MapService;
import app.domain.services.PlayerService;
import app.ui.views.game.GameFrame;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            ArrayList<String> newNames = new ArrayList<String>();
            newNames.add("Berke");
            newNames.add("Mehmet");
            newNames.add("Gökhan");
            PlayerService.createPlayer(newNames);
            MapService mapService = new MapService();
            new GameFrame(AppConfig.title, AppConfig.appSize, AppConfig.appLocation, mapService);
        });
    }
}
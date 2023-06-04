package app;


import app.common.errors.DbException;
import app.domain.models.game.map.Territory;
import app.domain.services.JsonSaveLoadService;
import app.domain.services.map.MapReadService;
import app.ui.views.menu.MainMenuFrame;

import java.util.List;

public class Main {
    public static void main(String[] args) throws DbException {
        MapReadService mrs = new MapReadService("app-service/src/main/java/app/__resource__/map.json");
        mrs.buildGameMapData();
        List<Territory> list = mrs.getGameMapTerritories();
        System.out.println(list);
        JsonSaveLoadService js = new JsonSaveLoadService();
        js.saveMap(list);
        List<Territory> newList = js.loadMap();
        System.out.println(newList);
        /*

        SwingUtilities.invokeLater(() -> {
            new MainMenuFrame();
            PlayerRepository pr = new PlayerRepository();
            try {
                pr.findPlayers(0, 0).forEach(p -> {
                    System.out.println(p);
                });
            } catch (DbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        */
    }
}
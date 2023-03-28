package app.presentation.views.Body;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.presentation.views.Body.Map.Map;

public class Body extends JPanel {
    public Map gameMap;

    public Body(Map gameMap) {
        buildClass(gameMap);
        buildView();
    }

    private void buildView() {
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(gameMap), BorderLayout.CENTER);
    }

    private void buildClass(Map gameMap) {
        this.gameMap = gameMap;
        gameMap.loadMap();
        gameMap.drawMap();
    }
}

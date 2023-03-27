package app.presentation.views.Body;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Body extends JPanel {
    public Map gameMap;

    public Body(Map gameMap) {
        buildView(gameMap);
    }

    public void buildView(Map gameMap) {
        this.gameMap = gameMap;
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(gameMap,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, BorderLayout.CENTER);
        gameMap.loadMap();
        gameMap.drawMap();
    }
}

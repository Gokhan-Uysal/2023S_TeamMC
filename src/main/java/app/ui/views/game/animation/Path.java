package app.ui.views.game.animation;

import java.awt.Point;

public interface Path {
    public Point nextPosition();
    public boolean hasMoreSteps();
}


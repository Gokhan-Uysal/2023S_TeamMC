package app.ui.views.game.animation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class Line extends JPanel {
    int xStart, yStart, xLimit, yLimit;
    Image image;
    Path myPath;

    public Line(int xStart, int yStart,
                int xLimit, int yLimit) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xLimit = xLimit;
        this.yLimit = yLimit;

        try
            {this.image = ImageIO.read(new File("src/main/java/app/__resource__/assets/animation/arrow.png"));}
        catch (IOException e)
            {e.printStackTrace();}

        myPath = new StraightLinePath(xStart, yStart, xLimit, yLimit, 50);
    }
    public void paint(Graphics g) {
        super.paint(g);
        Point pos = myPath.nextPosition();

        g.drawImage(image, (int)pos.getX(), (int)pos.getY(), 20, 30,null);

        if (! myPath.hasMoreSteps()) {
            if (pos.getX() == xStart)
                myPath = new StraightLinePath(xStart, yStart, xLimit, yLimit, 50);
            else
                myPath = new StraightLinePath(xLimit, yLimit, xStart, yStart, 50);
        }    }
}

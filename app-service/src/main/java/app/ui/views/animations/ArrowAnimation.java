package app.ui.views.animations;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ArrowAnimation extends SwingWorker<Void, List<Point>> {
    private final JPanel panel;
    private final Point startPoint;
    private final List<Point> endPoints;

    public ArrowAnimation(JPanel panel, Point startPoint, List<Point> endPoints) {
        this.panel = panel;
        this.startPoint = startPoint;
        this.endPoints = endPoints;
    }

    @Override
    protected Void doInBackground() throws Exception {
        int numSteps = 100;
        for (int i = 1; i <= numSteps; i++) {
            if (isCancelled()) {
                break;
            }

            double ratio = (double) i / numSteps;
            List<Point> intermediatePoints = new ArrayList<>();
            for (Point endPoint : endPoints) {
                int x = (int) (startPoint.x + ratio * (endPoint.x - startPoint.x));
                int y = (int) (startPoint.y + ratio * (endPoint.y - startPoint.y));
                intermediatePoints.add(new Point(x, y));
            }

            Thread.sleep(10);
            publish(intermediatePoints);
        }

        return null;
    }

    @Override
    protected void process(List<List<Point>> chunks) {
        List<Point> latestChunk = chunks.get(chunks.size() - 1);

        // Create a BufferedImage with the same dimensions as the panel
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setStroke(new BasicStroke(3)); // Set the line thickness
        g2d.setColor(Color.BLACK);

        // Draw the lines on the BufferedImage
        for (Point endPoint : latestChunk) {
            int x1 = startPoint.x;
            int y1 = startPoint.y;
            int x2 = endPoint.x;
            int y2 = endPoint.y;

            int dx = x2 - x1;
            int dy = y2 - y1;
            double angle = Math.atan2(dy, dx);
            int length = (int) Math.sqrt(dx * dx + dy * dy);
            int arrowLength = 15;
            int arrowAngle = 30;

            int x3 = (int) (x2 - arrowLength * Math.cos(angle - Math.toRadians(arrowAngle)));
            int y3 = (int) (y2 - arrowLength * Math.sin(angle - Math.toRadians(arrowAngle)));
            int x4 = (int) (x2 - arrowLength * Math.cos(angle + Math.toRadians(arrowAngle)));
            int y4 = (int) (y2 - arrowLength * Math.sin(angle + Math.toRadians(arrowAngle)));

            g2d.drawLine(x1, y1, x2, y2);
            g2d.drawLine(x2, y2, x3, y3);
            g2d.drawLine(x2, y2, x4, y4);
        }

        g2d.dispose();

        // Paint the BufferedImage onto the panel
        Graphics panelGraphics = panel.getGraphics();
        panelGraphics.drawImage(image, 0, 0, null);
        panelGraphics.dispose();
    }
}
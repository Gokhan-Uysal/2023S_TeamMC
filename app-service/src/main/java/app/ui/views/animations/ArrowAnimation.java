package app.ui.views.animations;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArrowAnimation extends SwingWorker<Void, java.util.List<Point>> {
    private final JPanel panel;
    private final Point startPoint;
    private final java.util.List<Point> endPoints;

    public ArrowAnimation(JPanel panel, Point startPoint, java.util.List<Point> endPoints) {
        this.panel = panel;
        this.startPoint = startPoint;
        this.endPoints = endPoints;
    }

    @Override
    protected Void doInBackground() throws Exception {
        int steps = 100;
        int delay = 10;

        for (int i = 0; i <= steps; i++) {
            java.util.List<Point> intermediatePoints = new ArrayList<>();

            for (Point endPoint : endPoints) {
                int startX = (int) (startPoint.x + 50 * Math.cos(getAngle(startPoint, endPoint)));
                int startY = (int) (startPoint.y + 50 * Math.sin(getAngle(startPoint, endPoint)));
                int endX = endPoint.x;
                int endY = endPoint.y;

                int dx = endX - startX;
                int dy = endY - startY;

                int x = startX + dx * i / steps;
                int y = startY + dy * i / steps;

                intermediatePoints.add(new Point(x, y));
            }

            publish(intermediatePoints);
            Thread.sleep(delay);
        }

        return null;
    }

    @Override
    protected void process(java.util.List<java.util.List<Point>> chunks) {
        List<Point> lastIntermediatePoints = chunks.get(chunks.size() - 1);
        panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());

        for (Point endPoint : endPoints) {
            drawLine(panel.getGraphics(), (int) (startPoint.x + 50 * Math.cos(getAngle(startPoint, endPoint))),
                    (int) (startPoint.y + 50 * Math.sin(getAngle(startPoint, endPoint))), endPoint.x, endPoint.y);
        }

        for (Point intermediatePoint : lastIntermediatePoints) {
            drawLine(panel.getGraphics(), (int) (startPoint.x + 50 * Math.cos(getAngle(startPoint, intermediatePoint))),
                    (int) (startPoint.y + 50 * Math.sin(getAngle(startPoint, intermediatePoint))),
                    intermediatePoint.x, intermediatePoint.y);
        }
    }

    private void drawLine(Graphics g, int startX, int startY, int endX, int endY) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4)); // Increase line thickness here

        g2d.drawLine(startX, startY, endX, endY);

        g2d.dispose();
    }

    private double getAngle(Point startPoint, Point endPoint) {
        return Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);
    }
}
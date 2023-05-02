package app.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class AppConfig {
    // Color palette https://lospec.com/palette-list/pollen8
    public static final Color color1 = new Color(0x73464c);
    public static final Color color2 = new Color(0xab5675);
    public static final Color color3 = new Color(0xee6a7c);
    public static final Color color4 = new Color(0xffa7a5);
    public static final Color color5 = new Color(0xffe07e);
    public static final Color color6 = new Color(0xffe7d6);
    public static final Color color7 = new Color(0x72dcbb);
    public static final Color color8 = new Color(0x34acba);

    // Title
    public static final String title = "ConKUeror";

    // App size
    private static final int width = 1280;
    private static final int height = 800;
    public static final Dimension appSize = new Dimension(width, height);

    public static final Dimension helpScreenSize = new Dimension(width / 2, height / 2);

    // Screen size
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Location
    private static final int xLocation = (screenSize.width / 2) - width / 2;
    private static final int yLocation = (screenSize.height / 2) - height / 2;
    public static final Point appLocation = new Point(xLocation, yLocation);

    // Paths
    public static final String basePath = "src/main/java/app";
}

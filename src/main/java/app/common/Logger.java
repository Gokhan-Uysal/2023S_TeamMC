package app.common;

public class Logger {
    public static void info(Layer layer, String info){
        System.out.printf("[INFO] [%s] %s%n", layer, info);
    }

    public static void warning(Layer layer, String message){
        System.out.printf("[WARNING] [%s] %s%n", layer, message);
    }

    public static void error(Layer layer, Exception e){
        System.out.printf("[ERROR] [%s] %s%n", layer, e.getMessage());
        e.printStackTrace();
    }

    public static void error(Layer layer, String message){
        System.out.printf("[ERROR] [%s] %s%n", layer, message);
    }
}

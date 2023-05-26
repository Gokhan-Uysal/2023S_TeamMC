package app.common;

public class Logger {

    public static void log(String message) {
        System.out.println(message);
    }

    public static void info(String info) {
        System.out.printf("[INFO]: %s%n", info);
    }

    public static void warning(String message) {
        System.out.printf("[WARNING]: %s%n", message);
    }

    public static void error(Exception e) {
        System.out.printf("[ERROR]: %s%n", e.getMessage());
        e.printStackTrace();
    }

    public static void error(String message) {
        System.out.printf("[ERROR]: %s%n", message);
    }
}

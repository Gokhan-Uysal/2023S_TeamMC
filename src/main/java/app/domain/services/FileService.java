package app.domain.services;

import java.io.*;
import java.util.*;

public class FileService {
    private static final String FILENAME = "data.csv";

    public static void writeData(String username, String password) {
        try {
            FileWriter writer = new FileWriter(FILENAME, true);
            writer.write(username + ";" + password + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkData(String username, String password) {
        try {
            File file = new File(FILENAME);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(";");
                if (fields[0].equals(username) && fields[1].equals(password)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}

package app.domain.services;

import java.io.*;
import java.util.*;

public class FileService {
    private static final String FILENAME = "src/main/java/app/resource/assets/data.csv";

    public static void writeData(String username, char[] password) {
        try {
            String passwordString = new String(password);
            FileWriter writer = new FileWriter(FILENAME, true);
            writer.write(username + ";" + passwordString + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkData(String username, char[] password) {
        try {
            String passwordString = new String(password);
            File file = new File(FILENAME);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                String[] fields = line.split(";");

                if (fields[0].equals(username) && fields[1].equals(passwordString)) {
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

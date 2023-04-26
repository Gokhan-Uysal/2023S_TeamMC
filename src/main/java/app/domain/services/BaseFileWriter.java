package app.domain.services;

import java.io.FileWriter;
import java.io.IOException;

public class BaseFileWriter {

    public static void saveRegistrationInfo(String username, char[] password, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            // Hash the password and save the user info to the CSV file
            String passwordString = new String(password);

            writer.append(username);
            writer.append(";");
            writer.append(passwordString);
            writer.append("\n");
            System.out.println(username);
            System.out.println(passwordString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package util;

import model.UserModel;
import java.io.*;
import java.util.*;

public class FileManager {

    private static final String DATA_FILE = "users_data.csv";

    public static void exportToCSV(UserModel model) {
        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            writer.append("ID,Name,Email,Class,DOB,Age\n");

            model.getUsers().forEach((id, userInfo) -> {
                try {
                    String age = String.valueOf(DateValidator.calculateAge(userInfo[2]));
                    writer.append(id).append(",")
                            .append(userInfo[0]).append(",")
                            .append(userInfo[1]).append(",")
                            .append(userInfo[3]).append(",")
                            .append(userInfo[2]).append(",")
                            .append(age).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("Data exported to " + DATA_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile(UserModel model, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(model.getUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String[]> loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            return (Map<String, String[]>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }
}

package util;

import model.UserModel;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupManager {

    private static final String BACKUP_DIR = "backups/";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    static {
        File dir = new File(BACKUP_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static String createBackup(UserModel model) {
        String filename = BACKUP_DIR + "backup_" + LocalDateTime.now().format(formatter) + ".dat";
        FileManager.saveToFile(model, filename);
        System.out.println("✓ Backup created: " + filename);
        return filename;
    }

    public static boolean restoreFromBackup(UserModel model, String filename) {
        java.util.Map<String, String[]> data = FileManager.loadFromFile(filename);
        if (!data.isEmpty()) {
            data.forEach((id, userInfo) -> {
                try {
                    model.addUser(id, userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4],
                            userInfo[5]);
                } catch (IllegalArgumentException e) {
                    // Skip invalid entries
                }
            });
            System.out.println("✓ Restored from backup: " + filename);
            return true;
        }
        return false;
    }

    public static String[] listBackups() {
        File dir = new File(BACKUP_DIR);
        return dir.list();
    }
}

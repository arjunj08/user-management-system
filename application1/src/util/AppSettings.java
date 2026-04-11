package util;

import java.io.*;
import java.util.*;

public class AppSettings {

    private static final String SETTINGS_FILE = "app_settings.properties";
    private Properties properties = new Properties();

    public AppSettings() {
        loadSettings();
    }

    private void loadSettings() {
        File file = new File(SETTINGS_FILE);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(SETTINGS_FILE)) {
                properties.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            setDefaults();
            saveSettings();
        }
    }

    private void setDefaults() {
        properties.setProperty("app.theme", "light");
        properties.setProperty("app.language", "en");
        properties.setProperty("app.autoBackup", "true");
        properties.setProperty("app.dateFormat", "dd/MM/yyyy");
    }

    public void saveSettings() {
        try (FileOutputStream fos = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(fos, "User Management System Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSetting(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public void setSetting(String key, String value) {
        properties.setProperty(key, value);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    public void setBoolean(String key, boolean value) {
        properties.setProperty(key, String.valueOf(value));
    }
}

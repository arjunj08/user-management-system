package util;

import java.util.Set;
import java.util.UUID;

public class IDGenerator {

    private static final String PREFIX = "USR";

    public static String generateNumericID() {
        return String.valueOf(System.currentTimeMillis() % 1000000);
    }

    public static String generateUniqueID(Set<String> existingIDs) {
        String id;
        do {
            id = PREFIX + System.nanoTime() % 1000000;
        } while (existingIDs.contains(id));
        return id;
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

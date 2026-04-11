package model;

import java.util.*;
import java.util.stream.Collectors;
import util.DateValidator;
import util.EmailValidator;

public class UserModel {

    private Map<String, String[]> users = new HashMap<>();
    private List<String> operationHistory = new ArrayList<>();

    public boolean exists(String id) {
        return users.containsKey(id);
    }

    public boolean emailExists(String email, String excludeId) {
        return users.values().stream()
                .anyMatch(u -> u[1].equalsIgnoreCase(email) && !excludeId.equals(
                        users.entrySet().stream()
                                .filter(e -> Arrays.equals(e.getValue(), u))
                                .map(Map.Entry::getKey)
                                .findFirst()
                                .orElse("")));
    }

    // Format: name, email, cls, dob, phone, address
    public void addUser(String id, String name, String email, String cls, String dob, String phone, String address) {
        if (!EmailValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!DateValidator.isValidDate(dob)) {
            throw new IllegalArgumentException("Invalid date format (use dd/MM/yyyy)");
        }
        users.put(id, new String[]{name, EmailValidator.sanitizeEmail(email), cls, dob, phone, address});
        addHistory("Added user: " + id);
    }

    public void deleteUser(String id) {
        if (users.remove(id) != null) {
            addHistory("Deleted user: " + id);
        }
    }

    public void updateUser(String id, String name, String email, String cls, String dob, String phone, String address) {
        if (!EmailValidator.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!DateValidator.isValidDate(dob)) {
            throw new IllegalArgumentException("Invalid date format (use dd/MM/yyyy)");
        }
        users.put(id, new String[]{name, EmailValidator.sanitizeEmail(email), cls, dob, phone, address});
        addHistory("Updated user: " + id);
    }

    public String[] getUser(String id) {
        return users.get(id);
    }

    public Map<String, String[]> getUsers() {
        return users;
    }

    public List<Map.Entry<String, String[]>> searchByName(String name) {
        return users.entrySet().stream()
                .filter(e -> e.getValue()[0].toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, String[]>> searchByEmail(String email) {
        return users.entrySet().stream()
                .filter(e -> e.getValue()[1].toLowerCase().contains(email.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Map.Entry<String, String[]>> searchByClass(String cls) {
        return users.entrySet().stream()
                .filter(e -> e.getValue()[2].equalsIgnoreCase(cls))
                .collect(Collectors.toList());
    }

    public int totalUsers() {
        return users.size();
    }

    public int getTotalAge() {
        return users.values().stream()
                .mapToInt(u -> DateValidator.calculateAge(u[3]))
                .sum();
    }

    public double getAverageAge() {
        if (users.isEmpty()) return 0;
        return (double) getTotalAge() / users.size();
    }

    public void addHistory(String operation) {
        operationHistory.add(java.time.LocalDateTime.now() + " - " + operation);
    }

    public List<String> getHistory() {
        return new ArrayList<>(operationHistory);
    }

    public void clearHistory() {
        operationHistory.clear();
    }

    public Map<String, Integer> getUsersByClass() {
        return users.values().stream()
                .collect(Collectors.groupingBy(u -> u[2], Collectors.summingInt(u -> 1)));
    }
}
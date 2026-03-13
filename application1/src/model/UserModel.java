package model;

import java.util.*;

public class UserModel {

    private Map<String, String[]> users = new HashMap<>();

    public boolean exists(String id) {
        return users.containsKey(id);
    }

    public void addUser(String id, String name, String cls, String dob) {
        users.put(id, new String[]{name, cls, dob});
    }

    public void deleteUser(String id) {
        users.remove(id);
    }

    public void updateUser(String id, String name, String cls, String dob) {
        users.put(id, new String[]{name, cls, dob});
    }

    public String[] getUser(String id) {
        return users.get(id);
    }

    public Map<String, String[]> getUsers() {
        return users;
    }

    public int totalUsers() {
        return users.size();
    }
}
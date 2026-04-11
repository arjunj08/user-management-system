package controller;

import view.MainView;
import model.UserModel;
import util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.util.*;

public class MainController {

    private MainView view;
    private UserModel model;
    private boolean isEditMode = false;
    private StatisticsAnalyzer stats;

    public MainController(MainView view, UserModel model) {

        this.view = view;
        this.model = model;
        this.stats = new StatisticsAnalyzer(model);

        // User Management
        view.addBtn.addActionListener(e -> addUser());
        view.updateBtn.addActionListener(e -> updateUser());
        view.deleteBtn.addActionListener(e -> deleteUser());
        view.resetBtn.addActionListener(e -> resetForm());
        view.generateBtn.addActionListener(e -> generateID());

        // Search & Filter
        view.searchBtn.addActionListener(e -> searchUser());
        view.clearSearchBtn.addActionListener(e -> clearSearch());

        // Export & Refresh
        view.exportBtn.addActionListener(e -> exportToCSV());
        view.refreshBtn.addActionListener(e -> refreshTable());

        // Tabs
        view.tabDashboard.addActionListener(e -> showDashboard());
        view.tabHistory.addActionListener(e -> showHistory());
        view.tabUsers.addActionListener(e -> refreshTable());

        // Exit
        view.exitBtn.addActionListener(e -> System.exit(0));

        // Menu - File
        view.fileNew.addActionListener(e -> resetForm());
        view.fileExport.addActionListener(e -> exportToCSV());
        view.fileBackup.addActionListener(e -> createBackup());
        view.fileRestore.addActionListener(e -> restoreBackup());
        view.fileExit.addActionListener(e -> System.exit(0));

        // Menu - Tools
        view.toolsStats.addActionListener(e -> showDetailedStatistics());
        view.toolsValidate.addActionListener(e -> validateAllUsers());
        view.toolsClear.addActionListener(e -> clearAllData());

        // Menu - Help
        view.helpAbout.addActionListener(e -> showAbout());
        view.helpGuide.addActionListener(e -> showUserGuide());

        // Date field listener for age calculation
        view.dobField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                updateAgeDisplay();
            }
        });

        // Table row selection
        view.userTable.getSelectionModel().addListSelectionListener(e -> loadUserFromTable());

        refreshTable();
        System.out.println("✓ User Management System loaded successfully!");
    }

    private void addUser() {

        String id = view.idField.getText().trim();
        String name = view.nameField.getText().trim();
        String email = view.emailField.getText().trim();
        String cls = view.classNameField.getText().trim();
        String dob = view.dobField.getText().trim();
        String phone = view.phoneField.getText().trim();
        String address = view.addressField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || email.isEmpty() || cls.isEmpty() || dob.isEmpty()) {
            JOptionPane.showMessageDialog(view, "❌ Please fill all required fields", "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (model.exists(id)) {
            JOptionPane.showMessageDialog(view, "❌ User ID already exists", "Duplicate Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        FormValidator.ValidationResult result = FormValidator.validate(id, name, email, cls, dob, phone,
                address);
        if (!result.isValid) {
            JOptionPane.showMessageDialog(view, "❌ " + result.message, "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            model.addUser(id, name, email, cls, dob, phone, address);
            refreshTable();
            JOptionPane.showMessageDialog(view, "✓ User added successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            resetForm();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(view, "❌ " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUser() {

        String id = view.idField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(view, "❌ Please enter User ID", "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!model.exists(id)) {
            JOptionPane.showMessageDialog(view, "❌ User not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = view.nameField.getText().trim();
        String email = view.emailField.getText().trim();
        String cls = view.classNameField.getText().trim();
        String dob = view.dobField.getText().trim();
        String phone = view.phoneField.getText().trim();
        String address = view.addressField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || cls.isEmpty() || dob.isEmpty()) {
            JOptionPane.showMessageDialog(view, "❌ Please fill all required fields", "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        FormValidator.ValidationResult result = FormValidator.validate(id, name, email, cls, dob, phone,
                address);
        if (!result.isValid) {
            JOptionPane.showMessageDialog(view, "❌ " + result.message, "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            model.updateUser(id, name, email, cls, dob, phone, address);
            refreshTable();
            JOptionPane.showMessageDialog(view, "✓ User updated successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            resetForm();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(view, "❌ " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteUser() {

        String id = view.idField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(view, "❌ Please enter User ID", "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!model.exists(id)) {
            JOptionPane.showMessageDialog(view, "❌ User not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view,
                "Are you sure you want to delete this user?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            model.deleteUser(id);
            refreshTable();
            JOptionPane.showMessageDialog(view, "✓ User deleted successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            resetForm();
        }
    }

    private void searchUser() {

        String searchType = (String) view.searchTypeCombo.getSelectedItem();
        String searchValue = view.searchValueField.getText().trim();

        if (searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(view, "❌ Please enter search value", "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        view.tableModel.setRowCount(0);

        List<Map.Entry<String, String[]>> results = new ArrayList<>();

        if ("ID".equals(searchType)) {
            String[] user = model.getUser(searchValue);
            if (user != null) {
                results.add(new AbstractMap.SimpleEntry<>(searchValue, user));
            }
        } else if ("Name".equals(searchType)) {
            results = model.searchByName(searchValue);
        } else if ("Email".equals(searchType)) {
            results = model.searchByEmail(searchValue);
        } else if ("Class".equals(searchType)) {
            results = model.searchByClass(searchValue);
        }

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(view, "❌ No users found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        results.forEach(entry -> {
            String id = entry.getKey();
            String[] u = entry.getValue();
            int age = DateValidator.calculateAge(u[3]);
            view.tableModel.addRow(new Object[]{id, u[0], u[1], u[2], u[3], u[4], age});
        });
    }

    private void clearSearch() {
        view.searchValueField.setText("");
        view.searchTypeCombo.setSelectedIndex(0);
        refreshTable();
    }

    private void loadUserFromTable() {
        int selectedRow = view.userTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = view.tableModel.getValueAt(selectedRow, 0).toString();
            String[] user = model.getUser(id);
            if (user != null) {
                view.idField.setText(id);
                view.nameField.setText(user[0]);
                view.emailField.setText(user[1]);
                view.classNameField.setText(user[2]);
                view.dobField.setText(user[3]);
                view.phoneField.setText(user[4]);
                view.addressField.setText(user[5]);
                updateAgeDisplay();
                isEditMode = true;
            }
        }
    }

    private void updateAgeDisplay() {
        String dob = view.dobField.getText().trim();
        if (!dob.isEmpty() && DateValidator.isValidDate(dob)) {
            int age = DateValidator.calculateAge(dob);
            if (age >= 0) {
                view.ageLabel.setText("Age: " + age + " years");
            }
        }
    }

    private void generateID() {
        String id = IDGenerator.generateUniqueID(model.getUsers().keySet());
        view.idField.setText(id);
    }

    private void resetForm() {
        view.idField.setText("");
        view.nameField.setText("");
        view.emailField.setText("");
        view.classNameField.setText("");
        view.dobField.setText("");
        view.phoneField.setText("");
        view.addressField.setText("");
        view.ageLabel.setText("Age: --");
        isEditMode = false;
    }

    private void refreshTable() {
        view.tableModel.setRowCount(0);

        model.getUsers().forEach((id, u) -> {
            int age = DateValidator.calculateAge(u[3]);
            view.tableModel.addRow(new Object[]{id, u[0], u[1], u[2], u[3], u[4], age});
        });

        updateStatistics();
    }

    private void updateStatistics() {
        view.totalUsersLabel.setText("📊 Total Users: " + stats.getTotalUsers());

        double avgAge = stats.getAverageAge();
        view.avgAgeLabel.setText("📈 Avg Age: " + String.format("%.1f", avgAge) + " years");

        Map<String, Integer> classDist = model.getUsersByClass();
        StringBuilder classStats = new StringBuilder("📋 Classes: ");
        classDist.forEach((cls, count) -> classStats.append(cls).append("(").append(count).append(") "));
        if (classStats.toString().equals("📋 Classes: ")) {
            view.classStatsLabel.setText("📋 Classes: None");
        } else {
            view.classStatsLabel.setText(classStats.toString());
        }
    }

    private void exportToCSV() {
        if (model.totalUsers() == 0) {
            JOptionPane.showMessageDialog(view, "❌ No users to export", "Export Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        FileManager.exportToCSV(model);
        JOptionPane.showMessageDialog(view, "✓ Data exported to users_data.csv successfully!", "Export Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void createBackup() {
        if (model.totalUsers() == 0) {
            JOptionPane.showMessageDialog(view, "❌ No users to backup", "Backup Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String filename = BackupManager.createBackup(model);
        JOptionPane.showMessageDialog(view, "✓ Backup created successfully!\nFile: " + filename, "Backup Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void restoreBackup() {
        String[] backups = BackupManager.listBackups();
        if (backups == null || backups.length == 0) {
            JOptionPane.showMessageDialog(view, "❌ No backup files found", "Restore Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String selected = (String) JOptionPane.showInputDialog(view, "Select a backup to restore:",
                "Restore Backup", JOptionPane.QUESTION_MESSAGE, null, backups, backups[0]);

        if (selected != null) {
            model.getUsers().clear();
            if (BackupManager.restoreFromBackup(model, "backups/" + selected)) {
                refreshTable();
                JOptionPane.showMessageDialog(view, "✓ Data restored successfully!", "Restore Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "❌ Failed to restore backup", "Restore Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showDashboard() {
        StringBuilder dashboard = new StringBuilder();
        dashboard.append("📊 DASHBOARD STATISTICS\n");
        dashboard.append("═══════════════════════════════\n");
        dashboard.append("Total Users: ").append(stats.getTotalUsers()).append("\n");
        dashboard.append("Average Age: ").append(String.format("%.2f", stats.getAverageAge())).append(" years\n");
        dashboard.append("Youngest: ").append(stats.getYoungestAge()).append(" years old\n");
        dashboard.append("Oldest: ").append(stats.getOldestAge()).append(" years old\n");
        dashboard.append("Most Common Class: ").append(stats.getMostCommonClass()).append("\n");
        dashboard.append("Email Domains: ").append(stats.getEmailDomainsCount()).append("\n");

        Map<String, Integer> classDist = model.getUsersByClass();
        if (!classDist.isEmpty()) {
            dashboard.append("\nClass Distribution:\n");
            classDist.forEach((cls, count) -> dashboard.append("  • ").append(cls).append(": ").append(count)
                    .append(" students\n"));
        }

        dashboard.append("\nRecent Operations:\n");
        List<String> history = model.getHistory();
        if (history.isEmpty()) {
            dashboard.append("  No operations yet");
        } else {
            history.stream().skip(Math.max(0, history.size() - 5)).forEach(op -> dashboard.append("  • ").append(op)
                    .append("\n"));
        }

        JOptionPane.showMessageDialog(view, dashboard.toString(), "Dashboard",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void showHistory() {
        List<String> history = model.getHistory();
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No operation history", "History", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder historyText = new StringBuilder("OPERATION HISTORY\n═══════════════════════════════\n");
        history.forEach(op -> historyText.append(op).append("\n"));

        JTextArea textArea = new JTextArea(historyText.toString());
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 11));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 400));

        JOptionPane.showMessageDialog(view, scrollPane, "Operation History", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showDetailedStatistics() {
        String report = stats.generateReport();
        JTextArea textArea = new JTextArea(report);
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 11));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(600, 500));

        JOptionPane.showMessageDialog(view, scrollPane, "Detailed Statistics", JOptionPane.INFORMATION_MESSAGE);
    }

    private void validateAllUsers() {
        StringBuilder validation = new StringBuilder("USER VALIDATION REPORT\n═══════════════════════════════\n\n");

        int validCount = 0;
        int invalidCount = 0;

        for (Map.Entry<String, String[]> entry : model.getUsers().entrySet()) {
            String id = entry.getKey();
            String[] user = entry.getValue();

            FormValidator.ValidationResult result = FormValidator.validate(id, user[0], user[1], user[2], user[3],
                    user[4], user[5]);

            if (result.isValid) {
                validCount++;
                validation.append("✓ ").append(id).append(" - ").append(user[0]).append("\n");
            } else {
                invalidCount++;
                validation.append("✗ ").append(id).append(" - ").append(result.message).append("\n");
            }
        }

        validation.append("\n═══════════════════════════════\n");
        validation.append("Valid Users: ").append(validCount).append("\n");
        validation.append("Invalid Users: ").append(invalidCount).append("\n");

        JTextArea textArea = new JTextArea(validation.toString());
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 11));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 400));

        JOptionPane.showMessageDialog(view, scrollPane, "Validation Report", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearAllData() {
        int confirm = JOptionPane.showConfirmDialog(view,
                "⚠️ WARNING: This will delete ALL user data!\nAre you absolutely sure?",
                "Clear All Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            int confirm2 = JOptionPane.showConfirmDialog(view,
                    "This action cannot be undone. Proceed?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm2 == JOptionPane.YES_OPTION) {
                model.getUsers().clear();
                model.clearHistory();
                refreshTable();
                JOptionPane.showMessageDialog(view, "✓ All data cleared!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void showAbout() {
        String about = "User Management System Pro v2.0\n\n" +
                "A professional Java Swing application for managing user information.\n\n" +
                "Features:\n" +
                "✓ Complete CRUD Operations\n" +
                "✓ Advanced Search & Filter\n" +
                "✓ CSV Export/Import\n" +
                "✓ Data Backup & Restore\n" +
                "✓ Email Validation\n" +
                "✓ Age Calculator\n" +
                "✓ Detailed Statistics\n" +
                "✓ Transaction History\n\n" +
                "© 2024 - All Rights Reserved\n" +
                "Developed with ❤️ using Java Swing";

        JOptionPane.showMessageDialog(view, about, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showUserGuide() {
        String guide = "USER GUIDE\n═════════════════════════════════════\n\n" +
                "1. ADDING A USER:\n" +
                "   • Fill in all required fields\n" +
                "   • Click 'Add User' or use File > New User\n" +
                "   • Use 'Generate ID' for auto-generation\n\n" +
                "2. SEARCHING:\n" +
                "   • Select search type (ID, Name, Email, Class)\n" +
                "   • Enter search term\n" +
                "   • Click 'Search'\n\n" +
                "3. EDITING:\n" +
                "   • Click on a row in the table to load user data\n" +
                "   • Modify the fields\n" +
                "   • Click 'Update User'\n\n" +
                "4. DELETING:\n" +
                "   • Select the user to delete\n" +
                "   • Click 'Delete User'\n" +
                "   • Confirm the deletion\n\n" +
                "5. EXPORTING:\n" +
                "   • Click 'Export CSV' in toolbar or File menu\n" +
                "   • Data will be saved to users_data.csv\n\n" +
                "6. BACKUP & RESTORE:\n" +
                "   • File > Create Backup (saves current data)\n" +
                "   • File > Restore from Backup (select backup to restore)\n\n" +
                "7. DATE FORMAT:\n" +
                "   • Use dd/MM/yyyy format (e.g., 15/06/2000)\n" +
                "   • Age is automatically calculated\n\n" +
                "Need more help? Check the Dashboard for statistics!";

        JTextArea textArea = new JTextArea(guide);
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 11));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(600, 500));

        JOptionPane.showMessageDialog(view, scrollPane, "User Guide", JOptionPane.INFORMATION_MESSAGE);
    }
}
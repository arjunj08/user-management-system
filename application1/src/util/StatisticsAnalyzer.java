package util;

import model.UserModel;
import java.util.*;

public class StatisticsAnalyzer {

    private UserModel model;

    public StatisticsAnalyzer(UserModel model) {
        this.model = model;
    }

    public int getTotalUsers() {
        return model.totalUsers();
    }

    public double getAverageAge() {
        return model.getAverageAge();
    }

    public int getYoungestAge() {
        return model.getUsers().values().stream()
                .mapToInt(u -> DateValidator.calculateAge(u[3]))
                .min()
                .orElse(-1);
    }

    public int getOldestAge() {
        return model.getUsers().values().stream()
                .mapToInt(u -> DateValidator.calculateAge(u[3]))
                .max()
                .orElse(-1);
    }

    public String getMostCommonClass() {
        Map<String, Integer> classDist = model.getUsersByClass();
        return classDist.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    public long getEmailDomainsCount() {
        return model.getUsers().values().stream()
                .map(u -> u[1].substring(u[1].lastIndexOf('@') + 1))
                .distinct()
                .count();
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("════════════════════════════════════════════════════════\n");
        report.append("                USER STATISTICS REPORT                    \n");
        report.append("════════════════════════════════════════════════════════\n\n");

        report.append("Total Users: ").append(getTotalUsers()).append("\n");
        report.append("Average Age: ").append(String.format("%.2f", getAverageAge())).append(" years\n");
        report.append("Youngest User Age: ").append(getYoungestAge()).append(" years\n");
        report.append("Oldest User Age: ").append(getOldestAge()).append(" years\n");
        report.append("Most Common Class: ").append(getMostCommonClass()).append("\n");
        report.append("Email Domains: ").append(getEmailDomainsCount()).append("\n\n");

        report.append("Class Distribution:\n");
        model.getUsersByClass().forEach((cls, count) -> {
            double percentage = (count * 100.0) / getTotalUsers();
            report.append("  • ").append(String.format("%-20s", cls))
                    .append(count).append(" users (")
                    .append(String.format("%.1f%%", percentage))
                    .append(")\n");
        });

        report.append("\n════════════════════════════════════════════════════════\n");
        return report.toString();
    }
}

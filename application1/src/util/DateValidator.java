package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;

public class DateValidator {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static int calculateAge(String dateStr) {
        try {
            LocalDate birthDate = LocalDate.parse(dateStr, formatter);
            LocalDate today = LocalDate.now();
            return Period.between(birthDate, today).getYears();
        } catch (DateTimeParseException e) {
            return -1;
        }
    }

    public static String getDateFormat() {
        return "dd/MM/yyyy";
    }

    public static boolean isFutureDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return date.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}

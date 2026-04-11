package util;

public class FormValidator {

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.trim().length() >= 2
                && !name.trim().matches(".*\\d+.*");
    }

    public static boolean isValidPhone(String phone) {
        if (phone.isEmpty()) return true; // Optional field
        return phone.replaceAll("[^0-9\\-\\+]", "").length() >= 7;
    }

    public static boolean isValidAddress(String address) {
        if (address.isEmpty()) return true; // Optional field
        return address.trim().length() >= 5;
    }

    public static boolean isValidClassFormat(String className) {
        return className != null && !className.trim().isEmpty() && className.trim().length() <= 20;
    }

    public static ValidationResult validate(String id, String name, String email, String className, String dob,
            String phone, String address) {

        if (id == null || id.trim().isEmpty()) {
            return new ValidationResult(false, "ID cannot be empty");
        }

        if (!isValidName(name)) {
            return new ValidationResult(false, "Name must be at least 2 characters (no numbers)");
        }

        if (!EmailValidator.isValidEmail(email)) {
            return new ValidationResult(false, "Invalid email format");
        }

        if (!isValidClassFormat(className)) {
            return new ValidationResult(false, "Invalid class format");
        }

        if (!DateValidator.isValidDate(dob)) {
            return new ValidationResult(false, "Invalid date format (use dd/MM/yyyy)");
        }

        if (DateValidator.isFutureDate(dob)) {
            return new ValidationResult(false, "Date of birth cannot be in the future");
        }

        if (!isValidPhone(phone)) {
            return new ValidationResult(false, "Invalid phone number format");
        }

        if (!isValidAddress(address)) {
            return new ValidationResult(false, "Address must be at least 5 characters");
        }

        return new ValidationResult(true, "Validation successful");
    }

    public static class ValidationResult {
        public boolean isValid;
        public String message;

        public ValidationResult(boolean isValid, String message) {
            this.isValid = isValid;
            this.message = message;
        }
    }
}

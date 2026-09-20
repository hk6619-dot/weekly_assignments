public class Week2Practice {

    public static void main(String[] args) {
        // Test calls can be placed here
    }

    // 1. Vowel & Consonant Counter
    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;
        String lowerText = text.toLowerCase();

        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            if (c >= 'a' && c <= 'z') {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }
        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    // 2. CSV Student Record Parser
    public static void parseStudentRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.println("Name: " + fields[0].trim() + 
                               " | Roll No: " + fields[1].trim() + 
                               " | Dept: " + fields[2].trim());
        }
    }

    // 3. File Extension Validator
    public static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            return "Rejected — invalid file type";
        }
        
        String extension = filename.substring(dotIndex + 1);
        if (extension.equalsIgnoreCase("pdf") || 
            extension.equalsIgnoreCase("docx") || 
            extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        }
        return "Rejected — invalid file type";
    }

    // 4. Masked Phone Number Formatter
    public static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) return "Invalid phone number";
        
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }
        
        StringBuilder masked = new StringBuilder();
        masked.append("XXXXXX");
        masked.append("-");
        masked.append(phone.substring(6));
        return masked.toString();
    }

    // 5. Bank Transaction Reference Generator & Validator
    public static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed;
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String reference) {
        if (reference.length() != 14) return "Invalid: wrong length";
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) return "Invalid: bank code must be 3 letters";
        }
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) return "Invalid: non-digit body";
        }
        
        StringBuilder formatted = new StringBuilder();
        formatted.append("[").append(reference.substring(0, 3)).append("] DATE: ");
        formatted.append(reference.substring(3, 5)).append("/");
        formatted.append(reference.substring(5, 7)).append("/");
        formatted.append(reference.substring(7, 9));
        formatted.append(" | SEQ: ").append(reference.substring(9));
        
        return formatted.toString();
    }
}
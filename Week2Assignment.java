import java.util.*;

public class Week2Assignment {

    public static void main(String[] args) {
        // Test calls can be placed here
    }

    // 1. ATM PIN Length Validator
    public static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    // 2. Word Reversal Encoder
    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            StringBuilder reversedWord = new StringBuilder(words[i]);
            result.append(reversedWord.reverse().toString());
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    // 3. Product Inventory CSV Parser
    public static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            System.out.println("Product: " + fields[0].trim() + 
                               " | SKU: " + fields[1].trim() + 
                               " | Qty: " + fields[2].trim());
        }
    }

    // 4. Library ISBN Normalizer & Validator
    public static String normalizeCode(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) return trimmed;
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    public static String validateAndFormat(String code) {
        if (code.length() != 13) return "Invalid: wrong length";
        
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) return "Invalid: publisher code must be 3 letters";
        }
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) return "Invalid: non-digit body";
        }
        
        StringBuilder formatted = new StringBuilder();
        formatted.append("[").append(code.substring(0, 3)).append("] YEAR: ");
        formatted.append(code.substring(3, 7));
        formatted.append(" | CATALOG: ").append(code.substring(7));
        
        return formatted.toString();
    }

    // 5. Stop-Word-Filtered Word Frequency Report
    public static void printFilteredWordFrequency(String feedback) {
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        List<String> stopList = Arrays.asList(stopWords);
        
        // Convert to lowercase and replace common punctuation
        String cleanedText = feedback.toLowerCase().replace(".", "").replace(",", "");
        String[] words = cleanedText.split("\\s+");
        
        Map<String, Integer> wordCount = new HashMap<>();
        
        for (String word : words) {
            if (!word.isEmpty() && !stopList.contains(word)) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        
        // Sort by count in descending order
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordCount.entrySet());
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
public class Week1Assignment {

    public static void main(String[] args) {
        // Test calls can be placed here
    }

    // 1. The Exam Hall Seat Duplication Checker
    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean foundDuplicate = false;
        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    foundDuplicate = true;
                }
            }
        }
        if (!foundDuplicate) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    // 2. The Typing Speed Test Accuracy Checker
    public static void checkTypingAccuracy(String original, String typed) {
        int matchCount = 0;
        int firstMismatch = -1;

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matchCount++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = ((double) matchCount / original.length()) * 100;
        
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ", matchCount, original.length(), accuracy);
        if (firstMismatch != -1) {
            System.out.printf("First Mismatch at position %d ('%c' vs '%c')\n", 
                (firstMismatch + 1), original.charAt(firstMismatch), typed.charAt(firstMismatch));
        } else {
            System.out.println("No Mismatches");
        }
    }

    // 3. The Traffic Signal Streak Analyzer
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) return;

        int maxStreak = 0;
        char bestColor = ' ';
        
        int currentStreak = 1;
        char currentColor = signalLog.charAt(0);

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentColor) {
                currentStreak++;
            } else {
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                    bestColor = currentColor;
                }
                currentColor = signalLog.charAt(i);
                currentStreak = 1;
            }
        }
        // Final check for the last streak
        if (currentStreak > maxStreak) {
            maxStreak = currentStreak;
            bestColor = currentColor;
        }

        System.out.println("Longest Streak: '" + bestColor + "' repeated " + maxStreak + " times");
    }

    // 4. The Warehouse Inventory Balancer
    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0, totalB = 0;
        int maxQuantity = -1;
        String maxSection = "";
        int maxIndex = -1;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            if (sectionA[i] > maxQuantity) {
                maxQuantity = sectionA[i];
                maxSection = "Section A";
                maxIndex = i + 1;
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];
            if (sectionB[i] > maxQuantity) {
                maxQuantity = sectionB[i];
                maxSection = "Section B";
                maxIndex = i + 1;
            }
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";
        System.out.printf("Section A Total: %d | Section B Total: %d | Status: %s | Highest Quantity: %d (%s, Item %d)\n",
                totalA, totalB, status, maxQuantity, maxSection, maxIndex);
    }

    // 5. The Movie Review Word Length Profiler
    public static void classifyWordLengths(String review) {
        String[] words = review.split("\\s+");
        int shortCount = 0, mediumCount = 0, longCount = 0;

        for (String word : words) {
            // Remove punctuation for accurate counting
            String cleanWord = word.replaceAll("[^a-zA-Z]", ""); 
            int len = cleanWord.length();
            if (len >= 1 && len <= 4) shortCount++;
            else if (len >= 5 && len <= 8) mediumCount++;
            else if (len >= 9) longCount++;
        }

        System.out.printf("Short: %d | Medium: %d | Long: %d\n", shortCount, mediumCount, longCount);
    }
}
import java.util.Random;

public class Week1Practice {

    public static void main(String[] args) {
        // Test calls can be placed here
    }

    // 1. Rock-Paper-Scissors Game
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) return "Draw";
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void simulateRPSGame() {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int wins = 0, losses = 0, draws = 0;
        int rounds = 5;

        for (int i = 1; i <= rounds; i++) {
            String computerMove = moves[random.nextInt(3)];
            String playerMove = moves[random.nextInt(3)]; // Simulating player input
            String result = playRound(playerMove, computerMove);

            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;

            System.out.println("Round " + i + "\nPlayer: " + playerMove + ", Computer: " + computerMove + " | " + result);
        }
        double winPercentage = ((double) wins / rounds) * 100;
        System.out.printf("Final Summary | Wins: %d | Losses: %d | Draws: %d | Win %%=%.1f%%\n", wins, losses, draws, winPercentage);
    }

    // 2. Palindrome Checker (3 Approaches)
    public static boolean isPalindromeIterative(String text) {
        int left = 0, right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left++) != text.charAt(right--)) return false;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) return true;
        if (text.charAt(0) != text.charAt(text.length() - 1)) return false;
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] arr = text.toCharArray();
        String reversed = "";
        for (int i = arr.length - 1; i >= 0; i--) reversed += arr[i];
        return text.equals(reversed);
    }

    // 3. BMI Calculator for a Team
    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi <= 24.9) return "Normal";
        if (bmi <= 29.9) return "Overweight";
        return "Obese";
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            System.out.printf("Person %d - Height: %.2f m, Weight: %.2f kg | BMI: %.2f | Status: %s\n",
                    (i + 1), heights[i], weights[i], bmi, getBmiStatus(bmi));
        }
    }

    // 4. First Non-Repeating Character
    public static char findFirstNonRepeatingChar(String text) {
        int[] freq = new int[256];
        for (char c : text.toCharArray()) freq[c]++;
        for (char c : text.toCharArray()) {
            if (freq[c] == 1) return c;
        }
        System.out.println("No Non-Repeating Character Found");
        return '\0'; 
    }

    // 5. Reverse Customer Name
    public static String reverseCustomerName(String customerName) {
        String reversed = "";
        for (int i = customerName.length() - 1; i >= 0; i--) {
            reversed += customerName.charAt(i);
        }
        return reversed;
    }
}
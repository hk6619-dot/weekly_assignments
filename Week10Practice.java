import java.util.Arrays;

public class Week10Practice {

    // 1. Vowel and Consonant Counter
    public static void countVowelsAndConsonants(String word) {
        int vowels = 0, consonants = 0;
        String lowerWord = word.toLowerCase();
        for (char c : lowerWord.toCharArray()) {
            if (Character.isLetter(c)) {
                // Check if a character is a vowel (case-insensitive)[cite: 85]
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') vowels++;
                else consonants++; // All other alphabetic characters are consonants[cite: 85]
            }
        }
        System.out.println("Vowels: " + vowels + "\nConsonants: " + consonants);
    }

    // 2. Word Reverser and Palindrome Checker
    public static void reverseAndCheckPalindrome(String word) {
        // Reverse using a mutable text builder[cite: 85]
        String reversed = new StringBuilder(word).reverse().toString();
        System.out.println(reversed);
        if (word.equalsIgnoreCase(reversed)) {
            System.out.println("palindrome");
        } else {
            System.out.println("not a palindrome");
        }
    }

    // 3. Even and Odd Numbers in a List
    public static void countEvenOdd(int[] numbers) {
        int even = 0, odd = 0;
        for (int num : numbers) {
            if (num % 2 == 0) even++; // Use modulo operator to determine even or odd[cite: 86]
            else odd++;
        }
        System.out.println("Even: " + even + "\nOdd: " + odd);
    }

    // 4. In-Place Array Reversal
    public static void reverseArrayInPlace(int[] arr) {
        int left = 0, right = arr.length - 1;
        // Use two pointers starting from beginning and end, swap and move towards center[cite: 86]
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        System.out.println(Arrays.toString(arr));
    }

    // 5. Digit Sum and Number Reversal
    public static void digitSumAndReverse(int number) {
        int sum = 0, reversed = 0;
        while (number > 0) {
            int digit = number % 10; // Extract digits using modulo 10[cite: 86]
            sum += digit;
            reversed = (reversed * 10) + digit; // Build reversed number by multiplying by 10 and adding digit[cite: 86]
            number /= 10; // Remove last digit using integer division by 10[cite: 86]
        }
        System.out.println("Sum of digits: " + sum + "\nReverse: " + reversed);
    }

    // 6. Prime Number Checker
    public static void isPrime(int n) {
        if (n <= 1) {
            System.out.println(n + " is not prime");
            return;
        }
        boolean prime = true;
        // Iterate from 2 up to the square root of the number[cite: 87]
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                prime = false;
                break;
            }
        }
        System.out.println(n + (prime ? " is prime" : " is not prime"));
    }

    // 7. Factorial Calculation
    public static void calculateFactorial(int n) {
        long factorial = 1;
        // Use a loop to multiply numbers from 1 up to the input number, handling 0 factorial naturally[cite: 87]
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println(factorial);
    }

    // 8. Student Grade Assignment
    public static void assignGrade(int marks) {
        // Use an if-else if-else ladder starting from the highest grade boundary[cite: 88]
        if (marks >= 90) System.out.println("Grade A");
        else if (marks >= 75) System.out.println("Grade B");
        else if (marks >= 60) System.out.println("Grade C");
        else if (marks >= 40) System.out.println("Grade D");
        else System.out.println("Grade F");
    }

    // 9. Leap Year Detector
    public static void isLeapYear(int year) {
        // Divisible by 4, but not by 100, unless it is also divisible by 400[cite: 88]
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            System.out.println("Leap year");
        } else {
            System.out.println("Not a leap year");
        }
    }

    // 10. Student Result Card Generator
    public static class Student {
        String name;
        int[] marks;

        public Student(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
        }

        public void generateResultCard() {
            int sum = 0;
            for (int mark : marks) sum += mark;
            double average = (double) sum / marks.length;

            String grade = "F";
            // Conditional logic to determine the grade based on average[cite: 88]
            if (average >= 75) grade = "B";
            else if (average >= 40 && average <= 59) grade = "D";
            // Note: 60-74 range omitted in instructions, defaulting to standard logic or unhandled

            // Convert the student's name to uppercase for printing[cite: 88]
            System.out.printf("%s: Average %.1f, Grade %s\n", name.toUpperCase(), average, grade);
        }
    }

    public static void main(String[] args) {
        // Test calls can be executed here.
    }
}
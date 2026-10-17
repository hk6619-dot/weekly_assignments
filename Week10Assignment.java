import java.util.Arrays;

public class Week10Assignment {

    /**
     * Problem 7: Second-Best Score
     * COMPLEXITY: 
     * - Time Complexity: O(N) where N is the length of scores array, as we visit each score exactly once[cite: 80].
     * - Space Complexity: O(1) as we only use two integer variables (highest and second)[cite: 80].
     * - Comparison: Sorting the array first takes O(N log N) time[cite: 80]. A single O(N) pass is significantly faster for large arrays and avoids mutating the original array.
     */
    public static int secondHighest(int[] scores) {
        int highest = -1;
        int second = -1;
        
        for (int score : scores) {
            if (score > highest) {
                second = highest;
                highest = score;
            } else if (score > second && score != highest) { 
                // Skip a score that equals the current highest[cite: 80]
                second = score;
            }
        }
        return second;
    }

    /**
     * Problem 4: Exam Score Band Counter
     * COMPLEXITY:
     * - Linear Scan Time: O(N)[cite: 81].
     * - Final Solution Time: O(log N) because it performs two binary searches on a sorted array[cite: 81].
     * - Final Solution Space: O(1).
     * - Edge Duplicates: Duplicates at the edges require finding the *first* position >= low and the *first* position > high[cite: 81]. 
     *   A naive binary search stops at *any* match, which would miss duplicates. By searching for the strict boundary indices, the difference correctly yields the total count inclusive of duplicates[cite: 81].
     */
    public static int countInBand(int[] scores, int low, int high) {
        int startBoundary = lowerBound(scores, low);
        int endBoundary = upperBound(scores, high);
        return endBoundary - startBoundary;
    }

    private static int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    /**
     * Problem 8: Duty Roster Rotation
     * COMPLEXITY:
     * - Time Complexity: O(N) where N is the length of names array, as we iterate through the array once to calculate new positions[cite: 82].
     * - Space Complexity: O(N) to store the newly rotated array.
     * - Explanation: Rotating one place at a time, k times, requires O(N * k) operations[cite: 82]. When k is 10^9, this results in billions of operations causing a timeout. Using `k % n` removes full turns, bringing the operations down to a single O(N) pass[cite: 82].
     */
    public static String[] rotateRoster(String[] names, int k) {
        int n = names.length;
        String[] rotated = new String[n];
        k = k % n; // Remove full turns[cite: 82]
        
        for (int i = 0; i < n; i++) {
            // Each name at index i moves to index (i + k) % n[cite: 82]
            rotated[(i + k) % n] = names[i];
        }
        return rotated;
    }

    /**
     * Problem 6: Class Attendance Tracker
     * COMPLEXITY:
     * - Time Complexity: O(N) to traverse the days array exactly once[cite: 83].
     * - Space Complexity: O(1) auxiliary space used.
     * - Single Pass: Yes, both total present days and the longest streak can easily be calculated in a single pass by keeping a running count that grows on every 1 and resets on 0[cite: 83].
     */
    public static void attendanceSummary(int[] days) {
        int presentCount = 0;
        int currentStreak = 0;
        int longestStreak = 0;

        for (int day : days) {
            if (day == 1) {
                presentCount++;
                currentStreak++; // Grows on every 1[cite: 83]
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak; // Remember the largest value it reaches[cite: 83]
                }
            } else {
                currentStreak = 0; // Resets to 0 on every 0[cite: 83]
            }
        }
        System.out.println("Present: " + presentCount + ", Longest streak: " + longestStreak);
    }

    /**
     * Problem 10: Busiest Bus Row
     * COMPLEXITY:
     * - Time Complexity: O(r * c) where r is rows and c is columns, as every cell in the 2D grid is visited once[cite: 84].
     * - Space Complexity: O(1) as we only keep track of maxRowIndex, maxTotal, and the current row's running sum[cite: 84].
     */
    public static void busiestRow(int[][] grid) {
        int maxRowIndex = -1;
        int maxTotal = -1;

        for (int i = 0; i < grid.length; i++) {
            int currentRowTotal = 0;
            for (int j = 0; j < grid[i].length; j++) {
                currentRowTotal += grid[i][j]; // Add up the seats in each row[cite: 84]
            }
            // Update the best row only when a total is strictly larger to naturally break ties with the smaller index[cite: 84]
            if (currentRowTotal > maxTotal) {
                maxTotal = currentRowTotal;
                maxRowIndex = i;
            }
        }
        System.out.println("Row " + maxRowIndex + ", Total " + maxTotal);
    }

    public static void main(String[] args) {
        // Test cases can be run here
    }
}
public class Week4Practice {

    public static void main(String[] args) {
        // Test calls can be placed here
    }

    // L1. Two Sum
    // Uses two nested loops to check every pair of different positions in the array[cite: 6].
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j}; // Returns the two indices immediately[cite: 6].
                }
            }
        }
        return new int[]{}; 
    }

    // L2. Best Time to Buy and Sell Stock
    // Walks through the array once, tracking the lowest price seen so far and the maximum profit[cite: 6].
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    // L3. Contains Duplicate
    // Uses two nested loops to compare every element at position i against every element at position j[cite: 6].
    public static boolean containsDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true; // Return true right away if a match is found[cite: 6].
                }
            }
        }
        return false; // Return false if no matching pair is found[cite: 6].
    }

    // L4. Merge Two Sorted Arrays
    // Uses a while loop and one index pointer for each array to copy elements into a new result array[cite: 6].
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        
        // Copy remaining elements directly onto the end[cite: 6].
        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }
        
        return result;
    }

    // L5. Rotate Array
    // Reduces k using modulo arithmetic and builds a new array based on calculated positions[cite: 6].
    public static int[] rotateArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) return nums;
        
        k = k % nums.length;
        int[] newArray = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            newArray[(i + k) % nums.length] = nums[i]; 
        }
        
        return newArray;
    }
}
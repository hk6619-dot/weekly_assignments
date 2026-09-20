import java.util.*;

public class Week4Assignment {

    public static void main(String[] args) {
        // Test calls can be placed here
    }

    // A1. Product of Array Except Self
    // Solves it in O(n) time using two passes (forward and backward) without using division[cite: 5].
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        
        // Forward pass accumulating the running product to the left[cite: 5].
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        
        // Backward pass multiplying the running product to the right[cite: 5].
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * right;
            right *= nums[i];
        }
        
        return answer;
    }

    // A2. Maximum Subarray
    // Uses Kadane's algorithm to decide whether to extend the running subarray or start fresh[cite: 5].
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }

    // A3. 3Sum
    // Sorts the array first, then uses two inward-moving pointers to find zero-sum pairs while skipping duplicates[cite: 5].
    public static int[][] threeSum(int[] nums) {
        Arrays.sort(nums);
        List<int[]> resultList = new ArrayList<>();
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates for the first element
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    resultList.add(new int[]{nums[i], nums[left], nums[right]});
                    left++;
                    right--;
                    
                    // Skip duplicates for the second and third elements to avoid reporting the same triplet twice[cite: 5].
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return resultList.toArray(new int[0][]);
    }

    // A4. Subarray Sum Equals K
    // Uses running prefix sums combined with a hash map to track the frequency of earlier prefix sums[cite: 5].
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        
        // Base case: to handle subarrays that start from index 0[cite: 5].
        prefixSumMap.put(0, 1);
        
        for (int num : nums) {
            currentSum += num;
            
            if (prefixSumMap.containsKey(currentSum - k)) {
                count += prefixSumMap.get(currentSum - k);
            }
            
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }

    // A5. Find Minimum in Rotated Sorted Array
    // Uses a modified binary search to compare the middle element to the rightmost element in O(log n) time[cite: 5].
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Decides which half of the array the minimum must be hiding in[cite: 5].
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return nums[left];
    }
}
/*

Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:
Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4 
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.

Example 2:
Input: nums = [-2, -1, 2, 1], k = 1
Output: 2 
Explanation: The subarray [-1, 2] sums to 1 and is the longest.

Follow Up:
Can you do it in O(n) time?

Leetcode: https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
Difficulty: Medium

*/

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int longest = 0;
        int currSum = 0;
        Map<Integer, Integer> seen = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            if (currSum == k) {
                longest = i + 1;
            } else if (seen.containsKey(currSum - k)) {
                longest = Math.max(longest, i - seen.get(currSum - k));
            }
            
            seen.put(currSum, seen.getOrDefault(currSum, i));
        }
        
        return longest;
    }
}

/*

Time: O(n).
Space: O(n).

This is another classic sliding window question. It is slightly different from the other classic sliding window algorithm,
in which you literally have a "sliding" window that increases/decreases with the given condition and run in O(2n) time.
We continuously add the i-th element to the currSum and add that (value, i) to the seen map. Whenever we see that we could've
taken a part of the array out (currSum - k) to get the target value, we know that point is a possible solution.

*/

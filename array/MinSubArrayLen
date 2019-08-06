/*

Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

*/

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minimum = Integer.MAX_VALUE;
        int currSum = 0;
        int start = 0;
        int end = 0;
        
        while (end < nums.length) {
            currSum += nums[end];
            
            while (currSum >= s) {
                minimum = Math.min(minimum, end - start + 1);
                currSum -= nums[start];
                start++;
            }
            
            end++;
        }
        
        return minimum == Integer.MAX_VALUE ? 0 : minimum;
    }
}

/*

Time: O(n).
Space: O(1).

Another classic, classic sliding window question. We have start and end pointers. We continuously increase the end pointer
until we see a currSum greater than the target. Then, until currSum >= target, we increment the start point and subtract
nums[start] from currSum -- this is the main logic in the sliding window algorithm. Note that we can use this
sliding window algorithm because of the constraint that the array only contains positive integers.

*/

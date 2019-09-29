/*

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

Leetcode: https://leetcode.com/problems/product-of-array-except-self/
Difficulty: Medium

*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        
        left[0] = nums[0];
        right[right.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < left.length; i++) {
            left[i] = nums[i] * left[i - 1];
            right[right.length - i - 1] = nums[right.length - i - 1] * right[right.length - i];
        }
        
        for (int i = 0; i < nums.length; i++) {
            int leftVal = (i == 0) ? 1 : left[i - 1];
            int rightVal = (i == nums.length - 1) ? 1 : right[i + 1];
            nums[i] = leftVal * rightVal;
        }
        
        return nums;
   
    }
}

/*

Time: O(n).
Space: O(n). Think about how we can reduce to O(1) -- we use the orignal array to store left values and just a 
running variable to calculate the right value;

This is a good example of array question. It does not have any special data structure, but the problem is complex
and the solution is very clever.

The O(n^2) time solution is trivial. For each index i , we traverse through left and right of the array to calcuate
the product. Where is the bottle neck here? We are re-calculating the product of the array that we've already calculated!
This can be improved if we somehow, for index i, the product of the left subarray and the right subarray stored.
So we maintain 2 arrays, one for the product of left subarray and one for the product of right subarray.

Take [2, 3, 4, 5] for example. The first subarray would be [2, 6, 24, 120]. This means for any index i, the product of
left subarray is at i - 1. The right subarray would be [120, 60, 20, 5]. This means for any index i , the product of 
right subarray is at i + 1.

*/

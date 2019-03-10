/*

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.

Leetcode: https://leetcode.com/problems/move-zeroes/
Difficulty: Easy

*/

class Solution {
    public void moveZeroes(int[] nums) {
        int zeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[zeroIndex];
                nums[zeroIndex] = temp;
                zeroIndex++;
            }
        }
    }
}

/*

Time: O(n).
Space: O(1).

We keep two pointers -- slow and fast pointer. Slow pointer keeps track of the earliest zero index. Faster pointer
just iterates through the array incrementally with a for loop. Everytime nums[i] is a nonzero we swap nums[i] and
nums[zeroIndex] then increment zeroIndex by one. Think about why we can simply just do zeroIndex++.

This two pointer method is extremely common in arrays, string, linked lists, etc. Common ways include having 
slow and fast pointer, one from start and one from end, etc.

*/

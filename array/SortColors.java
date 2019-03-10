/*

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Leetcode: https://leetcode.com/problems/sort-colors/
Difficulty: Medium

*/

class Solution {
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, right);
                right--;
            } else {
                i++;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*

Time: O(n).
Space: O(1).

Here we, again, use the two pointer trick. But in this case, we use three pointers: 
1. Incrementing i with for loop.
2. Left pointer for where 0's should go.
3. Right pointer for where 2's should go.

We are only sorting 0's and 2's. After sorting these 1's will automatically be sorted. 

*/

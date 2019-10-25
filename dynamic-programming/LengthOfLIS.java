/*

Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 

Difficulty: Medium:
Leetcode: https://leetcode.com/problems/longest-increasing-subsequence/

*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int solution = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            int curr = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    curr = Math.max(curr, dp[j] + 1);
                }
            }
            dp[i] = curr;
            solution = Math.max(solution, dp[i]);
        }
        return solution;
    }
}

/*

Time: O(n).
Space: O(n).

The most difficult part of dynammic programming questions is noticing that the problem can be solved with dynamic programming!
In this problem, at each index we don't know if we should take it or not. Let A = [1, 5, 6, 3, 4]. At index 1 (value 5), 
should we take this index (as in, include it as part of the solution)? We might, we might not -- we don't know. A brute 
force way to approach this solution would be to recurse out at each index (one for taking it, one for not taking it).

For example, a recursion stack would look like f(1) => f(6) when we skip index 1 (value 5). Another recursion stack 
would look like f(1) => f(5) => f(6). We're going to recompute f(6)! The overlapping subproblems is quite clear in this.
So knowing this we know we can use dynamic programming to approach the problem. Another keyword that hints us is the nature of
"optimation" in the problem -- the problem asks for the "maximum" value. These "optimzation" keywords (minimum / maximum)
can be a hint (not always!) to use dynamic programming.

A follow-up to this questions asks for solve this in O(nlogn) time. To do this is quite complex, but we need to mix
binary search in our DP array. A technique we use in this is called patience sorting.

*/

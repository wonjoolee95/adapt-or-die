/*

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1

Leetcode: https://leetcode.com/problems/coin-change/
Difficulty: Medium

*/

class Solution {
    public int coinChange(int[] coins, int amount) {
        // Set up for brute-force approach.
        int min = coinChange(coins, amount, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
        
        // Set up for memoized top-down recursive approach.
        int[] dp = new int[amount + 1];
        return coinChange(coins, amount, dp);
    }
    
    // This is the brute foce approach. We recurse into every possibility
    // and backtrack to the original solution. Time complexity of O(c^n),
    // where c is number of coins and n is the amount. Space complexity of O(1). 
    private int coinChange(int[] coins, int amount, int currAmount) {
        if (amount == currAmount) {
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        int val = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (currAmount + coins[i] <= amount) {
                val = coinChange(coins, amount, currAmount + coins[i]);
            }
            
            if (val != Integer.MAX_VALUE) {
                min = Math.min(min, val + 1);
            }
        }
        
        return min;
    }
    
    // After solving the brute force, we can state the optimal substructure
    // and overlapping subproblems
    
    // Optimal substructure
    // F(n) = the minimum # of coins to add up to n.
    
    // Overlapping subproblems
    // F(n) = min(F(n - c0), ... , F(n - ci)) + 1. 
    
    // This is the memoized top-down recursive approach. We create an dp 
    // array of length amount, where each index represents the substructure, 
    // that is, the minimum number of coins to add up to that index (or amount). 
    // Time complexity of O(c * n), and space complexity of O(n).
    private int coinChange(int[] coins, int amount, int[] dp) {
        if (amount < 0) {
            return -1;
        }
        
        if (amount == 0) {
            return 0;
        }
        
        if (dp[amount] != 0) {
            return dp[amount];
        }
        
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int val = coinChange(coins, amount - coin, dp);
            if (val != -1) {
                min = Math.min(min, val + 1);
            }
        }
        
        dp[amount] = min == Integer.MAX_VALUE ? -1 : min;
        return dp[amount];
    }
    
    // This is the bottom-up iterative approach. We traverse through each
    // optimal substructure/subproblem and fill up the values iteraitvely.
    // The iterative approach is straight foward -- we get the mininum
    // value at each of the i - coin index and add + 1. Runtime complexity
    // of O(c * n) and space complexity of O(n).
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
    
}

/*

Time: O(c * n), where c is the number of coins and n is the amount.
Space: O(n), where n is the amount.

This is a very classic dynamic-programming question. This is my favorite introduction
question to dynamic programming. It is also very similar to the classic 0-1 knapsack
problem. Explanation is in comments at each method.

*/

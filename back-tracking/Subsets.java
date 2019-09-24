/*

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

Leetcode: https://leetcode.com/problems/subsets/
Difficulty: Medium

*/

class Solution {
    private List<List<Integer>> powerset = new ArrayList<>();
    private List<Integer> subset = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0);
        return powerset;
    }
    
    private void dfs(int[] nums, int index) {
        powerset.add(new ArrayList<>(subset));
        for (int i = index; i < nums.length; i++) {
            subset.add(nums[i]);
            dfs(nums, i + 1);
            subset.remove(subset.size() - 1);
        }
    }
}

/*

Time: O(n * 2^n), where n is the length of the nums list. We know that in a list with n elements, there are 2^n subsets.
We may not have known this before, but we should know it now! It's hard to prove, but for a small problem like this,
we can just have some plug-in & test trick to prove our 2^n time. Now at each step, we are copying the ArrayList so
additional factor of n is multiplied.
Space: O(n * 2^n), same as above.

A classic backtracking DFS problem! In backtracking algorithms, we DFS into a path (and possibly terminate early if we
decide the path is not correct) and when we come out of the recursion, we "backtrack" -- often means removing certain 
items that we have added. 

Look at this amazing post from Leetcode to read more about how backtracking algorithms are used -- https://tinyurl.com/yasaohmk.

*/

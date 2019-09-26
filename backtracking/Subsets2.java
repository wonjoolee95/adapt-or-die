/*

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

Leetcode: https://leetcode.com/problems/subsets-ii/
Difficulty: Medium

*/

class Solution {
    List<List<Integer>> powersets = new ArrayList<>();
    List<Integer> subsets = new ArrayList<>();
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, 0);
        return powersets;
    }
    
    private void dfs(int[] nums, int index) {
        powersets.add(new ArrayList<>(subsets));
        for (int i = index; i < nums.length; i++) {
            if (i > 0 && i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            subsets.add(nums[i]);
            dfs(nums, i + 1);
            subsets.remove(subsets.size() - 1);
        }
    }
}

/*

Time: O(n * 2^n). Array copying + powersets.
Space: O(2^n).

Same as the first powerset, except checking for duplicate. This duplicate check is common in these problems,
get familiar with them!

*/

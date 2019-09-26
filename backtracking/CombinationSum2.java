/*

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]

Leetcode: https://leetcode.com/problems/combination-sum-ii/
Difficulty: Medium

*/

class Solution {
    private List<List<Integer>> combinations = new ArrayList<>();
    private List<Integer> combination = new ArrayList<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return combinations;
    }
    
    private void dfs(int[] candidates, int target, int index) {
        if (target == 0) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        
        for (int i = index; i < candidates.length; i++) {
            if ((i > index) && (candidates[i] == candidates[i - 1])) {
                continue;
            }
            if ((target - candidates[i]) >= 0) {
                combination.add(candidates[i]);
                dfs(candidates, target - candidates[i], i + 1);
                combination.remove(combination.size() - 1);
            }
        }
    }
}

/*

Time: O(n * 2^n). For list copying + combination. 
Space: O(2^n).

To ensure we're not using duplicates, we have this line:
if ((i > index) && (candidates[i] == candidates[i - 1]))

Think about why we have this line. We don't want to always skip if candidates[i] == candidates[i - 1]. 
Imagine candidates [1, 1, 2] with target = 2. Combination [1, 1] is still valid, but at index 1 
candidates[1] == candidates[1 - 1]. We still want to look at this! So just skipping when 
candidates[i] == candidates[i - 1] wouldn't work. 

*/

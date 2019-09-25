/*

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

*/

class Solution {
    List<List<Integer>> combinations = new ArrayList<>();
    List<Integer> combination = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0);
        return combinations;
    }
    
    private void dfs(int[] candidates, int target, int index) {
        if (target == 0) {
            combinations.add(new ArrayList<>(combination));
        }
        
        for (int i = index; i < candidates.length; i++) {
            if ((target - candidates[i]) >= 0) {
                combination.add(candidates[i]);
                dfs(candidates, target - candidates[i], i);
                combination.remove(combination.size() - 1);
            }
        }
    }
}

/*

Time: O(n * 2^n). O(n) for creating a copy list (this is Java specific, because Java passes variables by reference).
We know the DFS recursion is O(2^n) because we know that 2^n combinations exists for a number n.
Space: O(2^n). 

Another classical backtracking problem. As with any backtracking problems, he key point is removing (or bactracking)
the value when we come out of the recursion. Also with many backtracking problems, the runtime complexity is 
definitely exponential (often times O(2^n) because 2^n combinations/powersets exists for n. 

*/

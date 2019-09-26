Backtracking is an extremely common alogrithm/skill used in various interview questions. I've been asked many backtracking questions in real interivews (Google onsite -- number blocks). Backtracking is used when we need to cover all possible cases (no Dynamic Programming here) and we know that we can exit out early of a recursion if the path isn't correct. Essentially, backtracking is a DFS algorithm without early termination. Note that when we come out of a recursive call, we need to "undo" what we did for that DFS branch (usually removing the last element from a List). 

Often in combination or powerset related problems, a variation would require that no duplicate is used. To do this duplicate check, we don't want to start another DFS branch if have already visited that recursive tree with the same current combination/powerset list. 

Examples where duplicates are allowed:
- [https://github.com/wonjoolee95/interview-questions/blob/master/backtracking/Subsets.java](Subsets.java)
- [https://github.com/wonjoolee95/interview-questions/blob/master/backtracking/CombinationSum.java](CombinationSum.java)

Examples where duplicates are NOT allowed:
- [https://github.com/wonjoolee95/interview-questions/blob/master/backtracking/Subsets2.java](Subsets2.java)
- [https://github.com/wonjoolee95/interview-questions/blob/master/backtracking/CombinationSum2.java](CombinationSum2.java)

Take a look at [this amazing Leetcode discuss post about backgracking](https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)). 

/*

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

Definition for a binary tree node:

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

*/

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> solution = new ArrayList<>();
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        
        while (!curr.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            List<Integer> currValues = new ArrayList<>();
            
            for (TreeNode node : curr) {
                currValues.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            
            solution.add(currValues);
            curr = next;
        }
        
        return solution;
        
    }
}

/*

Time: O(n).
Space: O(n).

*/

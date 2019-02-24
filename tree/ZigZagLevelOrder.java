/*

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> solution = new ArrayList<>();
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        boolean isLeftToRight = true;
        
        while (!curr.isEmpty()) {
            List<TreeNode> next = new ArrayList<>();
            LinkedList<Integer> currValues = new LinkedList<>();
            
            for (TreeNode node : curr) {
                if (isLeftToRight) {
                    currValues.add(node.val);
                } else {
                    currValues.addFirst(node.val);
                }
                
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            
            solution.add(currValues);
            curr = next;
            isLeftToRight = !isLeftToRight;
        }
        
        return solution;
    }
}

/*

Time: O(n).
Space: O(n).

We do a BFS, while maintaining two levels -- curr and next. The trick here is to use a LinkedList for currValues
and add value of each node to the head of the LinkedList when isLeftToRight and to the end otherwise. 

Another trick here is to see that we don't tree to do the *actual traversal* in a zig zag way. We can just
always do a normal left to right traversal. Only when we are adding to the values to currValues we need
to output in a zigzag way. And this can be done through adding the value to first or last to the LinkedList.
We can see that "reversing" is same as adding values continuously to the head.

*/


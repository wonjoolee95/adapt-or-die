/*

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
    2
   / \
  1   3
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
             is 5 but its right child's value is 4.



Definition for a binary tree node:

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

*/
 
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        
        if (root.val <= min || root.val >= max) {
            return false;
        }
        
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}

/*

Time: O(n).
Space: O(n), due to recursive call stack. Or better, O(h) because our recursion depth only goes up to O(h)
where h is the height of the tree. Note that O(n) can be same as O(h), since the height of the tree can be
same as n (in case of a really unbalanced, linked-list like looking tree).

We do a pre-order traversal, maining a min and max value. Everytime we go the left subtree, we update the max.
And everytime we go to the right subtree, we update the min. The main trick to this problem is realizing which
traversal to use. To validate if a tree is BST, we need to perform the validation at each subtree comparing 
the parent to its children. Again, at each subtree, we want to look at the parent then move onto its children.
This is definition of pre-order traversal.

*/

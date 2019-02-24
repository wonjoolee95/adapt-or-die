/*

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.

Definition for a binary tree node:

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

*/
 
class IsBalanced {
    public boolean isBalanced(TreeNode root) {
        return isBalancedUtil(root) >= 0;
    }
    
    private int isBalancedUtil(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftHeight = isBalancedUtil(root.left);
        int rightHeight = isBalancedUtil(root.right);
        
        if (leftHeight < 0 || rightHeight < 0 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
        
    }
}

/*

Time: O(n).
Space: O(n), due to recursive call stack.

We do a post-order traversal, adding +1 as we bubble up each time. When the height difference is > 1 
or we have a -1, we bubble up -1. On another implementation, we can create a custom Solution object 
that holds boolean value and pass that value to the util method.

*/

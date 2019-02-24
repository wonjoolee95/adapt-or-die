/*

Invert a binary tree.

Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9

Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1


Definition for a binary tree node:

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

 */
 
class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode node = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(node);
        
        return root;
    }
}

/*

Time: O(n).
Space: O(n), due to recursive call stack.

We just a preorder traversal, setting root.left to root.right and root.right to root.left, using a temporary variable. Algorithm is straightforward, but seeing the return value bubble up may not be too obvious. On another implementation (to make the return value easier), we can make a util method that does not return anything but simply changes root.left with root.right.

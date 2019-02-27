/*

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.


Definition for a binary tree node.
public class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;
   TreeNode(int x) { val = x; }
}

*/

class Solution {
    class Helper {
        int counter;
        int val;
    }
    
    public int kthSmallest(TreeNode root, int k) {
        Helper helper = new Helper();
        inOrderTraversal(root, k, helper);
        return helper.val;
    }
    
    private void inOrderTraversal(TreeNode root, int k, Helper helper) {
        if (root == null) {
            return;
        }
        
        inOrderTraversal(root.left, k, helper);
        
        helper.counter++;
        if (helper.counter == k) {
            helper.val = root.val;
        }
        
        inOrderTraversal(root.right, k, helper);
        
    }
}

/*

Time: O(n).
Space: O(n), due to recursive call stack. 

Another classic pre-order traversal question. We make this code cleaner by using a Helper class. 

*/

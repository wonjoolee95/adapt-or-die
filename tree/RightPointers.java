/*

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Example:

       1                          1 -> null 
   2       3          =>      2   ->  3 -> null
4    5   6   7             4 -> 5 -> 6 -> 7 -> null

Difficulty: Medium
Leetcode: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

*/

class Solution {
    public Node connect(Node root) {
        Node solution = root;
        if (root == null) {
            return root;
        }
        
        while (root.left != null) {
            Node curr = root;
            while (curr != null) {
                curr.left.next= curr.right;
                curr.right.next = curr.next == null ? null : curr.next.left;
                curr = curr.next;
            }
            root = root.left;
        }
        
        return solution;
    }
}

/*

Time: O(n), where is n is the number of nodes in the tree.
Space: O(1).

This is a really great tree question! Review it more!

First off, the O(n) space solution is trivial. We can do a simple level order traversal and set the right pointers. The real 
question is how can solve this is O(1) space?

At any node, it's obvious that we have to set node.left.next = node.right. The tricky part of how do we set the next pointer of
node.right (node.right.next belongs to the right subtree!). We do this by using the next pointer that we've already built previously.
Since we have alredy built the next pointer of the node's parent, we can do node.right.next = node.next.left.

This logic of using what we have previously built in common in all questions, but definitely way more prevalent in trees. 
Remember the "inverse a binary tree" question? In this question, we say root.left = root.right, and root.right = root.left (we
actually need to hold the root.left in a temp pointer). And then we can recursively have this piece of code (swapping root.left 
with root.right), because the parents of root.left and root.right has already been swapped! 

*/

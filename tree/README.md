Many tree questions become very simple if you know how to correctly traverse through it. There are 3 type of tree traversals: pre-order, in-order, and post-order.

Pre-order traversal is when you visit the root, and then its children -- hence the name "pre-order". Some common questions that can easily be solved with pre-order traversal include [Validate tree is BST](https://github.com/wonjoolee95/adapt-or-die/blob/master/tree/IsValidBST.java), [Find Kth smallest element in BST](https://github.com/wonjoolee95/adapt-or-die/blob/master/tree/KthSmallest.java). The code for pre-order will always look similar to this:
```
private void preOrder(Node root) {
  if (root == null) {
    return;
  }
  
  // Visit the node (main logic)
  visit(root);
  
  preOrder(root.left);
  preOrder(root.right);
}
```

In-order traversal is when you visit the left child, the root, and then the right child -- hence the name "in-order". The code for in-order will always look similar to this:
```
private void inOrder(Node root) {
  if (root == null) {
    return;
  }
  
  inOrder(root.left);
  
  // Visit the node (main logic)
  visit(root);
  
  inOrder(root.right);
}
```

Post-order traversal is when you visit the children and then the root -- hence the name "post-order". Some common questions that can easily be solved with post-order traversal include [Determine if tree is balanced](https://github.com/wonjoolee95/adapt-or-die/blob/master/tree/IsBalanced.java), Find lowest common ancestors. The code for post-order will always look similar to this:
```
private void postOrder(Node root) {
  if (root == null) {
    return;
  }
  
  postOrder(root.left); 
  postOrder(root.right);
  
  // Visit the node (main logic)
  visit(root);
}
```

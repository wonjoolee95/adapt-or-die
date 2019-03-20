/*

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Leetcode: https://leetcode.com/problems/min-stack/
Difficulty: Easy

*/

class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }
    
    public void pop() {
        int popped = stack.pop();
        if (popped == minStack.peek()) {
            minStack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/*

Time: O(1) for all operations.
Space: O(n).

There are many ways to implement this. In this solution, we keep two stacks -- a regular stack and a min stack.
We push to the min stack only if the new number is a new min. We pop from from the min stack only if the popped
number is the current min.

The trick to solving data structure problems is doing concrete examples and drawing some of these cases out on 
a paper or on the board.

*/

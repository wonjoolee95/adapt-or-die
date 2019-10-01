/*

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

Leetcode: https://leetcode.com/problems/evaluate-reverse-polish-notation/
Difficulty: Medium

*/

class Solution {
    public int evalRPN(String[] tokens) {
        Integer solution = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                String operator = token;
                Integer second = stack.pop();
                Integer first = stack.pop();
                Integer result = 0;
                if (token.equals("+")) {
                    result = first + second;
                } else if (token.equals("-")) {
                    result = first - second;
                } else if (token.equals("*")) {
                    result = first * second;
                } else if (token.equals("/")) {
                    result = first / second;
                }
                stack.push(result);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        
        return stack.isEmpty() ? 0 : stack.pop();
    }
}

/*

Time: O(n), where n is the length of the given tokens.
Space: O(n).

Another classic stack problem. Very similar to the basic calculator and decoding strings problem. Remember, when an
order of operation needs to be kept in an inner-to-out order, think stack!

*/

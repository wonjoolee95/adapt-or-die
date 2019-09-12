/*

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

Leetcode: https://leetcode.com/problems/decode-string/
Difficulty: Medium

*/

class Solution {
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        Integer digit = 0;
        for (Character c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                digit = digit * 10 + (c - '0');
            } else if (Character.isLetter(c)) {
                curr.append(c);
            } else if (c == '[') {
                numStack.push(digit);
                strStack.push(curr);
                digit = 0;
                curr = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = curr;
                curr = strStack.pop();
                Integer popped = numStack.pop();
                for (int i = 0; i < popped; i++) {
                    curr.append(temp);
                }
            }
        }
        
        return curr.toString();
    }
}

/*

Time: O(n).
Space: O(n).

The detailed logic especially why we keep a separate StringBuilder curr is kind of complex. But what's not complex is 
the fact that we're using a Stack. In these type of problems where we keep a "order of operation" think STACK!
Some similar problems implementing basic calculator, parsing string with backspace character, etc.

Think about the logic when we see ']'. 

*/

/*

Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.

Example 1:
Input: "()"
Output: True

Example 2:
Input: "(*)"
Output: True

Example 3:
Input: "(*))"
Output: True

Leetcode: https://leetcode.com/problems/valid-parenthesis-string/
Difficulty: Medium

*/

class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int max = 0;
        
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                low++;
                max++;
            } else if (c == ')') {
                low = Math.max(0, low - 1);
                max--;
                if (max < 0) return false;
            } else {
                low = Math.max(0, low - 1);
                max++;
            }
        }
        
        return low == 0;
    }
}

*/

Time: O(n), where n is the number is characters in the string.
Space: O(n).

This is a really really interesting one. First, we need to see that a brute force approach would be the recursive substitute
each possible value whenever we see a '*'. Then when we draw the recursion tree, we can see that we really are only keeping
track of a "range" of parenthesis. To be exact, we are keeping track of how many ")", ie. closed parentheses, we are expecting.

Take a look at "(*)(*))". Let n represent a range of numbers of closed parentheses ')' we are expecting.
At i = 0, we see '('. So we increment n. Now n = [1].
At i = 1, we see '*'. This means we can either open a parenthesis, close a parenthesis, or do nothing. This means n = [0, 1, 2].
At i = 2, we see '('. So we decrement n. Now n = [-1, 0, 1]. Note that we can ignore the negative value, because this just
means that we cannot use the previous '*' we've seen as a close parenthesis. So n = [0, 1]. Note again, this means that at this point,
we are expecting 0 or 1 closed parenthesis. 
At i = 3, we see '*'. Now n = [0, 1, 2].
At i = 4, we see ')'. So we decrement. Now n = [0, 1].
At i = 5, we see ')'. So we decrement. Now n = [0].

If n[0] = 0, we know there is such a way to form a valid parentheses.

To solve problems like this, just doing some examples by hand really helps. Solve some examples by hand and observe the pattern. 
And can we make that logic/pattern into code?

*/

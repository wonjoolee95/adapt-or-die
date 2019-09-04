/*

Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True

Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.

Leetcode: https://leetcode.com/problems/valid-palindrome-ii/
Difficulty: Easy

*/

class Solution {
    public boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return isPalindrome(s, start + 1, end) || isPalindrome(s, start, end - 1);
            }
            
            start++;
            end--;
        }
        
        return true;
    }
    
    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            
            start++;
            end--;
        }
        
        return true;
    }
}

/*

Time O(n).
Space: O(1).

When I first solved the problem, I made it over-complicated by thinking: if s[start] != s[end], I have 2 options: 
1. go into subproblem with s[start - 1] and s[end]
2. go into subproblem witn s[start] and s[end - 1]
I could do this recursively, going into 2 branches at each depth. 

This thinking is way over-complicating the question. The question specifies that at most *one character* can be removed.
With this constraint, the problem becomes really simple. When s[start] != s[end], we just need to check if the rest
of the string is a palindrome for two strings: s[start + 1], s[end] and s[start] and s[end - 1]. And since we're
at max looping throught the entire string twice, the time complexity is O(n).

*/

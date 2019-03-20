/*

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

Leetcode: https://leetcode.com/problems/palindromic-substrings/
Difficulty: Medium

*/

class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            count += extendSubstring(s, i, i);
            count += extendSubstring(s, i - 1, i);
        }
        
        return count;
        
    }
    
    private int extendSubstring(String s, int left, int right) {
        int count = 0;
        
        while (left >= 0 && right < s.length() && (s.charAt(left) == s.charAt(right))) {
            count++;
            left--;
            right++;
        }
        
        return count;
    }
}

/*

Time: O(n^2). The extendSubstring method is executed O(n) times and runs in O(n) time.
Space: O(1).

The brute force solution would go through every substring and check if it is a palindrome. 
To go through every substring, we would need a O(n^2) loop, and to check if that substring
is a palindrome, we would need another O(n) time. This brute force solution runs in O(n^3) time.

The bottle neck of this brute force is due to validating palindrome for *every substring*. We can
improve this by trying to "extend" the palindrome. At each index of the string, we can try to extend the
substring by increasing left and right pointer. Note that we have to take into the even palindrome into 
account (ex. "aabb"), by calling the same method with (i - 1). 

In the optimized solution, the extendSubstring method is executed O(n) times and runs in O(n) time. 
Therefore, the run time complexity improves to O(n^2). The main trick to solving this problem is realizing
how we can utilize the nature of a palindrome to extend. 

*/

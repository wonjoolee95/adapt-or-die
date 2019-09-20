/*

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Leetcode: https://leetcode.com/problems/minimum-window-substring/
Difficulty: Hard

*/

class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        int start = 0;
        int end = 0;
        int count = t.length();
        
        int shortest = Integer.MAX_VALUE;
        int shortestStart = 0;
        int shortestEnd = 0;
        
        while (end < s.length()) {
            Character rightChar = s.charAt(end);
            
            if (map.containsKey(rightChar)) {
                map.put(rightChar, map.get(rightChar) - 1);
                if (map.get(rightChar) >= 0) {
                    count--;
                }
            }
            
            while (count == 0) {
                int tempShortest = end - start;
                if (tempShortest < shortest) {
                    shortestStart = start;
                    shortestEnd = end + 1;
                    shortest = tempShortest;
                }
                
                Character leftChar = s.charAt(start);
                if (map.containsKey(leftChar)) {
                    map.put(leftChar, map.get(leftChar) + 1);
                    if (map.get(leftChar) > 0) {
                        count++;
                    }
                }
                
                start++;
            }
            
            end++;
        }
        
        return s.substring(shortestStart, shortestEnd);
    }
}

/*

Time: O(n), where n is the length of s. We are, at worse case, travering the the entire string twice.
Space: O(m), where m is the length of t.

Classic classic classic sliding window question! In sliding window, we always have two pointers -- start and end. 
We gradually increase the end until our condition is met (in this case, the condition is the subtring containing 
string t). And then we gradually decrease the start pointer while the condition is still being met. Then at the end, 
we have the minimum substring! The key point is how we are calculing the condition -- in this problem, the count. 

*/

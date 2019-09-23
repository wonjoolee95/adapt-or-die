/*

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

Leetcode: https://leetcode.com/problems/find-all-anagrams-in-a-string/
Difficulty: Medium

*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        List<Integer> solution = new ArrayList<>();
        int left = 0;
        int right = 0;
        int count = p.length();
        
        while (right < s.length()) {
            Character rightChar = s.charAt(right);
            if (map.containsKey(rightChar)) {
                map.put(rightChar, map.get(rightChar) - 1);
                if (map.get(rightChar) >= 0) {
                    count--;
                }
            }
            
            if (count == 0) {
                solution.add(left);
            }
            
            if ((right - left) == (p.length() - 1)) {
                Character leftChar = s.charAt(left);
                if (map.containsKey(leftChar)) {
                    map.put(leftChar, map.get(leftChar) + 1);
                    if (map.get(leftChar) > 0) {
                        count++;
                    }
                }
                left++;
            }
            
            right++;
        }
        
        return solution;
    }
}

/*

Time: O(n), where n is the length of the string. We, at most, traverse the string twice. So O(2n) = O(n). 
Space: O(n).

A variation of the classic sliding window on strings. Here we still have a sliding window, but only up to 
length p.length. This is because we are looking for anagrams, so we only want a window up to length p.length.
Then when count == 0, we know we have found an anagram then add it onto the string.

The key point here is noticing that we need to use the sliding window algorithm and realizing the contraint
of the window being its length at most being p.length.

*/

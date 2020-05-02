/*



Leetcode: 
Difficulty: Medium

*/

class Solution {
    public int longestStrChain(String[] words) {
        Map<String, Integer> seen = new HashMap<>();
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        
        int longest = 0;
        
        for (String word : words) {
            int chainLength = 1;
            for (int i = 0; i < word.length(); i++) {
                String parent = word.substring(0, i) + word.substring(i + 1, word.length());
                chainLength = Math.max(chainLength, seen.getOrDefault(parent, 0) + 1);
            }
            seen.put(word, chainLength);
            longest = Math.max(longest, chainLength);
        }
        
        return longest;
    }
}

/*

Time: O(nlogn + n*m^2), where n is the number of words in the list and m is the total length of all the words.
Space: O(n), where n is the number of words in the list.

We need to think intuitively - how is a "chain" created? Take this list for example ["a", "ab, "abc", "bc"].
We start at "a" with chain length 1. Then we're at "ab". We've already seen "a" with chain length 1. So we know "ab" 
has chain length of 2. Then we're at "abc". We've already seen "ab" with chain length 2. So we know "abc" has
chain length of 3.

*/

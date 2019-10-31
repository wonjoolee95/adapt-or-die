/*

Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

Leetcode: https://leetcode.com/problems/word-ladder/
Difficulty; Medium

*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        LinkedList<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> words = new HashSet<>(wordList);
        int count = 1;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                String polledWord = queue.poll();
                if (polledWord.equals(endWord)) {
                    return count;
                }

                for (int j = 0; j < polledWord.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char[] charArray = polledWord.toCharArray();
                        charArray[j] = c;
                        String newWord = new String(charArray);
                        if (words.contains(newWord)) {
                            queue.offer(newWord);
                            words.remove(newWord);
                        }
                    }
                }
            }
            count++;
        }
        
        return 0;
    }
}

/*

Time: O(n * s), where n is length of wordList and s is length of the beginWord.
Space: O(n * s), where n is length of wordList and s is length of the beginWord.

It's quite clear that we can view this problem as a graph. Each word is a node (with beginWord as the root) and there's
an edge between two words if they're one edit away. 

We're looking for shorest path, and we can see we can take a greedy approach. And since we know we cannot revisit the same
word again, this is just a really trivial BFS algorithm. The only trick is to see how we implement our logic to check
if two words are one edit away. Initially, I had a function to loop through our wordList and see if the word is one edit away
from the polledWord. However, it is more efficient to simply just iterate through all alphabet and replace each index
of the polledWord to see if that new word exists in our wordSet. Remember this is a handy trick to bring down the complexity
when working speicifically with English alphabets!

*/

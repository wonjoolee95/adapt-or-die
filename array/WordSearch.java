/*

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

*/

class Solution {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (exist(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean exist(char[][]board, String word, int i, int j, int k) {
        if (k == word.length()) {
            return true;
        }
        
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        
        boolean exists = false;
        if (board[i][j] == word.charAt(k)) {
            char c = board[i][j];
            board[i][j] = '#';
            exists = exist(board, word, i - 1, j, k + 1) ||
                exist(board, word, i, j + 1, k + 1) ||
                exist(board, word, i + 1, j, k + 1) ||
                exist(board, word, i, j - 1, k + 1);
            board[i][j] = c;
        }
        
        return exists;
    }
}

/* 

Time: O(nm * 4^k), where n and m are rows and columns of the board and k is length of the seach word.
The outer loop of iterating through each index of the board is clearly O(nm). Now, in each of this iteration,
we do a DFS that branches out 4 times, with each branch going upto the length of the seach word. We can see
that the DFS algorithm is O(4^k). Therefore, we can see the total runtime is O(nm * 4^k).
Space: O(nm), due to recursive call stack.

If we see the trick to view the board as a graph, this problem is really easy. This is common technique/trick in 
many matrix problems. Another popular similar questions is Islands of 1s.

*/

/*

You start at index 0 in an array with length 'h'. At each step, you can move to the left, move to the right, or stay in 
the same place(Note! Stay in the same place also takes one step). How many possible ways are you still at index 0 after 
you have walked 'n' step?

Example： n = 3, output = 4.
Exaplanation:
1. right->left->stay
2. right->stay->left
3. stay->right->left
4. stay->stay->stay

*/

class Solution {

	/*
	 * This is the brute force recursive appoach. At each index, we take every possible path.
	 * That is -- left, stay, and right. If we have 0 moves left and are at index 0, we know
	 * we have found a path. 
	 * 
	 * Time: O(3^n), where n is the number of moves, as we branch out to 3 branches up to n.
	 * Space: O(n), as recursive call stack goes down to n.
	 */
    public int waysToReturn(int length, int moves) {
    	return helper(length, moves, 0);
    }
    
    private int helper(int length, int moves, int index) {
        if (moves == 0 && index == 0) {
            return 1;
        } else if (moves == 0) {
            return 0;
        } else if (index == length || index < 0) {
        	return 0;
        }
        
        int left = helper(length, moves - 1, index - 1);
        int stay = helper(length, moves - 1, index);
        int right = helper(length, moves - 1, index + 1);

        int ways = left + stay + right;
        return ways;
    }

	/*
	 * This is memoized top-down dynamic programming approach. We keep a Map<Integer, Map<Integer, Integer>>,
	 * mapping {moves: {index: waysToReturn}}. For each move, if at that index, we have calculated the
	 * ways to return, we store it in our memo. 
     *
	 * Time: O(n^2), where n is the number of moves. Time complexity of these top-down memozied recursive
	 * algorithms are tricky to figure out. In these algorithms, instead of looking at the code, look at 
	 * the bigger algorithmic picture. In our code, we are looking at every path once, since we use memo.
	 * At each recursive stack, we move or stay at a spot. That means at
	 * each index, we'll recursing down to n - 1 paths. This is similar to a typical O(n^2) double-nested
	 * algorithm. 
	 * Space: O(n^2), as we are storing ways for every path. 
	 */
    public int waysToReturn(int length, int moves) {
    	Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();
    	return helper(length, moves, 0);
    }
    
    private int helper(int length, int moves, int index, Map<Integer, Map<Integer, Integer>> memo) {
		if (memo.containsKey(moves) && memo.get(moves).containsKey(index)) {
			return memo.get(moves).get(index);
		}

        if (moves == 0 && index == 0) {
            return 1;
        } else if (moves == 0) {
            return 0;
        } else if (index == length || index < 0) {
        	return 0;
        }
        
        int left = helper(length, moves - 1, index - 1);
        int stay = helper(length, moves - 1, index);
        int right = helper(length, moves - 1, index + 1);

        int ways = left + stay + right;

        if (!memo.containsKey(moves)) {
        	memo.put(moves, new HashMap<>());
        }

        memo.get(moves).put(index, ways);

        return ways;
    }
    
  /*
   * This is bottom-up dynamic programming approach. Transforming the recursive brute force approach
   * to the top-down memoized recursive approach is straight forward. Transforming the top-down 
   * memozied approach to bottom-up dynamic programming may be tricky sometimes. When this is tricky,
   * define a clear recurrence function. 
   *
   * Recurrence
   * Let T(m, i) be the n umber of ways to reach index 0 with m moves at index i.
   * T(m, i) = T(m - 1, i - 1) + T(m - 1, i) + T(m - 1, i + 1).
   *
   * We define a dp[moves + 1][length]. The rows indicate moves and columns indicate the index.
   * Each index dp[i][j] represents the number of ways to reach index 0 with i moves at index j.
   *
   * Time: O(n^2), where n is the number of moves. 
   * Space: O(n^2).
   */
    public int waysToReturn(int length, int moves) { 
    	// Recurrence
    	// Let T(m, i) be the number of ways to reach index 0 with m moves at index i.
    	// T(m, i) = T(m - 1, i - 1) + T(m - 1, i) + T(m - 1, i + 1).
    	int[] dp = new int[moves + 1][length];
    	dp[0][0] = 1;

    	for (int i = 1; i <= moves; i++) {
    		for (int j = 0; j < moves; j++) {
    			dp[i][j] += dpi[i - 1][j];
    			if (j - 1 >= 0) dp[i][j] += dp[i - 1][j - 1];
    			if (j + 1 < length) dp[i][j] += dp[i - 1][j + 1];
    		}
    	}

    	return dp[moves][0];
	}
}

/*

Time: O(n^2), where n is the number of moves. 
Space: O(n^2).

Recurrence
Let T(m, i) be the n umber of ways to reach index 0 with m moves at index i.
T(m, i) = T(m - 1, i - 1) + T(m - 1, i) + T(m - 1, i + 1).

*/

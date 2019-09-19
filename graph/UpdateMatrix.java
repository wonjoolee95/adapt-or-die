/*

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

Example 1:
Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
 
Example 2:
Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]
 
Leetcode: https://leetcode.com/problems/01-matrix/
Difficulty: Medium

*/

class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        LinkedList<int[]> queue = new LinkedList<>();
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0];
            int col = point[1];
            
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                
                if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length && matrix[newRow][newCol] > matrix[row][col]) {
                    matrix[newRow][newCol] = matrix[row][col] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
        
        return matrix;
    }
}

/*

Time: O(nm), where n is the number of rows and m is the number of columns.
Space: O(1), since we're modiying the matrix in place.

Again, when you see a matrix representation like this, think of graphs. Graphs are the most obvious data structure
to represent relations, and they should always be on top of your mind! Going back to the question, we're finding the
shortest path so it's pretty clear we need to use BFS.

A brute force approach would be to run BFS on every non-zero element to the nearest 0. This would be a O((nm)^2) time,
because we are doing BFS (which runs in O(nm) time) at each index. So the bottleneck here is that we are running
BFS on every index. We can avoiad this by running BFS on each 0 index and updating its neighbor value.

Whenever running BFS and DFS, it important to set the 'visited' condition. In a real graph, we just keep a visited
set. In this case (where we are just "visualizing" the matrix as a graph) we can treat a neighboring index as visited
if it has lower value than the parent index.

*/

/*

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:
Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2

Example 2:
Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Leetcode: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
Difficulty: Medium


*/


class Solution {
    public int countComponents(int n, int[][] edges) {
        private int count = 0;
        boolean[] visited = new boolean[n];
        boolean[][] adjacencyMatrix = new boolean[n][n];
        
        for (int[] edge : edges) {
            adjacencyMatrix[edge[0]][edge[1]] = true;
            adjacencyMatrix[edge[1]][edge[0]] = true; 
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, adjacencyMatrix, visited);
                count++;
            }
        }
    
        return count;    
    }
    
    private void dfs(int node, boolean[][] adjacencyMatrix, boolean[] visited) {
        visited[node] = true;
        for (int i = 0; i < adjacencyMatrix[node].length; i++) {
            if (!visited[i] && adjacencyMatrix[node][i]) {
                dfs(i, adjacencyMatrix, visited);
            } 
        }
    }
}

/*

Time: O(n + m), where n is number of nodes and m is number of edges.
Space: O(n + m), same as above.

This is a classic graph question, using adjacency list. Think about when you'd use adjacency list vs adjancey matrix.
In most graph questions, adjacency list is better as it only holds necessary data. The only trick to this question is 
identifying that you need to add edges u -> v AND v -> u. Then we can just treat this as a simple DFS on a tree.

To get more advanced, we can use Union Find data structure. This data strucutre is surprisingly very useful (especially in
Google) in interviews, so get familiar with it.

*/

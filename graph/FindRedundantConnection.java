/*

In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3

Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

Leetcode: 
Difficulty: Medium

*/

class DisjointSet {
    private int[] parent;
    private int[] rank;
    
    public DisjointSet(int n) {
        this.parent = new int[n + 1];
        this.rank = new int[n + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return parent[x];
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootX] = rootY;
            rank[rootY]++;
        }
    }
    
}

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] solution = new int[2];
        DisjointSet set = new DisjointSet(edges.length);
        
        for (int[] edge : edges) {
            int rootX = set.find(edge[0]);
            int rootY = set.find(edge[1]);
            if (rootX == rootY) {
                solution = edge;
            }
            
            set.union(edge[0], edge[1]);
        }
        
        return solution;
    }
}

/*

Time: Between O(n) and O(logn). To be spcific, O(n * f(n)), where f is the the reverse Ackermann function. This is close to constant time.
Space: O(n).

This is a classic disjoint set (or UnionFind) data structure question. At finding a new edge, we ask: does this edge form a cycle -- ie,
is this adding an edge within the same union (circle)? We know DisjointSet data structure can easily solve this problem.

Naive disjoint set supports O(n) find/union operation. Union by rank supports O(logn) find/union. Union by rank AND path compression
supports O(f(n)) find/union, where f is the inverse Ackermann fucntion (nearly constant). 

*/

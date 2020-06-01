/*

There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
  
Leetcode: https://leetcode.com/problems/course-schedule/
Difficulty: Medium
  
*/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] course : prerequisites) {
            graph.get(course[1]).add(course[0]);
        }
        
        boolean[] visited = new boolean[numCourses];
        boolean[] visiting = new boolean[numCourses];
        
        for (int i = 0; i < graph.size(); i++) {
            if (hasCycle(i, graph, visited, visiting)) {
                return false;
            }
        }
        
        return true;
        
    }
    
    // DFS
    private boolean hasCycle(int node, List<List<Integer>> graph, boolean[] visited, boolean[] visiting) {
        if (visiting[node]) {
            return true;
        }
        
        if (visited[node]) {
            return false;
        }
        
        visiting[node] = true;
        for (int neighbor : graph.get(node)) {
            if (hasCycle(neighbor, graph, visited, visiting)) {
                return true;
            }
        }
        
        visited[node] = true;
        visiting[node] = false;
        
        return false;
    }
}

/*

Time: O(V + E), where V is number of nodes (verticies) and E is the number of edges.
Space: O(V + E).

First, we need to make sense of the question (like always). Draw some examples and we can quickly see that if there is a 
cycle, the courses cannot be finished. This is a typical cycle finding (or topological sorting) question.

*/

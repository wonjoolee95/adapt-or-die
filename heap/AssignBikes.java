/*

On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.

Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index; if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until there are no available workers.

The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.

Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.

 

Example 1:
Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
Output: [1,0]
Explanation: 
Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].

Example 2:
Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
Output: [0,2,1]
Explanation: 
Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
 
*/

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        
        // [dinstance, workerIndex, bikeIndex]
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> {
            if (a[0] > b[0]) return 1;
            else if (a[0] < b[0]) return -1;
            
            if (a[1] > b[1]) return 1;
            else if (a[1] < b[1]) return -1;
            
            if (a[2] > b[2]) return 1;
            else if (a[2] < b[2]) return -1;
            
            return 0;
        });
        
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                Integer manhattenDistance = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                heap.offer(new int[]{manhattenDistance, i, j});
            }
        }
        
        int[] solution = new int[workers.length];
        
        boolean[] workerAssigned = new boolean[workers.length];
        boolean[] bikeAssigned = new boolean[bikes.length];
        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            if (!workerAssigned[info[1]] && !bikeAssigned[info[2]]) {
                solution[info[1]] = info[2];
                workerAssigned[info[1]] = true;
                bikeAssigned[info[2]] = true;
            }
        }
        
        return solution;
    }
}

/*

Time: O(mn * log(mn)). Each offer/poll operation to our heap takes O(log(mn)) time. We do this mn times.
Space: O(mn). 

It's clear that we can use a greedy algorithm. The closest worker-bike pair gets assigned to each other and we don't
care about any pairs in the later stages (greedy much!). After knowning that we can use a greedy approach, now we
need a way to keep the worker-bike pair in a sorted order. We can use a heap with a custom comparator. to maintain 
this order.

*/

/*

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)

Leetcode: https://leetcode.com/problems/k-closest-points-to-origin/
Difficulty: Medium

*/

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]>heap = new PriorityQueue(new Comparator<int[]>() {
            @Override
            public int compare(int[] first, int[] second) {
                double firstDistance = Math.sqrt(Math.pow(first[0], 2) + Math.pow(first[1], 2));
                double secondDistance = Math.sqrt(Math.pow(second[0], 2) + Math.pow(second[1], 2));
                
                int compareValue;
                if (firstDistance == secondDistance) {
                    compareValue = 0;
                } else if (firstDistance < secondDistance) {
                    compareValue = 1;
                } else {
                    compareValue = -1;
                }
                
                return compareValue;
            }
        });
        
        for (int[] point : points) {
            heap.offer(point);
            if (heap.size() > K) {
                heap.poll();
            }
        }
        
        int[][] closestPoints = new int[K][2];
        int i = 0;
        while (heap.size() > 0) {
            closestPoints[i] = heap.poll();
            i++;
        }
        
        return closestPoints;
    }
}

/*

Time: O(nlogk), for each element in points, we are inserting into the heap of height k. 
Space: O(k), our heap will be at maximum size k.

A brute force solution would be to sort the array in ascending order according to their distances from the origin
then return the first k elements. This would run in O(nlogn) time, where n is the number of points given in the input.
While we can make further optimization, this O(nlogn) solution will look very readable and clean. So it actually
may not be a bad solution. 

To optimize our brute-force solution, we need to identify what we want. We need to maintain the minimum k closest points.
What data structure allows us to keep elements in sorted order? Heap! If you can identify that you need to use a heap to
approach this problem, this problem becomes very easy. The only other trick is to use a *max* heap to store k values. If
we have a max heap and keep polling to maintain k values, we know that these k values will the k minimum values.

*/

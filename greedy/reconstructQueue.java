/*

Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), 
where h is the height of the person and k is the number of people in front of this person who have a height greater 
than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

Leetcode: https://leetcode.com/problems/queue-reconstruction-by-height/
Difficulty: Medium

*/

class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) {
            return people;
        }
        
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        
        List<int[]> list = new ArrayList<>();
        for (int[] val : people) {
            list.add(val[1], val);
        }
        
        int[][] solution = new int[people.length][2];
        for (int i = 0; i < list.size(); i++) {
            solution[i] = list.get(i);
        }
        
        return solution;
    }
}

/*

Time: O(n^2), where n is the number of elements in the queue. The O(nlogn) sort is dominated by the O(n^2) insert.
Space: O(n).

The trickiest part of actually solving the problem in the beginning (by hand). We must first notice that this is a greedy
problem. What if we can find the value for the first element, then continue to add the rest of the elements? 

Imagine we start with an empty list = []. Then we add the largest person to their index. If we continue to do this, we can 
reconstruct the queue by height. 

*/

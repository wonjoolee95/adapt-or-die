/*

We are given a list schedule of employees, which represents the working time for each employee.

Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

Example 1:

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]
Explanation:
There are a total of three employees, and all common
free time intervals would be [-inf, 1], [3, 4], [10, inf].
We discard any intervals that contain inf as they aren't finite.
 

Example 2:

Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]
 

(Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)

Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

Leetcode: https://leetcode.com/problems/employee-free-time/
Difficulty: Hard

*/

/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start,int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> solution = new ArrayList<>();
        
        PriorityQueue<Interval> heap = new PriorityQueue<>((a, b) -> a.start - b.start);
        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                heap.offer(interval);
            }
        }
        
        Interval prev = heap.peek();
        while (!heap.isEmpty()) {
            Interval curr = heap.poll();
            
            if (prev.end < curr.start) {
                // there is a free time (non-intersection)
                solution.add(new Interval(prev.end, curr.start));
                prev = curr;
             } else {
                // thhere is no free time (an intersection), so merge the intervals
                prev = prev.end > curr.end ? prev : curr;
            }
        }
        
        return solution;
    }
}

/*

Time: O(n*logn), where n is the number of Internvals in the schedule.
Space: O(n), same as above.

This is just a variable of merging intervals! The only different is that in this case, instead of finding intersections, we are 
finding non-intersections (free-time!). The key point in this problem is noticing that the problem is just merging intervals.
Another key point to realize is that "to whom" the intervals belong to doesn't matter -- we can just flatten the intervals.

And when we're working with merging intervals, we know we need to sort by StartTime -- this should just be automatic. After noticing
these few things, the rest of the logic should be simple. 

*/

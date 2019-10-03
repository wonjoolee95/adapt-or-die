/*

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1

Definition for an interval:
public class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

Leetcode: https://leetcode.com/problems/meeting-rooms-ii/
Difficulty: Medium

*/

class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        
        PriorityQueue<Integer> endTimes = new PriorityQueue<>((a, b) -> a - b);
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        
        endTimes.offer(intervals[0].end);
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start  >= endTimes.peek()) {
                endTimes.poll();
            }
            
            endTimes.offer(intervals[i].end);
        }
        
        return endTimes.size();
    }
}

/*

Time: O(nlogn), since sorting the array takes O(nlogn) time and min-extraction from heap worst case O(nlogn) time.
Space: O(n), since heap can store up to n intervals (more specifically, end times).

Let's think about when a room collision occurs -- when a meeting's start time occurs before another meetings endtime.
We need to see that if the intervals are sorted according to start time, we can simply iterate through the interverals
array and check if intervals[i].start is before (less than) the earliest room-occupying endtime. So aside from our 
initial intervals array input, we need to keep another list -- the room-occupying endtimes. For the explanation below,
we'll refer to this room-occupying endimtes as the endtimes list.

Our main logic is as following
1. Sort the intervals array according to the start time.
2. Iterate through the sorted intervals array.
3. If the intervals[i].start time is after (greater or equal) to the smallest endtime in endtimes list,
we remove that smallest endtime from the endtimes list.
4. We add intervals[i].end time to the endtimes list.
5. Once the iteration completes, we return endtimesList.size().

Since we aways need the endtimes list to be in sorted ascending order, we'll use a min-heap to represent this.

Imagine the intervals [[0, 20], [5, 10], [15, 20]].
In the first iteration [0, 20] we have a room that occupied until 20. In the second iteration [5, 15], we see that
its start time is earlier than the our earliest occupied room's endtime (which is 20), so we need to add this endtime
to our endtimes list.  Now, endtimes list will look like this -- [10, 20]. This means we have a meeting that ends in 
10 and another meeting that ends at 20. And in the last iteration [15, 20], we see that its start time is after 
(greater than) the earliest room-occupying endtime (which is 10). Therefore, we know that we can re-use this room that 
ends at 15. So we remove this 15 from the endtime list, making the endtimes list now -- [20, 20]. After finishing the 
iteration, we can simply return the size of this endtime list, which is 2. This is our answer to the minimum number of 
rooms needed.

The trick to this question is to really understand the question. Understanding what a "room collision" represents and 
identifying when it occurs is fundamental in solving the problem. In addition, identifying that we need the endtimes list
in a sorted order and that we can use a heap to represent this is key.

*/

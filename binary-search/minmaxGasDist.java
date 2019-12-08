/*

On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000

Note:
stations.length will be an integer in range [10, 2000].
stations[i] will be an integer in range [0, 10^8].
K will be an integer in range [1, 10^6].
Answers within 10^-6 of the true value will be accepted as correct.

Leetcode: https://leetcode.com/problems/minimize-max-distance-to-gas-station/
Difficulty: Hard

*/

class Solution {
    public double minmaxGasDist(int[] stations, int K) {
        double left = 0;
        double right = stations[stations.length - 1] - stations[0];
        
        while (right - left >= 10e-6) {
            double mid = (left + right) / 2;
            
            int count = 0;
            for (int i = 0; i < stations.length - 1; i++) {
                count += Math.ceil((stations[i + 1] - stations[i]) / mid) - 1;
            }
            
            if (count > K) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
}

/*

Time: O(nlogm), where n is number of stations and m is furthest distance possible (upper bound).
Space: O(1).

My first approach was a greedy algorithm. I initialized a max heap of int[] node, where node[0] is the distance between each adjacent
stations and node[1] is their divisor (how many stations are between them, by default it'll be 1). Then looping K times, poll the
maximum node and increase its node[1] (this is equivalent to adding another station). At the end, I can return first element in the 
heap as the solution. The runtime of this solution would be O(klogn), which would be very inefficient for a very lage value of K.

We have to note that the answer is bounded by [0, max distance]. More simply, bounded by [0, stations[n - 1] - stations[0]].
Given a guess, we can try to insert N stations whenever necessary (if distance between adjacent stations is more than the guess).
If N > K, then the guess too ambitious - we need to go for a lower guess. If guess N <= K, the guess was too loose. What does this 
look like? Yes, binary search! The time complexity of now O(nlogm), where n is number of stations and m is furthest distance 
possible (upper bound).

If an answer is always bounded, think about binary search.

*/

/*

Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]

Note: Assume k is always valid. The input array will always have k unique most frequent elements.
For example, if nums = [1, 1, 2, 2, 3, 3], k *cannot* be 2 because there are no 2 unique most 
frequent elements. In this example, the only valid k is 3.

Leetcode: https://leetcode.com/problems/top-k-frequent-elements/
Difficulty: Medium

*/

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> solution = new ArrayList<>();
        List<List<Integer>> buckets = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            buckets.add(new ArrayList<>());
        }
        
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            buckets.get(entry.getValue() - 1).add(entry.getKey());
        }
        
        for (int i = buckets.size() - 1; i >= 0 && k > 0; i--) {
            if (buckets.get(i).size() != 0) {
                solution.addAll(buckets.get(i));
                k -= buckets.get(i).size();
            }
        }
        
        return solution;
    }
}

/*

Time: O(n), we have four O(n) operations:
  1. O(n) to initalize the bucket list.
  2. O(n) to create the frequency map.
  3. O(n) to fill the bucket list.
  4. O(n) to fill the solution list.
Space: O(n).

The brute-force approach here would be to sort the frequency map by value and return the top k elements. This approach
would run in O(nlogn) time because we are sorting the frequency map will have n items.  To optimize this a little, 
we want our algorithm's runtime to depend on k not n. To do this, we can use a min heap that stores up to k elements.
With this approach, the algorithm will run in O(nlogk) time. 

To optimize it further, we need to a number in the array can be repeated only up to n times, where n is the length of 
the array. Furthermore, we don't care about the relative order of the result. That is, if the k most frequent elements
if [1, 2, 3], the answer [3, 1, 2] or [1, 3, 2] are also accepted. Knowing this, we'll using the bucket sort algorithm
to solve this question.

We initialize a list of list of length n. We call this 'bucket'. Then we fill out the frequency map, which maps the 
number to its frequency. Then we fill out the bucket list by iterating through the frequency map and doing (for each entry)
bucket[entry.getValue()].add(entry.getKey()]. So each index of the bucket represents frequency, and each list in the index
represents the numbers that occured with that frequency of the index. For example, n = [1, 1, 1, 2, 2, 3, 3, 4], our bucket will
look like: [[4], [2, 3], [1]]. Elements at index 0 appeared 1 time, elements at index 1 appeared 2 times, and elements at index 2
appear 3 times. Then we finally traverse through this bucket backwards, adding the list to the solution if the list.size() is not 0.

The trick to take away here is bucket sort. We can use bucket sort here, because:
1. Each index of the list carries a representation value. In this case, each index represents frequency.
2. We don't care about the relative order of the k top most frequent elements.

*/

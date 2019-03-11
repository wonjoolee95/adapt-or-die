/*

Create a timebased key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)

Stores the key and value, along with the given timestamp.
2. get(string key, int timestamp)

Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
If there are multiple such values, it returns the one with the largest timestamp_prev.
If there are no values, it returns the empty string ("").
 

Example 1:

Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
Output: [null,null,"bar","bar",null,"bar2","bar2"]
Explanation:   
TimeMap kv;   
kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
kv.get("foo", 1);  // output "bar"   
kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
kv.set("foo", "bar2", 4);   
kv.get("foo", 4); // output "bar2"   
kv.get("foo", 5); //output "bar2"   

Example 2:

Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
Output: [null,null,null,"","high","high","low","low"]

Leetcode: https://leetcode.com/problems/time-based-key-value-store/
Difficulty: Medium

*/

class TimeMap {
    Map<String, List<TimeStamp>> map;
    
    class TimeStamp {
        int time;
        String val;
        public TimeStamp(int time, String val) {
            this.time = time;
            this.val = val;
        }
    }
    
    public TimeMap() {
        this.map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(new TimeStamp(timestamp, value));
    }
    
    public String get(String key, int timestamp) {
        return map.containsKey(key) ? binarySearch(map.get(key), timestamp) : "";
    }
    
    private String binarySearch(List<TimeStamp> timestamps, int target) {
        int left = 0;
        int right = timestamps.size() - 1;
        
        while (right > left + 1) {
            int mid = (left + right) / 2;
            
            if (timestamps.get(mid).time == target) {
                return timestamps.get(mid).val;
            } else if (timestamps.get(mid).time > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (target < timestamps.get(left).time) {
            return "";
        }
        
        return target >= timestamps.get(right).time ? timestamps.get(right).val : timestamps.get(left).val;
    }
}

/*

Time: 
  set - O(1).
  get - O(log n).
Space: O(n).

A brute-force approach would be be to have a hashmap of key to a list of timestamp objects. For set operation,
we just need to append to that end of the list, which is O(1) time. For get operation, we need to iterate through
the entire list and find the correct timestamp, which is O(n) time.

Since the timestamps is always sorted, we can do a binary search instead of doing a linear search to improve the 
time complexity of get operation to O(log n). With a sorted array, binary search should always come into mind.

*/

/*

Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    ListNode head;
    Random random;
    public Solution(ListNode head) {
        this.head = head;
        this.random = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int index = 0;
        int result = 0;
        
        ListNode curr = head;
        while (curr != null) {
            index++;
            if (random.nextInt(index) == 0) {
                result = curr.val;
            }
            curr = curr.next;
        }
        
        return result;
    }
}

/*

Time: O(n) for getRandom(), since we are iterating through the entire nums array.
Space: O(1), since we are using the reservoir sampling algorithm!

This algorithm is known as the reservoir sampling algorithm. It is commonly used when we are working with a "stream" of inputs,
where the total length of the input is unknown. In reservoir sampling, we iterate through the input stream and store the
random integer. 

*/

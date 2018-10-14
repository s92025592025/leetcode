/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode output = new ListNode((l1.val + l2.val) % 10);
        int to_next_digit = (l1.val + l2.val) / 10;
        ListNode walker_out = output;
        ListNode walker_l1 = l1.next;
        ListNode walker_l2 = l2.next;

        while (walker_l1 != null || walker_l2 != null) {
        	if (walker_l1 != null && walker_l2 != null) {
        		walker_out.next = new ListNode((walker_l1.val + walker_l2.val + to_next_digit) % 10);
        		to_next_digit = (walker_l1.val + walker_l2.val + to_next_digit) / 10;
        		walker_l1 = walker_l1.next;
        		walker_l2 = walker_l2.next;
        	} else if (walker_l1 != null) {
        		walker_out.next = new ListNode((walker_l1.val + to_next_digit) % 10);
        		to_next_digit = (walker_l1.val + to_next_digit) / 10;
        		walker_l1 = walker_l1.next;
        	} else {
        		walker_out.next = new ListNode((walker_l2.val + to_next_digit) % 10);
        		to_next_digit = (walker_l2.val + to_next_digit) / 10;
        		walker_l2 = walker_l2.next;
        	}

        	walker_out = walker_out.next;
        }

        if (to_next_digit > 0) {
        	walker_out.next = new ListNode(to_next_digit);
        }

        return output;
    }
}
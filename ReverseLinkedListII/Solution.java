/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
    	if(m == n){
    		return head;
    	}

    	ListNode beforeM = head;
    	ListNode afterN = head;
    	ListNode walker = head;
    	ListNode tempList = head, tempTail = head;

    	for(int i = 1; i <= n; i++){
    		if(i == m - 1){
    			beforeM = walker;
    			walker = walker.next;
    		}else if(i >= m && i <= n){
    			if(i == m){
    				tempList = walker;
    				walker = walker.next;
    				tempList.next = null;
    				tempTail = tempList;
    			}else if(i == m) {
    				afterN = walker.next;
    				ListNode temp = tempList;
    				walker.next = temp;
    				tempList = walker;

    			}else{
    				ListNode temp = tempList;
    				tempList = walker;
    				walker = walker.next;
    				tempList.next = temp;
    			}
    		}else{
    			walker = walker.next;
    		}
    	}

    	beforeM.next = tempList;
    	tempTail.next = afterN;

    	return head;        
    }
}
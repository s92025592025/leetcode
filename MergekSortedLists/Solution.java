/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
    	if(lists.length == 0){
    		return null;
    	}
    	return mergeKLists(0, lists.length, lists);
    }

    public ListNode mergeKLists(int lo, int hi, ListNode[] lists){
    	if(lists.length == 0){
    		return null;
    	}

    	if(hi - lo <= 1){
    		return lists[lo];
    	}

    	// split
    	int mid = (hi + lo) >>> 1;
    	ListNode rightList = mergeKLists(lo, mid, lists);
    	ListNode leftList = mergeKLists(mid, hi, lists);



    	// mergesort
    	ListNode output;
    	ListNode walker;

    	if(rightList == null && leftList == null){
    		return null;
    	}else if(rightList == null){
    		output = leftList;
    		walker = output;
    		leftList = leftList.next;
    		output.next = null;
    	}else if(leftList == null){
    		output = rightList;
    		walker = output;
    		rightList = rightList.next;
    		output.next = null;
    	}else if(rightList.val < leftList.val){
    		output = rightList;
    		walker = output;
    		rightList = rightList.next;
    		output.next = null;
    	}else{
    		output = leftList;
    		walker = output;
    		leftList = leftList.next;
    		output.next = null;
    	}

    	while(!(rightList == null && leftList == null)){
    		if(rightList == null){
    			// add left to list
    			walker.next = leftList;
    			leftList = leftList.next;
    			walker = walker.next;
    			walker.next = null;
    		}else if(leftList == null){
    			// add right to list
    			walker.next = rightList;
    			rightList = rightList.next;
    			walker = walker.next;
    			walker.next = null;
    		}else if(rightList.val < leftList.val){
    			walker.next = rightList;
    			rightList = rightList.next;
    			walker = walker.next;
    			walker.next = null;
    		}else{
    			walker.next = leftList;
    			leftList = leftList.next;
    			walker = walker.next;
    			walker.next = null;
    		}
    	}

    	return output;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
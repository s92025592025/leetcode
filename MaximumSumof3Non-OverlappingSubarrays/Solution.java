import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    	Prefix[] sums = new Prefix[nums.length - k + 1];
    	Stack<Prefix> rLargest = new Stack<Prefix>();
    	Queue<Prefix> lLargest = new LinkedList<Prefix>(); 
    	int[] output = new int[3];

    	for(int i = 0; i < sums.length; i++){
    		Prefix temp = new Prefix(sum(i, k, nums), i);
    		if(rLargest.empty()){
    			rLargest.push(temp);
    			lLargest.add(temp);
    		}

    		sums[i] = temp;

    		if(temp.sum > rLargest.peek().sum){
    			rLargest.push(temp);
    			lLargest.add(temp);
    		}
    	}

    	rLargest = new Stack<Prefix>();

    	// make rLargest
    	for(int i = sums.length - 1; i > k + (k - 1); i--){
    		if(rLargest.empty()){
    			rLargest.push(sums[i]);
    		}else if(sums[i].sum >= rLargest.peek().sum){
    			rLargest.push(sums[i]);
    		}
    	}

    	int maxSum = Integer.MIN_VALUE;

    	// reconsider this part
    	for(int i = k; i < sums.length - k; i++){
    		Prefix lMax = lLargest.peek();
    		if(sums[i - (k - 1)].sum > lMax.sum){ // maybe not i, is i - (k - 2) or sth since the next max may not be
    									// immidiately available
    			lLargest.remove();
    		}

    		Prefix rMax = rLargest.peek();
    		if(sums[i + k].compareTo(rMax) == 0){ // maybe not i + (k - 1), same reason as last comment explained
    			rLargest.pop();
    		}

    		if(rMax.sum + lMax.sum + sums[i].sum > maxSum){
    			maxSum = rMax.sum + lMax.sum + sums[i].sum;
    			output[0] = lMax.index;
    			output[1] = sums[i].index;
    			output[2] = rMax.index;
    		}
    	}

    	return output;
    }

    public int sum(int start, int k, int[] nums){
    	int output = 0;

    	for(int i = start; i < start + k; i++){
    		output += nums[i];
    	}

    	return output;
    }

    class Prefix implements Comparable<Prefix>{
    	int sum;
    	int index;

    	public Prefix(int sum, int index){
    		this.sum = sum;
    		this.index = index;
    	}

    	public int compareTo(Prefix y){
    		if(this.sum == y.sum){
    			return this.index - y.index;
    		}

    		return -(this.sum - y.sum);
    	}
    }
}
import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    	Prefix[] sums = new Prefix[nums.length - k + 1];
    	int[] output = new int[3];

    	for(int i = 0; i < sums.length; i++){
    		sums[i] = new Prefix(sum(i, k, nums), i);
    	}

    	Arrays.sort(sums);

    	int maxSum = Integer.MIN_VALUE;

    	for(int i = 0; i < sums.length; i++){
    		for(int j = i + 1; j < sums.length; j++){
    			if(Math.abs(sums[i].index - sums[j].index) > k - 1){
    				for(int kk = j + 1; kk < sums.length; kk++){
    					if(Math.abs(sums[i].index - sums[kk].index) > k - 1 &&
    						Math.abs(sums[j].index - sums[kk].index) > k - 1 &&
    						sums[i].sum + sums[j].sum + sums[kk].sum > maxSum){
    						output[0] = sums[i].index;
    						output[1] = sums[j].index;
    						output[2] = sums[kk].index;
    						maxSum = sums[i].sum + sums[j].sum + sums[kk].sum;
    					}
    				}
    			}
    		}
    	}

		Arrays.sort(output);
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
class Solution {
    public int maxSubArray(int[] nums) {
    	if (nums.length <= 0) {
    		return 0;
    	}

        int output = 0;
        int possible_larger = 0;
        int largest_int = Integer.MIN_VALUE;
        boolean has_positive = false;

        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] > largest_int) {
        		has_positive = nums[i] > 0;
        		largest_int = nums[i];
        	}

        	possible_larger += nums[i];

        	if (possible_larger > output) {
        		output = possible_larger;
        	} else if (possible_larger < output) {
        		if (possible_larger <= 0) {
        			possible_larger = 0;
        		}
        	}
        }

        if (possible_larger > output) {
        	output = possible_larger;
        }

        if (!has_positive) {
        	return largest_int;
        }

        return output;
    }
}
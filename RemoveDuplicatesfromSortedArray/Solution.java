class Solution {
 	public int removeDuplicates(int[] nums) {
 		if(nums.length == 0){
 			return 0;
 		}

        int output = 1;
        int walker = nums[0];
        int walkerIndex = 0;

        for(int i = 1; i < nums.length; i++){
        	if(walker != nums[i]){
        		output++;
        		walker = nums[i];
        		nums[walkerIndex + 1] = walker;
        		walkerIndex++;
        	}
        }

        return output;
    }
}
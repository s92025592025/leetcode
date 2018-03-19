import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] twoSum(int[] nums, int target) {
 		int[] output = new int[2];
 		Map<Integer, ArrayList<Integer>> numMap = new HashMap<Integer, ArrayList<Integer>>();

 		for(int i = 0; i < nums.length; i++){
 			if(!numMap.containsKey(nums[i])){
 				numMap.put(nums[i], new ArrayList<Integer>());
 			}

 			numMap.get(nums[i]).add(i);
 		}

 		for(int i = 0; i < nums.length; i++){
			if(numMap.containsKey(target - nums[i])){
				output[0] = i;
				if(target - nums[i] == nums[i] && numMap.get(target - nums[i]).size() > 1){
					output[1] = numMap.get(target - nums[i]).get(1);
					return output;
				}else if(target - nums[i] != nums[i]){
					output[1] = numMap.get(target - nums[i]).get(0);
					return output;
				}
			}
 		}


 		return output;
    }
}
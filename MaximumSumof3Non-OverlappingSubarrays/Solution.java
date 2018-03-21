import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int kk) {
    	Map<Integer, List<Integer>> sums = new TreeMap<Integer, List<Integer>>();
    	int[] output = new int[3];

    	for(int i = 0; i < nums.length - kk + 1; i++){
    		int sum = sum(i, kk, nums);
    		if(!sums.containsKey(sum)){
    			List<Integer> list = new ArrayList<Integer>();
    			sums.put(sum, list);
    		}

    		sums.get(sum).add(i);
    	}

    	Integer[] sumSet = new Integer[sums.keySet().size()];
    	sumSet = sums.keySet().toArray(sumSet);
    	int max = Integer.MIN_VALUE;

    	for(int i = 0; i < sumSet.length; i++){
    		int temp = sumSet[i];
    		for(int j = i + 1; j < sumSet.length; j++){
    			temp += sumSet[j];
    			for(int k = j + 1; k < sumSet.length; k++){
    				temp += sumSet[k];
    				if(temp > max){
    					// check possible
    					Checker result = check(sumSet[i], sumSet[j], sumSet[k], sums);
    					if(result.passed){
    						//System.out.println("sums: " + sumSet[i] + ", " + sumSet[j] + ", " + sumSet[k]);
    						// NOTE: sums were right, but index not right

    						output = result.combination;
    					}
    				}
    				temp -= sumSet[k];
    			}
    			temp -= sumSet[j];
    		}
    	}

    	return output;
    }

    public Checker check(int i, int j, int k, Map<Integer, List<Integer>> indexList){
    	Checker output = new Checker();
    	List<Integer> iIndexs = indexList.get(i);
    	List<Integer> jIndexs = indexList.get(j);
    	List<Integer> kIndexs = indexList.get(k);

    	for(int ii = 0; ii < iIndexs.size(); ii++){
    		for(int jj = 0; jj < jIndexs.size(); jj++){
    			if(Math.abs(iIndexs.get(ii) - jIndexs.get(jj)) > 1){
    				for(int kk = 0; kk < kIndexs.size(); kk++){
    					if(Math.abs(iIndexs.get(ii) - kIndexs.get(kk)) > 1 &&
    						Math.abs(jIndexs.get(jj) - kIndexs .get(kk)) > 1){
    						output.passed = true;
    						output.combination[0] = iIndexs.get(ii);
    						output.combination[1] = jIndexs.get(jj);
    						output.combination[2] = iIndexs.get(kk);
    					}
    				}
    			}
    		}
    	}

    	return output;
    }

    class Checker{
    	int[] combination = new int[3];
    	boolean passed = false;;
    }

    public int sum(int start, int k, int[] nums){
    	int output = 0;

    	for(int i = start; i < start + k; i++){
    		output += nums[i];
    	}

    	return output;
    }
}
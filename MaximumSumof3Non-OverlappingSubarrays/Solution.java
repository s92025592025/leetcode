

class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    	Prefix[] sums = new Prefix[nums.length - k + 1];
    	int[] output = new int[3];

    	for(int i = 0; i < sums.length; i++){
    		// get all the prefex sums
    		sums[i] = new Prefix(sum(i, k, nums), i);
    	}

    	quickSort(0, sums.length, sums); // this bombed sth

    	output[0] = sums[0].index;
    	int maxSum = sums[0].sum;
    	int outputI = 1;

    	// testing
    	int[] testing = new int[sums.length];
    	for(int i = 0; i < sums.length; i++){
    		testing[i] = sums[i].sum;
    	}

    	return testing;
    	// testing
    	/*
    	for(int i = 1; i < sums.length && outputI < 3; i++){
    		if(maxSum < sums[i].sum){
    			output[outputI] = sums[i].index;
    			maxSum = sums[i].sum;
    			outputI++;
    		}
    	}

    	return output;    	
    	*/
    }

    public void quickSort(int lo, int hi, Prefix[] arr){
    	if(hi - lo <= 4){
    		for(int i = lo; i < hi; i++){
    			for(int s = i + 1; s < hi; s++){
    				if(arr[s].compareTo(arr[i]) < 0){
    					Prefix temp = arr[i];
    					arr[i] = arr[s];
    					arr[s] = temp;
    				}
    			}
    		}

    		return ;
    	}

    	// quicksort
    	int pivot = findPivot(lo, hi, arr);
    	// move pivot to front
    	Prefix temp = arr[lo];
    	arr[lo] = arr[pivot];
    	arr[pivot] = temp;
    	// start combing
    	int left = lo + 1;
    	int right = hi - 1;
    	boolean moveLeft = true;

    	while(left < right){
    		if(moveLeft){
    			if(arr[lo].compareTo(arr[left]) < 0){
    				moveLeft = false;
    			}else{
    				left++;
    			}
    		}else{
    			if(arr[lo].compareTo(arr[right]) > 0){
    				// swap with left
    				temp = arr[right];
    				arr[right] = arr[left];
    				arr[left] = temp;
    				right--;
    				left++;
    				moveLeft = true;
    			}else{
    				right--;
    			}
    		}
    	}

    	if(arr[lo].compareTo(arr[left]) > 0){
    		temp = arr[left];
    		arr[left] = arr[lo];
    		arr[lo] = temp;
    	}else{
    		temp = arr[left - 1];
    		arr[left - 1] = arr[lo];
    		arr[lo] = temp;
    	}

    	// resurse
    	int mid = (lo + hi) >>> 1;
    	quickSort(lo, mid, arr);
    	quickSort(mid, hi, arr);
    }

    public int findPivot(int lo, int hi, Prefix[] arr){
    	Prefix head = arr[lo];
    	Prefix tail = arr[hi - 1];
    	Prefix mid = arr[(lo + hi) >>> 1];

    	if((head.compareTo(mid) <= 0 && head.compareTo(tail) >= 0) ||
    		(head.compareTo(tail) <= 0 && head.compareTo(mid) >= 0)){
    		return lo;
    	}

    	if((mid.compareTo(head) <= 0 && mid.compareTo(tail) >= 0) ||
    		(mid.compareTo(head) >= 0 && mid.compareTo(tail) <= 0)){
    		return (lo + hi) >>> 1;
    	}

    	return hi - 1;
    }

    public int sum(int start, int k, int[] nums){
    	int output = 0;

    	for(int i = start; i < start + k; i++){
    		output += nums[i];
    	}

    	return output;
    }

    // to store prefex of all combination
    class Prefix implements Comparable<Prefix>{
    	public int sum;
    	public int index;

    	public Prefix(int sum, int index){
    		this.sum = sum;
    		this.index = index;
    	}

    	public int compareTo(Prefix y){
    		if(this.sum == y.sum){
    			// if same, then compare this
    			return -(this.index - y.index);
    		}

    		// compare this first
    		return this.sum - y.sum;
    	}
    }
}
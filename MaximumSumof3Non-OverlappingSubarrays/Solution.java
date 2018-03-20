

class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    	Prefix[] sums = new Prefix[nums.length - k + 1];
    	int[] output = new int[3];

    	for(int i = 0; i < sums.length; i++){
    		// get all the prefex sums
    		sums[i] = new Prefix(sum(i, k, nums), i);
    	}

    	MaxHeap heap = new MaxHeap(sums);

    	// TODO: change to actually write a heap class
    	for(int i = 0; i < 3; i++){
    		output[i] = heap.pop().index;
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

    private class MaxHeap{
    	public int size;
    	public Prefix[] heap;

    	public MaxHeap(Prefix[] heap){
    		this.heap = heap;
    		this.size = this.heap.length;

    		for(int i = this.heap.length - 1; i > 0; i--){
	    		int parent = (i - 1) >>> 1;
	    		if(this.heap[i].compareTo(this.heap[parent]) > 0){
	    			// this is percolate up
	    			Prefix temp = this.heap[i];
	    			this.heap[i] = this.heap[parent];
	    			this.heap[parent] = temp;
    			}
    		}
    	}

    	public Prefix pop(){
    		Prefix output = this.heap[0];
    		int parent = 0;
    		// TODO: pop and move things up

    		// remake perculate up
    		this.heap[0] = this.heap[this.size - 1];
    		this.size--;
    		while(parent < this.size){
    			int left = (parent >> 2) + 1;
    			int right = (parent >> 2) + 2;
    			if(!(left >= this.size && right >= this.size)){
    				if(left >= this.size){
    					Prefix temp = this.heap[right];
    					this.heap[right] = this.heap[parent];
    					this.heap[parent] = temp;
    					parent = right;
    				}else if(right >= this.size){
    					Prefix temp = this.heap[left];
    					this.heap[left] = this.heap[parent];
    					this.heap[parent] = temp;
    					parent = left;
    				}else{
    					if(this.heap[right].compareTo(this.heap[left]) > 0){
    						Prefix temp = this.heap[right];
	    					this.heap[right] = this.heap[parent];
	    					this.heap[parent] = temp;
	    					parent = right;
    					}else{
    						Prefix temp = this.heap[left];
	    					this.heap[left] = this.heap[parent];
	    					this.heap[parent] = temp;
	    					parent = left;
    					}
    				}
    			}else{
    				parent = right;
    			}
    		}


    		return output;
    	}
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
    		if(y == null){
    			return 1;
    		}

    		if(this.sum == y.sum){
    			// if same, then compare this
    			return -(this.index - y.index);
    		}

    		// compare this first
    		return this.sum - y.sum;
    	}
    }
}
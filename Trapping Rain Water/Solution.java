import java.util.Set;
import java.util.HashSet;

class Solution {
    public int trap(int[] height) {
    	int output = 0;
    	int possible = 0;
    	int left_block = 0;
    	Set<Pairs> filled = new HashSet<Pairs>();
        for (int i = 1; i < height.length; i++) {
        	if (height[left_block] > 0) {
        		if (height[left_block] <= height[i]) {
        			filled.add(new Pairs(left_block, i));
        			left_block = i;

        			output += possible;
        			possible = 0;
        		} else {
        			possible += height[left_block] - height[i];
        		}
        	} else {
        		left_block = i;
        	}
        }

    	int right_block = height.length - 1;
    	possible = 0;

        for (int i = height.length - 2; i >= 0; i--) {
        	if (height[right_block] > 0) {
        		if (height[right_block] <= height[i]) {
        			if (filled.contains(new Pairs(i, right_block))) {
        				possible = 0;
        			}
        			right_block = i;
        			
        			output += possible;
        			possible = 0;
        		} else {
        			possible += height[right_block] - height[i];
        		}
        	} else {
        		right_block = i;
        	}
        }

        return output;
    }

    private class Pairs {
    	public int left;
    	public int right;

    	public Pairs(int left, int right) {
    		this.left = left;
    		this.right = right;
    	}

    	public int hashCode() {
    		return this.left + this.right * 37;
    	}

    	public boolean equals(Object o) {
    		if (o instanceof Pairs) {
    			Pairs p = (Pairs)o;

    			return this.left == p.left && this.right == p.right;
    		}

    			return false;
    	}
    }
}
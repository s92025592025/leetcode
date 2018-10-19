import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public String nextClosestTime(String time) {
        Clock std = new Clock((time.charAt(0) - '0') * 10 + (time.charAt(1) - '0'), (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0'));
        Set<Clock> rearenge = new HashSet<Clock>();
        List<Integer> digits = new ArrayList<Integer>();

        // find all four digits
        for (int i = 0; i < time.length(); i++) {
        	if (time.charAt(i) != ':') {
        		digits.add(time.charAt(i) - '0');
        	}
        }

        // make all combination
        for (int h_ten : digits) {
        	for (int h_one : digits) {
        		for (int m_ten : digits) {
        			for (int m_one : digits) {
        				Clock temp = new Clock(h_ten * 10 + h_one, m_ten * 10 + m_one);
        				if (!temp.equals(std) && temp.hour / 24 < 1 && temp.min / 60 < 1) {
        					rearenge.add(temp);
        				}
        			}
        		}
        	}
        }

        // start comparing
        Clock output = std;
        int min_delta = Integer.MAX_VALUE;
        for (Clock other : rearenge) {
        	if (std.delta(other) < min_delta) {
        		min_delta = std.delta(other);
        		output = other;
        	}
        }

        return (output.hour / 10) + "" + (output.hour % 10) + ":" + (output.min / 10) + "" + (output.min % 10);
    }

    private class Clock {
    	public int hour;
    	public int min;

    	public Clock(int hour, int min) {
    		this.hour = hour;
    		this.min = min;
    	}

    	public int hashCode() {
    		return hour * 60 + min;
    	}

    	public int delta(Clock c) {
    		if (c.hashCode() - this.hashCode() <= 0) {
    			return 24 * 60 + c.hashCode();
    		}

    		return c.hashCode() - this.hashCode();
    		
    	}

    	public boolean equals(Object o) {
    		if (o instanceof Clock) {
    			Clock c = (Clock)o;
    			return this.hashCode() == c.hashCode();
    		}

    		return false;
    	}
    }
}
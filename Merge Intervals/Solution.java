/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

import java.util.Collections;

class Solution {
    public List<Interval> merge(List<Interval> intervals) {
    	Collections.sort(intervals, (x, y) -> x.start - y.start);
    	List<Interval> output = new ArrayList<Interval>();

    	for (int i = 0; i < intervals.size(); i++) {
    		if (output.size() == 0) {
    			output.add(intervals.get(i));
    		} else if (output.get(output.size() - 1).start <= intervals.get(i).start 
    			&& output.get(output.size() - 1).end >= intervals.get(i).start) {
    			Interval temp = output.get(output.size() - 1);

    			output.remove(output.size() - 1);
    			output.add(new Interval(temp.start, Math.max(temp.end, intervals.get(i).end)));
    		} else {
    			output.add(intervals.get(i));
    		}
    	}

    	return output;
    }
}
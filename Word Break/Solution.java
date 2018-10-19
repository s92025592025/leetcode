import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
    	Set<String> parts = new HashSet<String>(wordDict);

    	Queue<Integer> visited = new LinkedList<Integer>();
    	Set<Integer> went = new HashSet<Integer>();
    	visited.add(0);
    	went.add(0);
		
		while (!visited.isEmpty()) {
			int parent = visited.remove();

			for (int i = parent; i < s.length(); i++) {
				if (parts.contains(s.substring(parent, i + 1))) {
					if (!went.contains(i + 1)) {
						visited.add(i + 1);
						went.add(i + 1);
					}

					if (i + 1 == s.length()) {
						return true;
					}
				}
			}
		}

		return false;
    }
}
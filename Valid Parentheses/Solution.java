import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> open = new Stack<Character>();
        Map<Character, Character> open_closed = new HashMap<Character, Character>();
        Set<Character> close = new HashSet<Character>();

        open_closed.put('(', ')');
        open_closed.put('[', ']');
        open_closed.put('{', '}');

        close.add(')');
        close.add(']');
        close.add('}');

        for (int i = 0; i < s.length(); i++) {
        	if (open_closed.containsKey(s.charAt(i))) {
        		open.push(s.charAt(i));
        	} else if (open.isEmpty() && close.contains(s.charAt(i))) {
        		return false;
        	} else if (close.contains(s.charAt(i))){
        		if (open_closed.get(open.peek()) == s.charAt(i)) {
        			open.pop();
        		} else {
        			return false;
        		}
        	}
        }

        return open.isEmpty();
    }
}
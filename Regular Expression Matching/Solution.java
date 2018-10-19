import java.util.Stack;

class Solution {
    public boolean isMatch(String s, String p) {
    	Stack<Matcher> regex_pre = new Stack<Matcher>();
    	Stack<Matcher> regex = new Stack<Matcher>();

    	// build matcher pre stack
    	int p_walker = 0;
    	while (p_walker < p.length()) {
    		if (p_walker + 1 < p.length() && p.charAt(p_walker + 1) == '*') {
    			regex_pre.push(new Matcher(p.charAt(p_walker + 1), p.charAt(p_walker)));

    			p_walker += 2;
    		} else {
    			regex_pre.push(new Matcher(p.charAt(p_walker)));
    			p_walker++;
    		}
    	}

    	// reverse stack
    	while (!regex_pre.isEmpty()) {
    		regex.push(regex_pre.pop());
    	}

    	// start matching
    	return isMatch(s, regex);
    }

    private boolean isMatch(String s, Stack<Matcher> regex) {
    	if (s.length() > 0 && regex.isEmpty()) {
    		return false;
    	}

    	if (s.length() == 0) {
    		Stack<Matcher> temp = new Stack<Matcher>(); 
    		while (!regex.isEmpty() && regex.peek().special) {
    			temp.push(regex.pop());
    		}

    		boolean output = regex.isEmpty();

    		while (!temp.isEmpty()) {
    			regex.push(temp.pop());
    		}

    		return output;
    	}

    	if (!regex.peek().special) {
    		if (regex.peek().letter != s.charAt(0) && regex.peek().letter != '.') {
    			return false;
    		}

    		Matcher temp = regex.pop();
    		boolean sub = isMatch(s.substring(1), regex);
    		regex.push(temp);

    		return true && sub;
    	}

		// with star
    	if (regex.peek().letter != s.charAt(0) && regex.peek().letter != '.') {
    		Matcher temp = regex.pop();
    		boolean sub = isMatch(s, regex);
    		regex.push(temp);

    		return sub;
    	}

    	Matcher temp = regex.pop();
		boolean sub = isMatch(s, regex);
		regex.push(temp);

		if (sub) {
			return true;
		}

		return true && isMatch(s.substring(1), regex);
    }

    private class Matcher {
    	public boolean special;
    	public char special_char;
    	public char letter;

    	public Matcher(char special_char, char letter) {
    		this.special = true;
    		this.special_char = special_char;
    		this.letter = letter;
    	}

    	public Matcher(char letter) {
    		this.special = false;
    		//this.special_char = null;
    		this.letter = letter;
    	}
    }
}
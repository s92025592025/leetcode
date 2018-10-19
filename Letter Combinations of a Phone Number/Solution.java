import java.util.Map;
import java.util.HashMap;

class Solution {
    public List<String> letterCombinations(String digits) {
		Map<Character, String[]> mapping = new HashMap<Character, String[]>();

		mapping.put('2', new String[] {"a", "b", "c"});
		mapping.put('3', new String[] {"d", "e", "f"});
		mapping.put('4', new String[] {"g", "h", "i"});
		mapping.put('5', new String[] {"j", "k", "l"});
		mapping.put('6', new String[] {"m", "n", "o"});
		mapping.put('7', new String[] {"p", "q", "r", "s"});
		mapping.put('8', new String[] {"t", "u", "v"});
		mapping.put('9', new String[] {"w", "x", "y", "z"});

		return makeCombination(digits, mapping);

    }

    private List<String> makeCombination(String digits, Map<Character, String[]> mapping) {
    	if (digits.length() == 0) {
    		return new ArrayList<String>();
    	}

    	String[] letters = mapping.get(digits.charAt(0));
    	List<String> pre = makeCombination(digits.substring(1), mapping);
    	List<String> output = new ArrayList<String>();

    	if (pre.size() == 0) {
    		for (int i = 0; i < letters.length; i++) {
	    		output.add(letters[i]);
	    	}


	    	return output;
    	}

    	for (int i = 0; i < letters.length; i++) {
    		for (String s : pre) {
    			output.add(letters[i] + s);
    		}
    	}

    	return output;
    }
}
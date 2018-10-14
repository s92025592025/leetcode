import java.util.Map;
import java.util.Queue;

class LRUCache {
	private int size;
	private Map<Integer, Integer> map;
	Queue<Wraper> lru;

    public LRUCache(int capacity) {
        this.size = capacity;
        this.map = new HashMap<Integer, Integer>();
        this.lru = new LinkedList<Wraper>();
    }
    
    public int get(int key) {
        if (this.lru.isEmpty() || !map.containsKey(key)) {
        	return -1;
        }

        int output = map.get(key);
        lru.remove(new Wraper(key)); // may make self defined queue to achieve O(1) remove
        lru.add(new Wraper(key));

        return output;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
        	map.put(key, value);
        	lru.remove(new Wraper(key));
        	lru.add(new Wraper(key));
        } else {
        	map.put(key, value);
        	lru.add(new Wraper(key));

        	// check if larger then lru
        	if (lru.size() > this.size) {
	        	Wraper removed = lru.remove();
	       		map.remove(removed.key);
	        }
        }
    }

    private class Wraper {
    	int key;

    	public Wraper(int key) {
    		this.key = key;
    	}

    	public int hashCode() {
    		return this.key;
    	}

    	public boolean equals(Object o) {
    		if (o instanceof Wraper) {
    			Wraper w = (Wraper)o;

    			return this.key == w.key;
    		}

    			return false;
    	}
    }
}
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

class RandomizedSet {
    Set<Integer> hashTable;


    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.hashTable = new HashSet<Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        return this.hashTable.add(val);
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        return this.hashTable.remove(val);
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random rand = new Random();
        return (Integer)(this.hashTable.toArray()[(int)(rand.nextFloat() * this.hashTable.size())]);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
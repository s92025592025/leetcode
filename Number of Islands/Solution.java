import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int numIslands(char[][] grid) {
        int output = 0;
        char marker = 'a';

        for (int x = 0; x < grid.length; x++) {
        	for (int y = 0; y < grid[x].length; y++) {
        		if (grid[x][y] == '1') {
        			markIsland(grid, new Coord(x, y), marker);
        			marker++;
        			output++;
        		}
        	}
        }

        return output;
    }

    public void markIsland(char[][] grid, Coord start, char marker) {
    	Queue<Coord> toVisitChild = new LinkedList<Coord>();
    	Set<Coord> visited = new HashSet<Coord>();

    	visited.add(start);
    	toVisitChild.add(start);

    	while (!toVisitChild.isEmpty()) {
    		Coord parent = toVisitChild.remove();
    		grid[parent.x][parent.y] = marker;

    		if (parent.x > 0 && grid[parent.x - 1][parent.y] == '1' && !visited.contains(new Coord(parent.x - 1, parent.y))) { 
    			toVisitChild.add(new Coord(parent.x - 1, parent.y)); 
    			visited.add(new Coord(parent.x - 1, parent.y));
    		}
        	if (parent.y > 0 && grid[parent.x][parent.y - 1] == '1' && !visited.contains(new Coord(parent.x, parent.y - 1))) { 
        		toVisitChild.add(new Coord(parent.x, parent.y - 1)); 
    			visited.add(new Coord(parent.x, parent.y - 1));
        	}
        	if (parent.x < grid.length - 1 && grid[parent.x + 1][parent.y] == '1' && !visited.contains(new Coord(parent.x + 1, parent.y))) {
        	 	toVisitChild.add(new Coord(parent.x + 1, parent.y)); 
    			visited.add(new Coord(parent.x + 1, parent.y));
        	}
        	if (parent.y < grid[parent.x].length - 1 && grid[parent.x][parent.y + 1] == '1' && !visited.contains(new Coord(parent.x, parent.y + 1))) { 
        		toVisitChild.add(new Coord(parent.x, parent.y + 1)); 
    			visited.add(new Coord(parent.x, parent.y + 1)); 
        	}
    	}
    }

    private class Coord {
    	public int x;
    	public int y;

    	public Coord(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}

    	public int hashCode() {
    		return x + y * 37;
    	}

    	public boolean equals(Object o) {
    		if (o instanceof Coord) {
    			Coord c = (Coord)o;

    			return this.x == c.x && this.y == c.y;
    		}

    			return false;
    	}
    }
}
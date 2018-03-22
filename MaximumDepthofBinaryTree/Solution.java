/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        return findDepth(0, root);
    }

    public int findDepth(int depth, TreeNode node){
    	if(node == null){
    		return depth;
    	}

    	depth++;

    	int lDepth = findDepth(depth, node.left);
    	int rDepth = findDepth(depth, node.right);

    	if(lDepth > rDepth){
    		return lDepth;
    	}

    	return rDepth;
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int x = 0;
    class Pair {
        int sum;
        int count;
        Pair(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return x;
    }
    Pair dfs(TreeNode root) {
        if (root == null) return new Pair(0,0);
        Pair left = dfs(root.left);
        Pair right = dfs(root.right);
        int sum = root.val + left.sum + right.sum;
        int count = 1 + left.count + right.count;
        if (sum / count == root.val) x++;
        return new Pair(sum, count);
    }
}
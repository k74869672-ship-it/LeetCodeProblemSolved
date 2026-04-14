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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return dfs(root, targetSum);
    }

    private boolean dfs(TreeNode node, int remainingSum) {
        if (node == null) {
            return false;
        }
        remainingSum -= node.val;
        if (node.left == null && node.right == null) {
            return remainingSum == 0;
        }
        // return dfs(node.left,remainingSum)||dfs(node.right,remainingSum);
        boolean leftResult = dfs(node.left, remainingSum);
        if (leftResult == true) {
            return true;
        }
        boolean rightResult = dfs(node.right, remainingSum);
        if (rightResult == true) {
            return true;
        }
        return false;
    }
}
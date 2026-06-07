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
    public TreeNode createBinaryTree(int[][] descriptions) {
        HashMap<Integer, TreeNode> node = new HashMap<>();

        HashSet<Integer> nodeSet = new HashSet<>();

        for (int[] desc : descriptions) {
            int parentVal = desc[0];
            int childVal = desc[1];
            int isLeft = desc[2];

            node.putIfAbsent(parentVal, new TreeNode(parentVal));
            node.putIfAbsent(childVal, new TreeNode(childVal));

            if (isLeft == 1) {
                node.get(parentVal).left = node.get(childVal);
            } else {
                node.get(parentVal).right = node.get(childVal);
            }

            nodeSet.add(childVal);
        }
        for (int val : node.keySet()) {
            if (!nodeSet.contains(val)) {
                return node.get(val);
            }
        }
        return null;
    }
}
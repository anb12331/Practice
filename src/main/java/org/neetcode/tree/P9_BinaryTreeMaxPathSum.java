package org.neetcode.tree;

class P9_BinaryTreeMaxPathSum {
    public int maxPathSum(TreeNode root) {
        globalMaxPath = root.val;
        findMaxPath(root);
        return globalMaxPath;
    }

    int globalMaxPath;

    private int findMaxPath(TreeNode node) {
        int currVal = node.val;
        int leftMaxPath = 0, rightMaxPath = 0;

        if(node.left != null) leftMaxPath = findMaxPath(node.left);
        if(node.right != null) rightMaxPath = findMaxPath(node.right);

        int maxPathAbove = Math.max(currVal, currVal + Math.max(leftMaxPath, rightMaxPath));
        globalMaxPath = Math.max(maxPathAbove, globalMaxPath);

        int pathNotGoingAbove = currVal + leftMaxPath + rightMaxPath;
        globalMaxPath = Math.max(globalMaxPath, pathNotGoingAbove);

        return Math.max(0, maxPathAbove);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.addLeft(9);
        root.addRight(20).addLeft(15);
        root.right.addRight(7);

        System.out.println(new P9_BinaryTreeMaxPathSum().maxPathSum(root));
    }

}

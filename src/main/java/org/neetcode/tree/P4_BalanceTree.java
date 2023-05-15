package org.neetcode.tree;

public class P4_BalanceTree {
    public boolean isBalanced(TreeNode root) {
        return getDepthIfBalanced(root) > -1;

    }

    private int getDepthIfBalanced(TreeNode node) {
        if(node == null) return 0;

        int leftDepth = getDepthIfBalanced(node.left);
        int rightDepth = getDepthIfBalanced(node.right);

        if (leftDepth == -1 || rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1)
            return -1;
        else
            return 1 + Math.max(leftDepth, rightDepth);

    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(new int[]{1,3,5,2,6,4,0});
        System.out.println(new P4_BalanceTree().isBalanced(root));
    }
}

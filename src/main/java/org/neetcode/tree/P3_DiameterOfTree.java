package org.neetcode.tree;

import java.util.concurrent.atomic.AtomicInteger;

public class P3_DiameterOfTree {
    public int diameterOfBinaryTree(TreeNode root) {
        getDiameter(root);
        return maxDiameter;
    }

    private Integer maxDiameter = 0;
    private int getDiameter(TreeNode root) {
        int leftMaxPath = root.left != null ? 1 + getDiameter(root.left) : 0;
        int rightMaxPath = root.right != null ? 1 + getDiameter(root.right) : 0;

        int diameterForNode = leftMaxPath + rightMaxPath;
        synchronized (maxDiameter) {
            if(diameterForNode > maxDiameter) maxDiameter = diameterForNode;
        }
        return Math.max(leftMaxPath, rightMaxPath);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(new int[]{4,2,3,1,5});
        System.out.println(new P3_DiameterOfTree().diameterOfBinaryTree(root));
    }
}

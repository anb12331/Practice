package org.neetcode.tree;

public class P2_MaxDepthOfBinTree {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 :
                Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

//    private int getDepth()

    public static void main(String[] args) {
        System.out.println(new P2_MaxDepthOfBinTree().maxDepth(
                TreeNode.build(new int[] {3,1,20,15,27})
        ));
    }
}

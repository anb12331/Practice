package org.neetcode.tree;

public class P1_InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private void invert(TreeNode root) {
        if(root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invert(root.right);
            invert(root.left);
        }
    }


    public static void main(String[] args) {
        System.out.println(TreeNode.toListInOrder(
                new P1_InvertBinaryTree().invertTree(
                        TreeNode.build(new int[] {4,7,2,1,3,6,9})
                )
        ));
    }
}

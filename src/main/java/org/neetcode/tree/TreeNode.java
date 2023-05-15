package org.neetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static List<Integer> print(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrderTrv(root, res);
        return res;
    }

    private static void preOrderTrv(TreeNode root, List<Integer> res) {
        if(root != null) {
            res.add(root.val);
            preOrderTrv(root.left, res);
            preOrderTrv(root.right, res);
        }
    }

    private static void inOrderTrv(TreeNode root, List<Integer> res) {
        if(root != null) {
            inOrderTrv(root.left, res);
            res.add(root.val);
            inOrderTrv(root.right, res);
        }
    }

    private static void addNode(TreeNode root, int val) {
        if(val < root.val) {
            if(root.left != null)
                addNode(root.left, val);
            else root.left = new TreeNode(val);
        } else if(val > root.val) {
            if(root.right != null)
                addNode(root.right, val);
            else root.right = new TreeNode(val);
        } else {
            //Ignore duplicates??
        }
    }

    public static TreeNode build(int[] preOrderTrVals) {
        TreeNode root = new TreeNode(preOrderTrVals[0]);

        for(int i = 0; i < preOrderTrVals.length; i++) {
            addNode(root, preOrderTrVals[i]);
        }

        return root;
    }
}

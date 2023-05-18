package org.neetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class P7_BinTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        res.add(new ArrayList<>());
        Deque<LevelTreeNode> q = new ArrayDeque<>();
        int level = 0;
        q.add(new LevelTreeNode(root, 0));


        while(!q.isEmpty()) {
            LevelTreeNode lNode = q.pollFirst();
            TreeNode poppedNode = lNode.node;
            int poppedNodeLevel = lNode.level;
            if(poppedNodeLevel > level) {
                level++;
                res.add(new ArrayList<>());
            }
            res.get(level).add(poppedNode.val);

            if(poppedNode.left != null)
                q.add(new LevelTreeNode(poppedNode.left, level + 1));
            if(poppedNode.right != null)
                q.add(new LevelTreeNode(poppedNode.right, level + 1));
        }

        return res;
    }

    class LevelTreeNode {
        int level;
        TreeNode node;

        LevelTreeNode(TreeNode node, int level) {
            this.node = node; this.level = level;
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(new int[] {9,3,20,15,21});
        System.out.println(new P7_BinTreeLevelOrderTraversal().levelOrder(root));
    }
}

package org.neetcode.tree;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P6_BinTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        res = new ArrayList<>();

        Deque<TreeNode> q = new ArrayDeque<>();
        q.addLast(root);
        int level = 0;
        int nodesAddedInCurrLevel = 0;
        Deque<TreeNode> nextLevelQ = new ArrayDeque<>();

        /**
         *         6
         *     4      8
         *   3  5    7
         2
         */

        while(!q.isEmpty()) {
            TreeNode node = q.pollFirst();

            if(nodesAddedInCurrLevel == 0) {
                nodesAddedInCurrLevel++;
                res.add(node.val);
            }

            if(node.right != null) nextLevelQ.addLast(node.right);
            if(node.left != null) nextLevelQ.addLast(node.left);

            if(q.isEmpty()) {
                q = nextLevelQ;
                nextLevelQ = new ArrayDeque<>();
                nodesAddedInCurrLevel = 0;
            }
        }

        return res;
    }

    private List<Integer> res;

    public static void main(String[] args) {
        TreeNode root = TreeNode.build(new int[] {6,4,3,2,5,8,7});
        System.out.println(new P6_BinTreeRightSideView().rightSideView(root));
    }


}

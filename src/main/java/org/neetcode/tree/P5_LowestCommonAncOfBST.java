package org.neetcode.tree;


import java.util.ArrayList;
import java.util.List;

public class P5_LowestCommonAncOfBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        search(p.val, root, false);
        search(q.val, root, true);

        return lowestCommonAnc;
    }

    private List<Integer> path = new ArrayList<>();

    int pathPointer = 0;

    private TreeNode lowestCommonAnc = null;

    private TreeNode search(int val, TreeNode root, boolean checkPath) {
        if(root == null) return null;

        if(checkPath) {
            if(pathPointer < path.size() && root.val == path.get(pathPointer)) {
                lowestCommonAnc = root;
                pathPointer++;
            } else {
                return null;
            }
        } else {
            path.add(root.val);
        }
        if(root.val == val) {
            return root;
        }
        else if (val < root.val) {
            return search(val, root.left, checkPath);
        } else {
            return search(val, root.right, checkPath);
        }
    }

    public static void main(String[] args) {
        P5_LowestCommonAncOfBST p = new P5_LowestCommonAncOfBST();
        TreeNode root = TreeNode.build(new int[] {6,2,0,4,3,5,8,7,9});
        TreeNode node1 = p.search(0, root, false);
        TreeNode node2 = p.search(3, root, false);
        System.out.println(p.lowestCommonAncestor(root, node1, node2).val);
    }
}

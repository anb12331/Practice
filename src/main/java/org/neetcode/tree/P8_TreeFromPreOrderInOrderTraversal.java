package org.neetcode.tree;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class P8_TreeFromPreOrderInOrderTraversal {
    //3,9,20,15,7
    //9,3,15,20,7
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder; this.inorder = inorder;
        inMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
        return recursivelyBuildTree(0, preorder.length, 0, inorder.length);
    }

    int[] preorder; int[] inorder;
    Map<Integer, Integer> inMap;

    private TreeNode recursivelyBuildTree(int preStart, int preEnd, int inOrdStart, int inOrdEnd) {
        int root = preorder[preStart];
        TreeNode rootNode = new TreeNode(root);
        int rootInOrderPosn = inMap.get(root);
        int leftPreEnd = preStart + 1;
        if(rootInOrderPosn > inOrdStart) { //then root has left node
            int leftPreStart = preStart + 1;
            leftPreEnd = leftPreStart + (rootInOrderPosn - inOrdStart);
            rootNode.left = recursivelyBuildTree(leftPreStart, leftPreEnd, inOrdStart, rootInOrderPosn);
        }

        if(rootInOrderPosn < inOrdEnd - 1) { //then root has right node
            int rightPreStart = leftPreEnd;
            int rightPreEnd = preEnd;
            rootNode.right = recursivelyBuildTree(rightPreStart, rightPreEnd, rootInOrderPosn + 1, inOrdEnd);
        }

        return rootNode;
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};


        preorder = new int[] {1,2};
        inorder = new int[] {1,2};

        TreeNode root = new P8_TreeFromPreOrderInOrderTraversal().buildTree(preorder, inorder);
        System.out.println(TreeNode.toListPreOrder(root));
    }

}

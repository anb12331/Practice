package org.neetcode.tree;

class P10_SerializeDeserializeTree {
    StringBuilder sb = new StringBuilder();

    public String serialize(TreeNode root) {
        if(root == null) return "";
        sb.delete(0, sb.length());
        return serializeRecursive(root);
    }

    // Encodes a tree to a single string.
    public String serializeRecursive(TreeNode root) {
        sb.append(root.val);
        if(root.left != null || root.right != null) {
            sb.append("[");
            if (root.left != null) {
                serializeRecursive(root.left);
            }
            sb.append(",");
            if (root.right != null) {
                serializeRecursive(root.right);
            }
            sb.append("]");
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty()) return null;
        schars = data.toCharArray();
        sb.delete(0, sb.length());
        start = 0;
        return deserializeRecursive();
    }

    char[] schars;
    int start;

    private TreeNode deserializeRecursive() {
        if(schars[start] == '-') {
            sb.append('-');
            start++;
        }

        while(Character.isDigit(schars[start])) {
            sb.append(schars[start]);
            start++;
        }
        int val = Integer.parseInt(sb.toString());
        sb.delete(0, sb.length());

        TreeNode node = new TreeNode(val);
        if(schars[start] == '[') {
            start++; //[
            if(schars[start] != ',') node.left = deserializeRecursive();
            start++; //,
            if(schars[start] != ']') node.right = deserializeRecursive();
            start++; //]
        }

        return node;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.addLeft(9);
        root.addRight(20).addLeft(15);
        root.right.addRight(7);

        P10_SerializeDeserializeTree codec = new P10_SerializeDeserializeTree();

        String ser = codec.serialize(root);
        System.out.println(ser);
        TreeNode newRoot = codec.deserialize(ser);
        System.out.println(TreeNode.toListPreOrder(newRoot));
    }
}

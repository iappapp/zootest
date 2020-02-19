package com.github.iappapp.leetcode;

import com.github.iappapp.modal.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 平衡二叉树 LCOF
 * 判断二叉树是否是平衡二叉树
 */
public class BinaryTree {

    public static boolean isBalanced(TreeNode root) {
        if (null == root) {
            return true;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }
    // 递归
    private static int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = height(root.left);
        int rightDepth = height(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 非递归 使用后序遍历
    public static int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height = 0;
        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> tag = new Stack<>();
        while (root != null || !nodes.isEmpty()) {
            while (root != null) {
                nodes.push(root);
                tag.push(0);
                root = root.left;
            }
            if (tag.peek() == 1) {
                height = Math.max(height, nodes.size());
                nodes.pop();
                tag.pop();
                root = null;
            } else {
                root = nodes.peek();
                root = root.right;
                tag.pop();
                tag.push(1);
            }
        }
        return height;
    }

    // 非递归 使用队列
    public static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int height = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.peek();
            if (node.left == null && node.right == null) {
                break;
            } else {
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                queue.poll();
                height++;
            }

        }
        return height;
    }
}

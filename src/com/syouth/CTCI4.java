package com.syouth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by anton.ivanov on 8/5/2016.
 */
public class CTCI4 {
    public static Library.TreeNode CreateMinimalBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = (start + end) / 2;
        Library.TreeNode node = new Library.TreeNode(arr[middle]);
        node.setLeft(CreateMinimalBST(arr, start, middle - 1));
        node.setRight(CreateMinimalBST(arr, middle + 1, end));
        return node;
    }

    public static boolean FindRoute(Library.GraphNode start, Library.GraphNode finish) {
        HashSet<Library.GraphNode> selectedForVisit = new HashSet<>();

        Queue<Library.GraphNode> toVisit = new LinkedList<>();

        toVisit.add(start);

        while (!toVisit.isEmpty()) {
            Library.GraphNode node = toVisit.remove();
            if (node == finish) {
                return true;
            } else {
                for (Library.GraphNode n : node.getNeigthbors()) {
                    if (selectedForVisit.contains(n)) {
                        continue;
                    } else {
                        toVisit.add(n);
                        selectedForVisit.add(n);
                    }
                }
            }
        }

        return false;
    }

    public static boolean HasRoute(Library.GraphNode n1, Library.GraphNode n2) {
        return (FindRoute(n1, n2) || FindRoute(n2, n1));
    }

    public static Library.TreeNode CreateMinimalBST(int arr[]) {
        return CreateMinimalBST(arr, 0, arr.length - 1);
    }

    public static LinkedList<LinkedList<Library.TreeNode>> PrintBTByLayers(Library.TreeNode root) {
        LinkedList<LinkedList<Library.TreeNode>> result = new LinkedList<>();
        Queue<Library.TreeNode> toVisit = new LinkedList<>();

        toVisit.add(root);
        int nodesInCurLayer = 1;
        int nodesInNextLayer = 0;
        int layer = 0;
        result.add(new LinkedList<>());

        while (!toVisit.isEmpty()) {
            for (int i = 0; i < nodesInCurLayer; i++) {
                Library.TreeNode node = toVisit.remove();
                result.get(layer).add(node);
                if (node.left != null) {
                    nodesInNextLayer++;
                    toVisit.add(node.left);
                }
                if (node.rigth != null) {
                    nodesInNextLayer++;
                    toVisit.add(node.rigth);
                }
            }

            layer++;
            nodesInCurLayer = nodesInNextLayer;
            nodesInNextLayer = 0;
            if (nodesInCurLayer != 0) {
                result.add(new LinkedList<>());
            }
        }

        return result;
    }

    private static int getBranchHeight(Library.TreeNode n) {
        if (n == null) {
            return 0;
        }

        return 1 + Math.max(getBranchHeight(n.left), getBranchHeight(n.rigth));
    }

    public static boolean CheckBalanced(Library.TreeNode node) {
        int hDiff = Math.abs(getBranchHeight(node.left) - getBranchHeight(node.rigth));
        if (hDiff > 1) {
            return false;
        } else {
            return CheckBalanced(node.left) && CheckBalanced(node.rigth);
        }
    }
}

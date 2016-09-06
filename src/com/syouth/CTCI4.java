package com.syouth;

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

    private static void TraverseDepthNaturalOrder(Library.TreeNode root, LinkedList<Integer> sorted) {
        if (root == null) {
            return;
        }
        TraverseDepthNaturalOrder(root.left, sorted);
        sorted.add(root.data);
        TraverseDepthNaturalOrder(root.rigth, sorted);
    }

    public static boolean IsBST(Library.TreeNode root) {
        LinkedList<Integer> sorted = new LinkedList<>();
        TraverseDepthNaturalOrder(root, sorted);
        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i - 1) > sorted.get(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean IsBSTRecursive(Library.TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftResult = true;
        if (root.left != null) {
            if (root.data < root.left.data) {
                return false;
            } else {
                leftResult = IsBSTRecursive(root.left);
            }
        }
        boolean rightResult = true;
        if (root.rigth != null) {
            if (root.rigth.data < root.data) {
                return false;
            } else {
                rightResult = IsBSTRecursive(root.rigth);
            }
        }

        return leftResult && rightResult;
    }

    private static boolean IsNodeInSubtree(Library.TreeNode root, Library.TreeNode node, Library.TreeNode n2) {
        if (root == null) {
            return false;
        }
        if (root == node || root == n2) {
            return true;
        } else {
            return IsNodeInSubtree(root.rigth, node, n2) || IsNodeInSubtree(root.left, node, n2);
        }
    }

    public static Library.TreeNode FindFirstCommonAncestor(Library.TreeNode treeRoot, Library.TreeNode n1,
                                                           Library.TreeNode n2) {
        boolean inRightSubtree = IsNodeInSubtree(treeRoot.rigth, n1, n2);
        boolean inLeftSubtree = IsNodeInSubtree(treeRoot.left, n1, n2);
        if (inLeftSubtree && inRightSubtree) {
            return treeRoot;
        } else if (inLeftSubtree && (treeRoot.left != n1 && treeRoot.left != n2)) {
            return FindFirstCommonAncestor(treeRoot.left, n1, n2);
        } else if (inRightSubtree && (treeRoot.rigth != n1 && treeRoot.rigth != n2)){
            return FindFirstCommonAncestor(treeRoot.rigth, n1, n2);
        } else {
            return treeRoot;
        }
    }
}
